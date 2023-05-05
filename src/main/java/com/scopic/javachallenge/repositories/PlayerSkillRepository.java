package com.scopic.javachallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scopic.javachallenge.models.PlayerSkill;

@Repository
public interface PlayerSkillRepository extends JpaRepository<PlayerSkill, Long>{

}
