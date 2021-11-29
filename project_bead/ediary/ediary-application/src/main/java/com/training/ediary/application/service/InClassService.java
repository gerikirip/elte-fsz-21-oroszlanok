package com.training.ediary.application.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.InClass;
import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.repository.InClassRepo;



@Service
public class InClassService {
	
	@Autowired
	EdiaryUserService ediaryUserService;
	
	@Autowired
	SchoolYearService schoolYearService;
	
	@Autowired
	InClassRepo inClassRepo;
	
	public List<InClass> getInClassesByHeadTeacher(HttpServletRequest request){
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		return inClassRepo.findByHeadTeacher(loginTeacher);
	}
	
	public List<InClass> getInClassesByHeadTeacherBySchoolYear(HttpServletRequest request, int schoolYearId){
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		Optional<SchoolYear> schoolYear = schoolYearService.selectedYear(schoolYearId);
		if(schoolYear.isPresent())
		{
			return inClassRepo.findByHeadTeacherAndSchoolYear(loginTeacher, schoolYear.get());
		}
		return null;
	}
}
