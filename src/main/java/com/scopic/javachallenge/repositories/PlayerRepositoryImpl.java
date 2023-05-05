package com.scopic.javachallenge.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;

public class PlayerRepositoryImpl implements CustomPlayerDao{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Player fetchPlayerByPositionAndSkill(PlayerPosition position, Skill skill){
		List<Player> players = em.createQuery("SELECT p FROM Player p, PlayerSkill ps "
				+ "WHERE p.position = :position AND ps.player = p AND ps.skill = :skill")
				.setParameter("position", position)
				.setParameter("skill", skill)
				.getResultList();
		
		Player maxPlayer = null;
		int maxValue = 0;
		
		for(int i = 0; i<players.size(); i++) {
			Player currentPlayer = players.get(i);
			List<PlayerSkill> ps = currentPlayer.getPlayerSkills();
			for(int x = 0; x<ps.size(); x++) {
				if(ps.get(x).getSkill().equals(skill)) {
					if(ps.get(x).getValue()>maxValue) {
						maxPlayer = currentPlayer;
						maxValue = ps.get(x).getValue();
					}
				}
			}
		}
		
		return maxPlayer;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Player fetchPlayerByPosition(PlayerPosition position){
		List<Player> players = em.createQuery("SELECT p FROM Player p WHERE p.position = :position")
				.setParameter("position", position)
				.getResultList();
	
		Player maxPlayer = null;
		int maxValue = 0;
		
		for(int i = 0; i<players.size(); i++) {
			Player currentPlayer = players.get(i);
			List<PlayerSkill> ps = currentPlayer.getPlayerSkills();
			for(int x = 0; x<ps.size() ;x++) {
				if(ps.get(x).getValue()>maxValue) {
					maxPlayer = currentPlayer;
					maxValue = ps.get(x).getValue();
				}
				
			}
		}
		
		return maxPlayer;
	}
}
