// /////////////////////////////////////////////////////////////////////////////
// PLEASE DO NOT RENAME OR REMOVE ANY OF THE CODE BELOW.
// YOU CAN ADD YOUR CODE TO THIS FILE TO EXTEND THE FEATURES TO USE THEM IN YOUR WORK.
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scopic.javachallenge.ServiceClasses.TeamProcessService;
import com.scopic.javachallenge.models.CreateTeamRequest;


@RestController
public class TeamProcessController {
	
	@Autowired
	TeamProcessService teamProcessService;

    @PostMapping("/team/process")
    public ResponseEntity<List<Object>> create(@RequestBody List<CreateTeamRequest> request) {
    	  return teamProcessService.createTeam(request);
    }
}
