package com.training.ediary.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.domain.Student;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.repository.InClassRepo;
import com.training.ediary.repository.TakingSubjectRepo;


@Controller
public class TeacherPageController {
	
	@Autowired
	private UniversalController universalController;
	
	@Autowired
	private TakingSubjectRepo takingSubjectRepo;
	
	@Autowired
	private InClassRepo inClassRepo;
	
	@GetMapping("/teacherPage")
	public String form(Model model, HttpServletRequest request) {
		model.addAttribute("teacherSubjects",universalController.teacherSubjectList(request));
		model.addAttribute("schoolYears",universalController.schoolYears());
		model.addAttribute("schoolClasses",universalController.schoolClasses());
		return "teacherView/teacherPage";
	}
	
	@PostMapping("/teacherPage")
	public String formYear(@RequestParam int selectYear, @RequestParam int selectSubject, @RequestParam int selectSchoolClass, Model model, HttpServletRequest request) {
		
		if(universalController.selectedYear(selectYear).isPresent() && universalController.selectedSubject(selectSubject).isPresent() && universalController.selectedSchoolClass(selectSchoolClass).isPresent() && universalController.loginUser(request) != null)
		{
			List<Student> studentList = inClassRepo.findStudentsInClass(universalController.selectedYear(selectYear).get(),universalController.selectedSchoolClass(selectSchoolClass).get()); 		
			List<TakingSubject> takingSubjects = takingSubjectRepo.findBySubjectAndSchoolYearAndTeacher(universalController.selectedSubject(selectSubject).get(), universalController.selectedYear(selectYear).get(), (Teacher)universalController.loginUser(request));		
			List<TakingSubject> takingSubjectFiltered = new ArrayList<>();
			for(TakingSubject takingSubject : takingSubjects)
			{
				if(studentList.contains(takingSubject.getStudent()))
				{
					takingSubjectFiltered.add(takingSubject);
				}
			}
			model.addAttribute("takingSubjects",takingSubjectFiltered);
		}
		model.addAttribute("teacherSubjects",universalController.teacherSubjectList(request));
		model.addAttribute("selectedSubject", selectSubject);
		model.addAttribute("schoolYears",universalController.schoolYears());
		model.addAttribute("selectedYear",selectYear);
		model.addAttribute("schoolClasses",universalController.schoolClasses());
		model.addAttribute("selectedSchoolClass",selectSchoolClass);
		return "teacherView/teacherPage";
	}
}