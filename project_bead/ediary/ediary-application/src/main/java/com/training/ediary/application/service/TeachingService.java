package com.training.ediary.application.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.Subject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.repository.TeachingRepo;

@Service
public class TeachingService {

	@Autowired
	private TeachingRepo teachingRepo;
	
	@Autowired
	private EdiaryUserService ediaryUserService;
	
	public List<Subject> teacherSubjectList(HttpServletRequest request){
		if(ediaryUserService.loginUser(request) != null)
		{
			return teachingRepo.findTeachersSubjects((Teacher)ediaryUserService.loginUser(request));
		}
		return null;
	}
}
