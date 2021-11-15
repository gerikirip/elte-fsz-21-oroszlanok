package com.training.ediary.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.SchoolYear;

@Repository
public interface SchoolYearRepo extends JpaRepository<SchoolYear, Integer>{

}
