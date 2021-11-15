package com.training.ediary.application.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.application.service.MarkService;
import com.training.ediary.application.service.SchoolClassService;
import com.training.ediary.application.service.SchoolYearService;
import com.training.ediary.application.service.SubjectService;
import com.training.ediary.application.service.TakingSubjectService;
import com.training.ediary.domain.TakingSubject;


@Controller
public class TeacherPageController {
	
	@Autowired
	private TakingSubjectService takingSubjectService;
	
	@Autowired
	private SchoolYearService schoolYearService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private MarkService markService;
	
	@GetMapping("/teacherPage")
	public String teacherPage(Model model, HttpServletRequest request) {
		
		model.addAttribute("alert",null);	
		model.addAttribute("teacherSubjects",subjectService.teacherSubjectList(request));
		model.addAttribute("schoolYears",schoolYearService.schoolYears());
		model.addAttribute("schoolClasses",schoolClassService.schoolClasses());
		return "teacherView/teacherPage";
	}
	
	@GetMapping("/teacherPage/succesfullchange")
	public String teacherPageWithAlert(Model model, HttpServletRequest request) {
		
		model.addAttribute("alert","Sikeres mentés!");	
		model.addAttribute("teacherSubjects",subjectService.teacherSubjectList(request));
		model.addAttribute("schoolYears",schoolYearService.schoolYears());
		model.addAttribute("schoolClasses",schoolClassService.schoolClasses());
		return "teacherView/teacherPage";
	}
	
	@PostMapping("/teacherPage")
	public String teacherPageWithMarks(@RequestParam int selectYear, @RequestParam int selectSubject, @RequestParam int selectSchoolClass, Model model, HttpServletRequest request) {	
		List<TakingSubject> takingSubjectFiltered = takingSubjectService.takingSubjectFiltered(request, selectSubject, selectYear, selectSchoolClass);
		
		model.addAttribute("takingSubjects",takingSubjectFiltered);
		model.addAttribute("teacherSubjects",subjectService.teacherSubjectList(request));
		model.addAttribute("selectedSubject", selectSubject);
		model.addAttribute("schoolYears",schoolYearService.schoolYears());
		model.addAttribute("selectedYear",selectYear);
		model.addAttribute("schoolClasses",schoolClassService.schoolClasses());
		model.addAttribute("selectedSchoolClass",selectSchoolClass);
		model.addAttribute("isCurrentSemester", markService.isCurrentSemester(schoolYearService.selectedYear(selectYear).get()));
		
		return "teacherView/teacherPage";
	}
}