package com.training.ediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Integer>{

}
