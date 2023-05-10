package com.scopic.javachallenge.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.errorhandling.InvalidInputException;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

public class ExceptionHandlerTest extends BasePlayerControllerTest{
	
	@Test
    public void testInvalidSkillValue() throws Exception {
    	
    	PlayerSkill playerSkill = new PlayerSkill(Skill.SPEED, 101);
    	Player player = new Player("player1", PlayerPosition.DEFENDER, List.of(playerSkill));
        MvcResult result = mvc.perform(
        		MockMvcRequestBuilders
                .post(PLAYER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(player)))
        		.andDo(print())
        		.andExpect(status().isBadRequest())
        		.andReturn();
        
        InvalidInputException ex= new InvalidInputException("Player skill value may not exceed 100: SPEED 101");
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedResponseBody = objectMapper.writeValueAsString(ex);
        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody.contains(expectedResponseBody));
	
	}

}
