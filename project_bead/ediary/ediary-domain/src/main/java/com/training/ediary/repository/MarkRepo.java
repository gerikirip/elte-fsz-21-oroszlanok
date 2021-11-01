package com.training.ediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.Mark;

@Repository
public interface MarkRepo extends JpaRepository<Mark, Integer>{
	
}
