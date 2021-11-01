package com.training.ediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer>{
	
}	
