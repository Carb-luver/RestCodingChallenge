package com.scopic.javachallenge.repositories;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;

public interface CustomPlayerDao {
	
	public Player fetchPlayerByPosition(PlayerPosition position);

	public Player fetchPlayerByPositionAndSkill(PlayerPosition position, Skill skill);	

}
