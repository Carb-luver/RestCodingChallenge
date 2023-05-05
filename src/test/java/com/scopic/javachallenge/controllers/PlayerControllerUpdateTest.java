// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

public class PlayerControllerUpdateTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
    	
    	PlayerSkill playerSkill = new PlayerSkill(Skill.SPEED, 60);
    	Player player = new Player("player1", PlayerPosition.DEFENDER, List.of(playerSkill));
    	playerRepository.save(player);
    	
    	player.setName("Mark");
    	
    	mvc.perform(
        		MockMvcRequestBuilders
                .put("/player/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(player)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mark"));
    	
    }
}
