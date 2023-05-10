package com.scopic.javachallenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scopic.javachallenge.models.PlayerSkill;

@Repository
public interface PlayerSkillRepository extends JpaRepository<PlayerSkill, Long>{
	
	@Query("SELECT s FROM PlayerSkill s WHERE s.player.id = :id")
	List<PlayerSkill> findByPlayerId(Long id);
	
	@Query("DELETE FROM PlayerSkill s WHERE s.player.id = :id")
	List<PlayerSkill> deleteByPlayerId(Long id);

}
