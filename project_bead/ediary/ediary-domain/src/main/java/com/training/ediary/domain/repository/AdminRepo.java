package com.training.ediary.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{

}
