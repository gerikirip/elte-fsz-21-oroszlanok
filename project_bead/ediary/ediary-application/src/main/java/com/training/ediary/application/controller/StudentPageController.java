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
import com.training.ediary.application.transform.view.SchoolYearViewTransform;
import com.training.ediary.application.transform.view.TakingSubjectViewTransform;



@Controller
public class StudentPageController {
	
	@Autowired
	private SchoolYearService schoolYearService;
	
	@Autowired
	private TakingSubjectService takingSubjectService;
	
	@Autowired
	private TakingSubjectViewTransform takingSubjectViewTransform;
	
	@Autowired
	private SchoolYearViewTransform schoolYearViewTransform;
	
	@GetMapping("/studentPage")
	public String studentPage(Model model) {
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		return "studentView/studentPage";
	}
	@GetMapping("/studentPage/studentData")
    public String studentData(Model model) {
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
        return "studentView/studentData";
    }
	@PostMapping("/studentPage/studentData")
	public String studentPageWithMark2(@RequestParam int selectYear, Model model, HttpServletRequest request) {	

		model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.takingSubjectsByStudent(request, selectYear)));		
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("selectedYear",selectYear);
		return "studentView/studentData";
	}
	
	@GetMapping("/studentPage/studentAbsent")
    public String studentAbsent(Model model) {
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
        return "studentView/studentAbsent";
    }
	@PostMapping("/studentPage/studentAbsent")
	public String studentPageWithMark3(@RequestParam int selectYear, Model model, HttpServletRequest request) {	

		model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.takingSubjectsByStudent(request, selectYear)));		
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("selectedYear",selectYear);
		return "studentView/studentAbsent";
	}
	
	@PostMapping("/studentPage")
	public String studentPageWithMark(@RequestParam int selectYear, Model model, HttpServletRequest request) {	

		model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.takingSubjectsByStudent(request, selectYear)));		
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("selectedYear",selectYear);
		return "studentView/studentPage";
	}
}
