package com.training.ediary.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.ediary.application.service.AbsentService;
import com.training.ediary.application.service.InClassService;
import com.training.ediary.application.service.SchoolYearService;
import com.training.ediary.application.service.TakingSubjectService;
import com.training.ediary.application.transform.view.InClassViewTransform;
import com.training.ediary.application.transform.view.SchoolYearViewTransform;
import com.training.ediary.application.transform.view.TakingSubjectViewTransform;
import com.training.ediary.application.webdomain.request.AbsentAddRequest;
import com.training.ediary.application.webdomain.request.GetInClassRequest;

@Controller
public class AbsentController {
	
	@Autowired
	AbsentService absentService;
	
	@Autowired
	SchoolYearService schoolYearService;
	
	@Autowired
	InClassService inClassService;
	
	@Autowired
	SchoolYearViewTransform schoolYearViewTransform;
	
	@Autowired
	InClassViewTransform inClassViewTransform;
	
	@Autowired
	TakingSubjectViewTransform takingSubjectViewTransform;
	
	@Autowired
	TakingSubjectService takingSubjectService;
	
	@GetMapping("/teacherPage/teacherAbsent/{id}")
	public String addAbsentView(Model model, @PathVariable(name="id") int takingSubjectId, HttpServletRequest request) {
		return absentService.addAbsentView(model, takingSubjectId, request);
	}
	
	@PostMapping("/teacherPage/teacherAbsent")
	public String markAdd(AbsentAddRequest absentAddRequest, HttpServletRequest request, RedirectAttributes redirectAttributes){
		return absentService.addAbsent(absentAddRequest, request, redirectAttributes);
	}
	
	@GetMapping("/teacherPage/teacherAuthAbsent")
	public String authAbsentView(Model model, HttpServletRequest request, HttpSession session){
		Integer absentChoosenYear = (Integer)session.getAttribute("absentChoosenYear");
		
		if(absentChoosenYear != null) {
			model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.findBySchoolYearAndTeacher(request, absentChoosenYear)));
		}
		
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("absentChoosenYear", absentChoosenYear);

		
		return "teacherView/teacherAuthAbsent";
	}
	
	@PostMapping("/teacherPage/teacherAuthAbsent")
	public String authAbsentList(Model model, HttpServletRequest request, HttpSession session, GetInClassRequest inClassRequest) {
		
		session.setAttribute("absentChoosenYear", inClassRequest.getSelectYear());
		model.addAttribute("absentChoosenYear",(int)session.getAttribute("absentChoosenYear"));	
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("inClasses", inClassViewTransform.inClassListTransform(inClassService.getInClassesByHeadTeacherBySchoolYear(request, inClassRequest.getSelectYear())));
		model.addAttribute("takingSubjects", takingSubjectViewTransform.takingSubjectListTransform(takingSubjectService.findBySchoolYearAndTeacher(request, inClassRequest.getSelectYear())));
		
		return "teacherView/teacherAuthAbsent";
	}
	
	@GetMapping("/teacherPage/certifyAbsence/{id}")
	public String teacherSuccessYear(@PathVariable(name="id") int id) {
		absentService.certifyAbsence(id);
		return "redirect:/teacherPage/teacherAuthAbsent";
	}
}
