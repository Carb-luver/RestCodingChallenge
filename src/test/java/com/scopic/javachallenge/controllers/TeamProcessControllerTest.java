// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.CreateTeamRequest;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

class TeamProcessControllerTest extends BasePlayerControllerTest {
	
	
    @Test
    public void testSample() throws Exception {
    	
    	Player player1 = new Player("player1", PlayerPosition.FORWARD, List.of(new PlayerSkill(Skill.SPEED, 60)));
    	Player createdPlayer1 = playerRepository.save(player1);
    	createdPlayer1.getPlayerSkills().clear();
    	createdPlayer1.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(createdPlayer1));
    	skillDao.saveAll(createdPlayer1.getPlayerSkills());
    	
    	Player createdPlayer2 = new Player("player2", PlayerPosition.MIDFIELDER, List.of(new PlayerSkill(Skill.DEFENSE, 60)));
    	playerRepository.save(createdPlayer2);
    	playerRepository.save(createdPlayer2);
    	createdPlayer2.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(createdPlayer2));
    	skillDao.saveAll(createdPlayer2.getPlayerSkills());
    	
    	Player createdPlayer3 = new Player("player3", PlayerPosition.DEFENDER, List.of(new PlayerSkill(Skill.STRENGTH, 60)));
    	playerRepository.save(createdPlayer3);
    	playerRepository.save(createdPlayer3);
    	createdPlayer3.getPlayerSkills().stream().forEach(skill -> skill.setPlayer(createdPlayer3));
    	skillDao.saveAll(createdPlayer3.getPlayerSkills());
    	 	
    	List<CreateTeamRequest> requestList = List.of(
    			new CreateTeamRequest(PlayerPosition.FORWARD, Skill.SPEED),
    			new CreateTeamRequest(PlayerPosition.DEFENDER, Skill.DEFENSE));
    	
    	
    	
    	mvc.perform(
        		MockMvcRequestBuilders
                .post(TEAM_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestList)))
        		.andDo(print())
        		.andExpect(content().string(containsString("player1")))
        		.andReturn();
    }
}