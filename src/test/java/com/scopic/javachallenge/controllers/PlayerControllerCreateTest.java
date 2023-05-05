// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

class PlayerControllerCreateTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
    	
    	PlayerSkill playerSkill = new PlayerSkill(Skill.SPEED, 60);
    	Player player = new Player("player1", PlayerPosition.DEFENDER, List.of(playerSkill));
    	//Player createdPlayer = playerRepository.save(player);
    	//playerSkill.setPlayer(createdPlayer);

    	mvc.perform(
        		MockMvcRequestBuilders
                .post(PLAYER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(player)))
        		.andDo(print())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
        		.andReturn();
        
    }
    
    
    
}