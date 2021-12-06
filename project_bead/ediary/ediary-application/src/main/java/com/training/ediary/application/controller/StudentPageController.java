package com.training.ediary.application.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.training.ediary.application.webdomain.request.SchoolYearFormRequest;
import com.training.ediary.application.webdomain.view.SchoolYearView;
import com.training.ediary.application.webdomain.view.TakingSubjectView;



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
	
	@GetMapping("/studentPage/studentAbsent")
    public String studentAbsent(Model model, HttpSession session, HttpServletRequest request) {
		Integer choosenYearsAbsent = (Integer)session.getAttribute("choosenYearsAbsent");
		
		if(choosenYearsAbsent != null)
		{
			model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.takingSubjectsByStudent(request, choosenYearsAbsent)));		
		}	
		
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
        return "studentView/studentAbsent";
    }
	@PostMapping("/studentPage/studentAbsent")
	public String studentPageWithAbsents(SchoolYearFormRequest schoolYearFormRequest, Model model, HttpServletRequest request, HttpSession session) {	
		session.setAttribute("choosenYearsAbsent", schoolYearFormRequest.getSelectYear());	
		model.addAttribute("choosenYearsAbsent",(int)session.getAttribute("choosenYearsAbsent"));
		model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.takingSubjectsByStudent(request, schoolYearFormRequest.getSelectYear())));		
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("selectedYear",schoolYearFormRequest.getSelectYear());
		return "studentView/studentAbsent";
	}
	
	@GetMapping("/studentPage")
	public String studentPage(Model model, HttpSession session, HttpServletRequest request) {
		Integer choosenYears = (Integer)session.getAttribute("choosenYears");
		
		if(choosenYears != null)
		{
			model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.takingSubjectsByStudent(request, choosenYears)));		
		}	
		
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("choosenYears", choosenYears);
		return "studentView/studentPage";
	}
	
	@PostMapping("/studentPage")
	public String studentPageWithMark(SchoolYearFormRequest schoolYearFormRequest, Model model, HttpServletRequest request, HttpSession session) {			
		session.setAttribute("choosenYears", schoolYearFormRequest.getSelectYear());	
		model.addAttribute("choosenYears",(int)session.getAttribute("choosenYears"));
		model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.takingSubjectsByStudent(request, schoolYearFormRequest.getSelectYear())));		
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("selectedYear",schoolYearFormRequest.getSelectYear());

		return "studentView/studentPage";
	}
}
