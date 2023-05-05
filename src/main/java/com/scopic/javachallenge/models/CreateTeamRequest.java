package com.scopic.javachallenge.models;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;

public class CreateTeamRequest {
	
	Skill skill;
	PlayerPosition position;

	public CreateTeamRequest(PlayerPosition position, Skill skill) {
		this.skill = skill;
		this.position = position;
	}
	
	
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public PlayerPosition getPosition() {
		return position;
	}


	public void setPosition(PlayerPosition position) {
		this.position = position;
	}
	
	

}
