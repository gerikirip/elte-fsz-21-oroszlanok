package com.training.ediary.application.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.application.service.SchoolYearService;
import com.training.ediary.application.service.TakingSubjectService;



@Controller
public class StudentPageController {
	
	@Autowired
	private SchoolYearService schoolYearService;
	
	@Autowired
	private TakingSubjectService takingSubjectService;
	
	@GetMapping("/studentPage")
	public String studentPage(Model model) {
		model.addAttribute("schoolYears",schoolYearService.schoolYears());
		return "studentView/studentPage";
	}
	
	@PostMapping("/studentPage")
	public String studentPageWithMark(@RequestParam int selectYear, Model model, HttpServletRequest request) {	

		model.addAttribute("takingSubjects", takingSubjectService.takingSubjectsByStudent(request, selectYear));		
		model.addAttribute("schoolYears",schoolYearService.schoolYears());
		model.addAttribute("selectedYear",selectYear);
		return "studentView/studentPage";
	}
}
