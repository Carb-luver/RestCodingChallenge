// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.scopic.javachallenge.ServiceClasses.TeamProcessService;
import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.CreateTeamRequest;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

class TeamProcessServiceTest extends BasePlayerControllerTest {
	
	@Autowired
	TeamProcessService teamProcessService;
	
    @Test
    public void testSample() throws Exception {
    	Player player1 = new Player("player1", PlayerPosition.FORWARD, List.of(new PlayerSkill(Skill.SPEED, 60)));
    	playerRepository.save(player1);
    	player1.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(player1));
    	skillDao.saveAll(player1.getPlayerSkills());
    	Player player2 = new Player("player2", PlayerPosition.MIDFIELDER, List.of(new PlayerSkill(Skill.DEFENSE, 60)));
    	playerRepository.save(player2);
    	playerRepository.save(player2);
    	player2.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(player1));
    	skillDao.saveAll(player2.getPlayerSkills());
    	Player player3 = new Player("player3", PlayerPosition.DEFENDER, List.of(new PlayerSkill(Skill.STRENGTH, 60)));
    	playerRepository.save(player3);
    	playerRepository.save(player3);
    	player3.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(player3));
    	skillDao.saveAll(player3.getPlayerSkills());
    	
    	
    	
    	CreateTeamRequest request = new CreateTeamRequest(PlayerPosition.DEFENDER, Skill.STRENGTH);
    	 	
    	ResponseEntity<List<Object>> playerTeam = teamProcessService.createTeam(List.of(request));
    	assertTrue(playerTeam.getBody().toString().contains(player3.getName().toString()));
    }
}