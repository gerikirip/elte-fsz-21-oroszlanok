package com.training.ediary.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.application.service.CreateData;
import com.training.ediary.application.service.StudentService;
import com.training.ediary.application.service.SchoolClassService;
import com.training.ediary.application.service.SchoolYearService;
import com.training.ediary.application.transform.view.SchoolClassViewTransform;
import com.training.ediary.application.transform.view.SchoolYearViewTransform;
import com.training.ediary.application.transform.view.SubjectViewTransform;
import com.training.ediary.application.transform.view.TakingSubjectViewTransform;
import com.training.ediary.domain.SchoolClass;
import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.repository.SchoolClassRepo;
import com.training.ediary.domain.repository.SchoolYearRepo;


@Controller
public class AdminPageController {
	
	@Autowired
	SchoolYearRepo schoolYearRepo;
	
	@Autowired
	SchoolClassRepo schoolClassRepo;
	
	@Autowired
	SchoolClassService schoolClassService;
	
	@Autowired
	SchoolYearService schoolYearService;
	
	@Autowired
	CreateData createData;
	
	@Autowired
	private SchoolYearViewTransform schoolYearViewTransform;
	
	@Autowired
	private SchoolClassViewTransform schoolClassViewTransform;
	
	@GetMapping("/adminPage")
	public String adminPage(Model model) {
		return "adminView/adminPage";
	}
	
	@GetMapping("/adminPage/createschoolYear")
	public String schoolYear(Model model) {
		model.addAttribute("schoolYears",schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		return "adminView/createschoolYear";
	}
	
	@PostMapping("/adminPage/createschoolYear")
	public String schoolYearCreate(Model model, @RequestParam int startYear, @RequestParam int endYear) {
		SchoolYear schoolYear = createData.createSchoolYear(startYear, endYear);
        schoolYearRepo.save(schoolYear);
        model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		return "adminView/createschoolYear";
	}
	
	@GetMapping("/adminPage/createSchoolClass")
	public String schoolClass(Model model) {
		model.addAttribute("schoolClasses",schoolClassService.schoolClasses());
		return "adminView/createSchoolClass";
	}
	
	@PostMapping("/adminPage/createSchoolClass")
	public String schoolClassCreate(Model model, @RequestParam int classNumber, @RequestParam String classText) {
		SchoolClass schoolClass = createData.createSchoolClass(classNumber + "/" + classText);
		schoolClassRepo.save(schoolClass);
		model.addAttribute("schoolClasses",schoolClassViewTransform.schoolClassListTransform(schoolClassService.schoolClasses()));
		return "adminView/createSchoolClass";
	}
	
	@GetMapping("/adminPage/createTeacher")
	public String viewTeacher(Model model) {
		
		return "adminView/createTeacher";
	}
	

	
	
}
