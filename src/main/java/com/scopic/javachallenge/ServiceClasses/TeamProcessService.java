package com.scopic.javachallenge.ServiceClasses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scopic.javachallenge.errorhandling.InvalidInputException;
import com.scopic.javachallenge.models.CreateTeamRequest;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.repositories.PlayerRepository;

@Service
public class TeamProcessService {

	@Autowired
	PlayerRepository playerDao;

	public ResponseEntity<List<Object>> createTeam(List<CreateTeamRequest> teamRequestList) {
		try {
			checkForDups(teamRequestList);
		} catch (InvalidInputException e) {
			return new ResponseEntity<List<Object>>(List.of(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
		List<Object> team = new ArrayList<>();
		Player player;
		
		for(int i =0; i<teamRequestList.size(); i++) {
			player = null;
			CreateTeamRequest teamRequest = teamRequestList.get(i);
			player = playerDao.fetchPlayerByPositionAndSkill(teamRequest.getPosition(), teamRequest.getSkill());
			if(player == null) {
				player = playerDao.fetchPlayerByPosition(teamRequest.getPosition());
			}
			
			try {
				if(player == null) {
					throw new InvalidInputException("No players available for position: " + teamRequest.getPosition());
				}
			} catch (InvalidInputException e) {
				return new ResponseEntity<List<Object>>(List.of(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
			
			team.add(player);

		}
				
		return new ResponseEntity<List<Object>>(team, HttpStatus.CREATED);
	}

	private void checkForDups(List<CreateTeamRequest> teamRequestList) throws InvalidInputException {
		for (int i = 0; i<teamRequestList.size(); i++) {
			for(int x = 0; x<teamRequestList.size();x++) {
				if(i != x) {
					if((teamRequestList.get(i).getPosition() == teamRequestList.get(x).getPosition())
							&& (teamRequestList.get(i).getSkill() == teamRequestList.get(x).getSkill())) {
						throw new InvalidInputException("You cannot request more than one player with the same position and skill.");
					}
				}
			}
		}
	}

}
