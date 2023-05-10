package com.scopic.javachallenge.ServiceClasses;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.errorhandling.InvalidInputException;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;
import com.scopic.javachallenge.repositories.PlayerRepository;
import com.scopic.javachallenge.repositories.PlayerSkillRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	PlayerSkillRepository skillDao;
	
	public ResponseEntity<List<Player>> getAll(){
		return new ResponseEntity<List<Player>>(playerRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Object> createPlayer(Player player, List<PlayerSkill> playerSkills) {
		try {
			errorHandling(player);
		} catch (InvalidInputException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		Player createdPlayer = playerRepository.save(player);
		createdPlayer.getPlayerSkills().clear();
		playerSkills.stream().forEach(skill -> skill.setPlayer(createdPlayer));
		skillDao.saveAll(playerSkills);
		return new ResponseEntity<Object>(createdPlayer, HttpStatus.CREATED);
	}

	public ResponseEntity<Optional<Player>> getPlayer(Long id) {
		Optional<Player> player = playerRepository.findById(id);
		return new ResponseEntity<Optional<Player>>(player, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> updatePlayer(Long id, Player updatedPlayer) {
		try {
			errorHandling(updatedPlayer);
		} catch (InvalidInputException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		Optional<Player> outdatedPlayer = playerRepository.findById(id);
		
		if(!outdatedPlayer.isEmpty()) {
			playerRepository.deleteById(id);
		}
		
		updatedPlayer.setId(id);
		Player player = playerRepository.save(updatedPlayer);
		player.getPlayerSkills().clear();
		updatedPlayer.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(player));
		skillDao.saveAll(updatedPlayer.getPlayerSkills());
		return new ResponseEntity<Object>(player, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<Object> deletePlayer(String token, Long id) {
		if (token != null && token.contentEquals("Bearer SkFabTZibXE1aE14ckpQUUxHc2dnQ2RzdlFRTTM2NFE2cGI4d3RQNjZmdEFITmdBQkE=")) {
			playerRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	private void errorHandling(Player player) throws InvalidInputException { 
		for (int i =0; i<(player.getPlayerSkills().size()); i++) {
			if(player.getPlayerSkills().get(i).getValue() > 100) {
				throw new InvalidInputException("Player skill value may not exceed 100: "  + 
						player.getPlayerSkills().get(i).getSkill() + " " + player.getPlayerSkills().get(i).getValue());
			} else if (player.getPlayerSkills().get(i).getValue() < 0) {
				throw new InvalidInputException("Player skill minimum value is 0: " + 
						player.getPlayerSkills().get(i).getSkill() + " " + player.getPlayerSkills().get(i).getValue());
			}
		} if(!player.getPosition().equals(PlayerPosition.DEFENDER) && !player.getPosition().equals(PlayerPosition.FORWARD)
				&& !player.getPosition().equals(PlayerPosition.MIDFIELDER)) {
			throw new InvalidInputException("Invalid value for position " + player.getPosition());
		} if (player.getPlayerSkills().isEmpty()) {
			throw new InvalidInputException("Player skills is a required field");
		}
	}

}
