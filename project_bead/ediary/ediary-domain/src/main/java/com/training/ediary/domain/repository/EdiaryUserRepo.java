package com.training.ediary.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.EdiaryUser;

@Repository
public interface EdiaryUserRepo extends JpaRepository<EdiaryUser, Integer>{
	
	EdiaryUser findByUsername(String username);
}
