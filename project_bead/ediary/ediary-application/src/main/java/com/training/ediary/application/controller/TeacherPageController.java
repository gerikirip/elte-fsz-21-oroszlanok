package com.training.ediary.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.ediary.application.service.EdiaryService;
import com.training.ediary.application.service.InClassService;
import com.training.ediary.application.service.SchoolClassService;
import com.training.ediary.application.service.SchoolYearService;
import com.training.ediary.application.service.SubjectService;
import com.training.ediary.application.service.TakingSubjectService;
import com.training.ediary.application.transform.view.InClassViewTransform;
import com.training.ediary.application.transform.view.SchoolClassViewTransform;
import com.training.ediary.application.transform.view.SchoolYearViewTransform;
import com.training.ediary.application.transform.view.SubjectViewTransform;
import com.training.ediary.application.transform.view.TakingSubjectViewTransform;
import com.training.ediary.application.webdomain.request.TakingSubjectFormRequest;
import com.training.ediary.application.webdomain.view.TakingSubjectView;


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
	private InClassService inClassService;
	
	@Autowired
	private TakingSubjectViewTransform takingSubjectViewTransform;
	
	@Autowired
	private SubjectViewTransform subjectViewTransform;
	
	@Autowired
	private SchoolYearViewTransform schoolYearViewTransform;
	
	@Autowired
	private SchoolClassViewTransform schoolClassViewTransform;
	
	@Autowired
	private InClassViewTransform inClassViewTransform;
	
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
		model.addAttribute("teacherSubjects",subjectViewTransform.subjectListTransform(subjectService.teacherSubjectList(request)));
		model.addAttribute("schoolYears", schoolYearViewTransform.schoolClassListTransform(schoolYearService.schoolYears()));
		model.addAttribute("schoolClasses", schoolClassViewTransform.schoolClassListTransform(schoolClassService.schoolClasses()));
		model.addAttribute("inClasses", inClassViewTransform.inClassListTransform(inClassService.getInClassesByHeadTeacher(request)));
		
		model.addAttribute("choosenSubject",choosenSubject);
		model.addAttribute("choosenYears", choosenYears);
		model.addAttribute("choosenClass", choosenClass);
		
		return "teacherView/teacherPage";
	}
	
	@PostMapping("/teacherPage")
	public String teacherPageWithMarks(TakingSubjectFormRequest takingSubjectRequest,  HttpSession session,  Model model, HttpServletRequest request) {	
		
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
	
	@GetMapping("/teacherPage/teacherSuccessYear/{id}")
	public String teacherSuccessYear(@PathVariable(name="id") int id) {
		takingSubjectService.successOrNotSuccessYear(id);
		return "redirect:/teacherPage";
	}
}