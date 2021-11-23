package com.training.ediary.application.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.training.ediary.application.service.EdiaryService;
import com.training.ediary.application.service.SchoolClassService;
import com.training.ediary.application.service.SchoolYearService;
import com.training.ediary.application.service.SubjectService;
import com.training.ediary.application.service.TakingSubjectService;
import com.training.ediary.application.transform.view.SchoolClassViewTransform;
import com.training.ediary.application.transform.view.SchoolYearViewTransform;
import com.training.ediary.application.transform.view.SubjectViewTransform;
import com.training.ediary.application.transform.view.TakingSubjectViewTransform;
import com.training.ediary.application.webdomain.request.TakingSubjectFormRequest;
import com.training.ediary.application.webdomain.view.TakingSubjectView;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.repository.TakingSubjectRepo;


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
	private EdiaryService ediaryService;
	
	@Autowired
	private TakingSubjectViewTransform takingSubjectViewTransform;
	
	@Autowired
	private SubjectViewTransform subjectViewTransform;
	
	@Autowired
	private SchoolYearViewTransform schoolYearViewTransform;
	
	@Autowired
	private SchoolClassViewTransform schoolClassViewTransform;
	
	@GetMapping("/teacherPage")
	public String teacherPage(Model model, HttpServletRequest request, HttpSession session) {
		Integer choosenSubject = (Integer)session.getAttribute("choosenSubject");
		Integer choosenYears = (Integer)session.getAttribute("choosenYears");
		Integer choosenClass = (Integer)session.getAttribute("choosenClass");
		if(choosenSubject != null && choosenYears != null && choosenClass != null)
		{
			List<TakingSubjectView> takingSubjectFiltered = takingSubjectViewTransform.
					takingSubjectListTransform(takingSubjectService.
							takingSubjectFiltered(request, choosenSubject, choosenYears, choosenClass));				
			model.addAttribute("takingSubjects",takingSubjectFiltered);
			model.addAttribute("isCurrentSemester", ediaryService.isCurrentSemester(schoolYearService.selectedYear(choosenYears).get()));
		}		
		model.addAttribute("alert",null);	
		model.addAttribute("teacherSubjects",subjectViewTransform.subjectListTransform(subjectService.teacherSubjectList(request)));
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("schoolClasses", schoolClassViewTransform.schoolClassListTransform(schoolClassService.schoolClasses()));
		
		model.addAttribute("choosenSubject",choosenSubject);
		model.addAttribute("choosenYears", choosenYears);
		model.addAttribute("choosenClass", choosenClass);
		return "teacherView/teacherPage";
	}
	
	@GetMapping("/teacherPage/succesfullchange")
	public String teacherPageWithAlert(Model model, HttpServletRequest request, HttpSession session) {
		Integer choosenSubject = (Integer)session.getAttribute("choosenSubject");
		Integer choosenYears = (Integer)session.getAttribute("choosenYears");
		Integer choosenClass = (Integer)session.getAttribute("choosenClass");
		if(choosenSubject != null && choosenYears != null && choosenClass != null)
		{
			List<TakingSubjectView> takingSubjectFiltered = takingSubjectViewTransform.
					takingSubjectListTransform(takingSubjectService.
							takingSubjectFiltered(request, choosenSubject, choosenYears, choosenClass));				
			model.addAttribute("takingSubjects",takingSubjectFiltered);
			model.addAttribute("isCurrentSemester", ediaryService.isCurrentSemester(schoolYearService.selectedYear(choosenYears).get()));
		}
		
		model.addAttribute("alert","Sikeres ment�s!");	
		model.addAttribute("teacherSubjects",subjectViewTransform.subjectListTransform(subjectService.teacherSubjectList(request)));
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("schoolClasses", schoolClassViewTransform.schoolClassListTransform(schoolClassService.schoolClasses()));
		return "teacherView/teacherPage";
	}
	
	@PostMapping("/teacherPage")
	public String teacherPageWithMarks(TakingSubjectFormRequest takingSubjectRequest,  HttpSession session,  Model model, HttpServletRequest request) {	
		System.out.println(takingSubjectRequest.getSelectSubject());
		
		session.setAttribute("choosenSubject", takingSubjectRequest.getSelectSubject());
		session.setAttribute("choosenYears", takingSubjectRequest.getSelectYear());
		session.setAttribute("choosenClass", takingSubjectRequest.getSelectSchoolClass());
		
		List<TakingSubjectView> takingSubjectFiltered = takingSubjectViewTransform.
				takingSubjectListTransform(takingSubjectService.
						takingSubjectFiltered(request, takingSubjectRequest.getSelectSubject(), takingSubjectRequest.getSelectYear(), takingSubjectRequest.getSelectSchoolClass()));
		
		model.addAttribute("choosenSubject",(int)session.getAttribute("choosenSubject"));
		model.addAttribute("choosenYears",(int)session.getAttribute("choosenYears"));
		model.addAttribute("choosenClass",(int)session.getAttribute("choosenClass"));
		model.addAttribute("teacherSubjects",subjectViewTransform.subjectListTransform(subjectService.teacherSubjectList(request)));
		model.addAttribute("selectedSubject", takingSubjectRequest.getSelectSubject());
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("selectedYear",takingSubjectRequest.getSelectYear());
		model.addAttribute("schoolClasses", schoolClassViewTransform.schoolClassListTransform(schoolClassService.schoolClasses()));
		model.addAttribute("selectedSchoolClass",takingSubjectRequest.getSelectSchoolClass());
		model.addAttribute("isCurrentSemester", ediaryService.isCurrentSemester(schoolYearService.selectedYear(takingSubjectRequest.getSelectYear()).get()));
		
		model.addAttribute("takingSubjects",takingSubjectFiltered);
		return "teacherView/teacherPage";
	}
	
	@GetMapping("/teacherSuccessYear/{id}")
	public String teacherSuccessYear(@PathVariable(name="id") int id) {
		takingSubjectService.successOrNotSuccessYear(id);
		return "redirect:/teacherPage";
	}
}