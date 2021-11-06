package com.training.ediary.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.application.service.EdiaryUserService;
import com.training.ediary.application.service.InClassService;
import com.training.ediary.application.service.MarkService;
import com.training.ediary.application.service.SchoolClassService;
import com.training.ediary.application.service.SchoolYearService;
import com.training.ediary.application.service.TakingSubjectService;
import com.training.ediary.application.service.TeachingService;
import com.training.ediary.domain.Mark;
import com.training.ediary.domain.Student;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.repository.MarkRepo;
import com.training.ediary.repository.TakingSubjectRepo;


@Controller
public class TeacherPageController {
	
	@Autowired
	private TakingSubjectService takingSubjectService;
	
	@Autowired
	private InClassService inClassService;
	
	@Autowired
	private SchoolYearService schoolYearService;
	
	@Autowired
	private TeachingService teachingService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@GetMapping("/teacherPage")
	public String teacherPage(Model model, HttpServletRequest request) {
		
		model.addAttribute("alert",null);	
		model.addAttribute("teacherSubjects",teachingService.teacherSubjectList(request));
		model.addAttribute("schoolYears",schoolYearService.schoolYears());
		model.addAttribute("schoolClasses",schoolClassService.schoolClasses());
		return "teacherView/teacherPage";
	}
	
	@GetMapping("/teacherPage/succesfullchange")
	public String teacherPageWithAlert(Model model, HttpServletRequest request) {
		
		model.addAttribute("alert","Sikeres mentés!");	
		model.addAttribute("teacherSubjects",teachingService.teacherSubjectList(request));
		model.addAttribute("schoolYears",schoolYearService.schoolYears());
		model.addAttribute("schoolClasses",schoolClassService.schoolClasses());
		return "teacherView/teacherPage";
	}
	
	@PostMapping("/teacherPage")
	public String teacherPageWithMarks(@RequestParam int selectYear, @RequestParam int selectSubject, @RequestParam int selectSchoolClass, Model model, HttpServletRequest request) {	
		List<Student> studentList = inClassService.studentListBySchoolClass(request, selectYear, selectSchoolClass);		
		List<TakingSubject> takingSubjects = takingSubjectService.findBySubjectAndSchoolYearAndTeacher(request, selectSubject, selectYear);
		List<TakingSubject> takingSubjectFiltered = takingSubjectService.takingSubjectFiltered(studentList, takingSubjects);
		
		model.addAttribute("takingSubjects",takingSubjectFiltered);
		model.addAttribute("teacherSubjects",teachingService.teacherSubjectList(request));
		model.addAttribute("selectedSubject", selectSubject);
		model.addAttribute("schoolYears",schoolYearService.schoolYears());
		model.addAttribute("selectedYear",selectYear);
		model.addAttribute("schoolClasses",schoolClassService.schoolClasses());
		model.addAttribute("selectedSchoolClass",selectSchoolClass);
		return "teacherView/teacherPage";
	}
}