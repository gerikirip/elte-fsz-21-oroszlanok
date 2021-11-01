package com.training.ediary.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.training.ediary.domain.EdiaryUser;
import com.training.ediary.domain.SchoolClass;
import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.Subject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.repository.EdiaryUserRepo;
import com.training.ediary.repository.SchoolClassRepo;
import com.training.ediary.repository.SchoolYearRepo;
import com.training.ediary.repository.SubjectRepo;
import com.training.ediary.repository.TeachingRepo;

@Controller
public class UniversalController {
	
	@Autowired
	private EdiaryUserRepo ediaryUserRepo;
	
	@Autowired
	private SchoolYearRepo schoolYearRepo;
	
	@Autowired
	private SchoolClassRepo schoolClassRepo;
	
	@Autowired
	private TeachingRepo teachingRepo;
	
	@Autowired
	private SubjectRepo subjectRepo;
	
	
	public EdiaryUser loginUser(HttpServletRequest request){
		return ediaryUserRepo.findByUsername(request.getUserPrincipal().getName());
	}
	
	public Optional<SchoolYear> selectedYear(int selectYear){
		return schoolYearRepo.findById(selectYear);
	}
	
	public Optional<Subject> selectedSubject(int selectSubject){
		return subjectRepo.findById(selectSubject);
	}
	
	public Optional<SchoolClass> selectedSchoolClass(int selectSchoolClass){
		return schoolClassRepo.findById(selectSchoolClass);
	}
	
	public List<SchoolYear> schoolYears(){
		return schoolYearRepo.findAll();
	}
	
	public List<SchoolClass> schoolClasses(){
		return schoolClassRepo.findAll();
	}
		
	public List<Subject> teacherSubjectList(HttpServletRequest request){
		return teachingRepo.findTeachersSubjects((Teacher)loginUser(request));
	}
}
