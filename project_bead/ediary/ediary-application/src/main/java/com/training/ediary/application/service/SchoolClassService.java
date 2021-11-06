package com.training.ediary.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.SchoolClass;
import com.training.ediary.repository.SchoolClassRepo;

@Service
public class SchoolClassService {
	
	@Autowired
	SchoolClassRepo schoolClassRepo;

	public Optional<SchoolClass> selectedSchoolClass(int selectSchoolClass){
		return schoolClassRepo.findById(selectSchoolClass);
	}
	
	public List<SchoolClass> schoolClasses(){
		return schoolClassRepo.findAll();
	}
}
