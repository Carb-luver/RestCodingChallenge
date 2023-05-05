// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

public class PlayerControllerDeleteTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
        
        PlayerSkill playerSkill = new PlayerSkill(Skill.SPEED, 60);
    	Player player = new Player("player1", PlayerPosition.DEFENDER, List.of(playerSkill));
    	playerSkill.setPlayer(player);
    	skillDao.save(playerSkill);
    	playerRepository.save(player);
    	
        mvc.perform(MockMvcRequestBuilders
        		.delete("/player/{id}", 1)
        	      .header("Authorization", "Bearer SkFabTZibXE1aE14ckpQUUxHc2dnQ2RzdlFRTTM2NFE2cGI4d3RQNjZmdEFITmdBQkE=")
        	      .accept(MediaType.APPLICATION_JSON)
        	      .param("user", "user"))
        	      .andExpect(status().isAccepted())
        	      .andDo(print())
                  .andReturn();
        

    }
}
