// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

public class PlayerControllerListingTest extends BasePlayerControllerTest {
	
    @Test
    public void getAllPlayersTest(){
    	
    	PlayerSkill playerSkill = new PlayerSkill(Skill.SPEED, 60);
    	Player player = new Player("player1", PlayerPosition.DEFENDER, List.of(playerSkill));
    	playerRepository.save(player);
    	
    	try {
			ResultActions result = mvc.perform( 
					MockMvcRequestBuilders
					  .get("/player")
					  .accept(MediaType.APPLICATION_JSON))
				      .andDo(print())
				      .andExpect(status().isOk())
				      .andExpect(content().string(containsString("player1")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @Test
    public void getPlayerTest() {
    	
    	PlayerSkill playerSkill = new PlayerSkill(Skill.SPEED, 60);
    	Player player = new Player("player1", PlayerPosition.DEFENDER, List.of(playerSkill));
    	playerRepository.save(player);
    	
    	try {
			mvc.perform( 
				MockMvcRequestBuilders
				  .get("/player/{id}", 1)
				  .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
