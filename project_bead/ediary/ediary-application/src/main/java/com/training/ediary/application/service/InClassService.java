package com.training.ediary.application.service;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.Student;
import com.training.ediary.repository.InClassRepo;


@Service
public class InClassService {
	
	@Autowired
	private InClassRepo inClassRepo;
	
	@Autowired
	private SchoolYearService schoolYearService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	public List<Student> studentListBySchoolClass(HttpServletRequest request, int selectYear, int selectSchoolClass){
		if(schoolYearService.selectedYear(selectYear).isPresent() && schoolClassService.selectedSchoolClass(selectSchoolClass).isPresent())
		{
			return inClassRepo.findStudentsInClass(schoolYearService.selectedYear(selectYear).get(),schoolClassService.selectedSchoolClass(selectSchoolClass).get()); 
		}
		return null;
	}
}
