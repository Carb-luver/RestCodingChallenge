// /////////////////////////////////////////////////////////////////////////////
// PLEASE DO NOT RENAME OR REMOVE ANY OF THE CODE BELOW.
// YOU CAN ADD YOUR CODE TO THIS FILE TO EXTEND THE FEATURES TO USE THEM IN YOUR WORK.
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scopic.javachallenge.ServiceClasses.PlayerService;
import com.scopic.javachallenge.errorhandling.InvalidInputException;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerService playerService;

    @GetMapping("/player")
    public ResponseEntity<List<Player>> index() {
        return playerService.getAll();
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Optional<Player>> show(@PathVariable final Long id) {
        return playerService.getPlayer(id);
    }

    @PostMapping("/player")
    public ResponseEntity<Object> create(@RequestBody Player newPlayer) {
		List<PlayerSkill> playerSkills = newPlayer.getPlayerSkills();
    	return playerService.createPlayer(newPlayer, playerSkills);
        
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Object> update(@PathVariable final Long id, @RequestBody Player player) {
        try {
			return playerService.updatePlayer(id, player);
		} catch (InvalidInputException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Object> delete(@PathVariable final Long id, HttpServletResponse response) {
    	return playerService.deletePlayer(response.getHeader("Authorization"), id);    
    }
}
