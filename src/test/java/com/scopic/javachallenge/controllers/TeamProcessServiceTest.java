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
    	Player createdPlayer1 = playerRepository.save(player1);
    	createdPlayer1.getPlayerSkills().clear();
    	createdPlayer1.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(createdPlayer1));
    	skillDao.saveAll(createdPlayer1.getPlayerSkills());
    	
    	Player player2 = new Player("player2", PlayerPosition.MIDFIELDER, List.of(new PlayerSkill(Skill.DEFENSE, 60)));
    	Player createdPlayer2 = playerRepository.save(player2);
    	createdPlayer2.getPlayerSkills().clear();
    	createdPlayer2.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(createdPlayer2));
    	skillDao.saveAll(createdPlayer2.getPlayerSkills());
    	
    	Player player3 = new Player("player3", PlayerPosition.DEFENDER, List.of(new PlayerSkill(Skill.STRENGTH, 60)));
    	Player createdPlayer3 = playerRepository.save(player3);
    	createdPlayer3.getPlayerSkills().clear();
    	createdPlayer3.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(createdPlayer3));
    	skillDao.saveAll(createdPlayer3.getPlayerSkills());
    	
    	
    	
    	CreateTeamRequest request = new CreateTeamRequest(PlayerPosition.DEFENDER, Skill.STRENGTH);
    	 	
    	ResponseEntity<List<Object>> playerTeam = teamProcessService.createTeam(List.of(request));
    	assertTrue(playerTeam.getBody().toString().contains(player3.getName().toString()));
    }
}