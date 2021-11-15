package com.training.ediary.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.repository.SchoolYearRepo;

@Service
public class SchoolYearService {

	@Autowired
	private SchoolYearRepo schoolYearRepo;
	
	public Optional<SchoolYear> selectedYear(int selectYear){
		return schoolYearRepo.findById(selectYear);
	}
	
	public List<SchoolYear> schoolYears(){
		return schoolYearRepo.findAll();
	}
}
