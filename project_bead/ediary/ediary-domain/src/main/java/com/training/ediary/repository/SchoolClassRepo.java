package com.training.ediary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.SchoolClass;

@Repository
public interface SchoolClassRepo extends JpaRepository<SchoolClass, Integer>{

}
