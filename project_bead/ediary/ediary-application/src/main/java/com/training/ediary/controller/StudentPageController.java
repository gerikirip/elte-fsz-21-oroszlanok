package com.training.ediary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.domain.Student;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.repository.TakingSubjectRepo;



@Controller
public class StudentPageController {
	
	@Autowired
	private TakingSubjectRepo takingSubjectRepo;
	
	@Autowired
	private UniversalController universalController;
	
	@GetMapping("/studentPage")
	public String form(Model model) {
		model.addAttribute("schoolYears",universalController.schoolYears());
		return "studentView/studentPage";
	}
	
	@PostMapping("/studentPage")
	public String formYear(@RequestParam int selectYear, Model model, HttpServletRequest request) {
		if(universalController.selectedYear(selectYear).isPresent() && universalController.loginUser(request) != null)
		{
			List<TakingSubject> takingSubjects = takingSubjectRepo.findBySchoolYearAndStudent(universalController.selectedYear(selectYear).get(), (Student)universalController.loginUser(request));
			  
			model.addAttribute("takingSubjects",takingSubjects);
		}
		model.addAttribute("schoolYears",universalController.schoolYears());
		model.addAttribute("selectedYear",selectYear);
		return "studentView/studentPage";
	}
}
