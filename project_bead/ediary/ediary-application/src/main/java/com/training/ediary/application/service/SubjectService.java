package com.training.ediary.application.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.Subject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.repository.SubjectRepo;
import com.training.ediary.domain.repository.TeachingRepo;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepo subjectRepo;
	
	@Autowired
	private TeachingRepo teachingRepo;
	
	@Autowired
	private EdiaryUserService ediaryUserService;
	
	public Optional<Subject> selectedSubject(int selectSubject){
		return subjectRepo.findById(selectSubject);
	}
	
	public List<Subject> teacherSubjectList(HttpServletRequest request){
		if(ediaryUserService.loginUser(request) != null)
		{
			return teachingRepo.findTeachersSubjects((Teacher)ediaryUserService.loginUser(request));
		}
		return null;
	}
}
