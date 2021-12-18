package com.training.ediary.application.service;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.ediary.application.transform.MarkTransform;
import com.training.ediary.application.transform.view.InClassViewTransform;
import com.training.ediary.application.transform.view.MarkViewTransform;
import com.training.ediary.application.transform.view.TakingSubjectViewTransform;
import com.training.ediary.application.webdomain.request.MarkAddRequest;
import com.training.ediary.application.webdomain.request.MarkChangeRequest;
import com.training.ediary.domain.InClass;
import com.training.ediary.domain.Mark;
import com.training.ediary.domain.MarkHistory;
import com.training.ediary.domain.Student;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.repository.InClassRepo;
import com.training.ediary.domain.repository.MarkHistoryRepo;
import com.training.ediary.domain.repository.MarkRepo;

@Service
public class MarkService {

	@Autowired
	private MarkRepo markRepo;
	
	@Autowired
	private TakingSubjectService takingSubjectService;
	
	@Autowired
	private EdiaryUserService ediaryUserService;
	
	@Autowired
	private EdiaryService ediaryService;
	
	@Autowired
	private MarkHistoryRepo markHistoryRepo;
	
	@Autowired
	private InClassRepo inClassRepo;
	
	@Autowired
	private MarkTransform markTransformer;
	
	@Autowired
	private TakingSubjectViewTransform takingSubjectViewTransform;
	
	@Autowired
	private InClassViewTransform inClassViewTransform;
	
	@Autowired
	private MarkViewTransform markViewTransform;
	
	public Optional<Mark> selectedMark(int id){
		return markRepo.findById(id);
	}
	
	public String teacherMarkChangeView(Model model, int markId, HttpServletRequest request) {
		TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markId);
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		InClass studentClass = inClassRepo.findByStudentAndSchoolYear(takingSubject.getStudent(),takingSubject.getSchoolYear());
		if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId()) 
		{
			model.addAttribute("isCurrentSemester", ediaryService.isCurrentSemester(takingSubject.getSchoolYear()));
			model.addAttribute("mark", markViewTransform.markTransform(selectedMark(markId).get()));
			model.addAttribute("takingSubject",takingSubjectViewTransform.takingSubjectTransform(takingSubject));
			model.addAttribute("studentClass",inClassViewTransform.inClassTransform(studentClass));
			return "teacherView/teacherMarkChange";
		}
		return "teacherView/teacherDeniedPage";
	}
	
	public String studentMarkChangeView(Model model, int markId, HttpServletRequest request) {
		TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markId);
		Student loginStudent = (Student)ediaryUserService.loginUser(request);
		if(loginStudent.getEdiaryUserId() == takingSubject.getStudent().getEdiaryUserId()) 
		{
			model.addAttribute("mark", markViewTransform.markTransform(selectedMark(markId).get()));
			model.addAttribute("takingSubject",takingSubjectViewTransform.takingSubjectTransform(takingSubject));
			return "studentView/studentChange";
		}
		return "studentView/studentDeniedPage";
	} 
	
	public String teacherMarkChange(MarkChangeRequest markChangeRequest, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
			TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markChangeRequest.getMarkId());
			Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId()) 
			{
				if(ediaryService.isCurrentSemester(takingSubject.getSchoolYear()))
				{
					MarkHistory markHistory = new MarkHistory();
					Mark changeMark = selectedMark(markChangeRequest.getMarkId()).get();
					if(markChangeRequest.getMarkScore() <= 5 && markChangeRequest.getMarkScore() >= 1) {
						markHistory.setPreChangedMark(changeMark.getMarkScore());
						changeMark.setMarkScore(markChangeRequest.getMarkScore());
						markHistory.setPostChangedMark(changeMark.getMarkScore());
						markHistory.setChangeDate(new Date());
						markHistory = markHistoryRepo.save(markHistory);
						changeMark.getMarkHistories().add(markHistory);
						markRepo.save(changeMark);
						redirectAttributes.addFlashAttribute("alert", "Sikeres változtatás!");
						return "redirect:/teacherPage";
					}
					return "teacherView/teacherError";
				}
			}
		return "teacherView/teacherDeniedPage";
	}
	
	public String teacherMarkAddView(Model model, int takingSubjectId, HttpServletRequest request) {
		Optional<TakingSubject> selectedId = takingSubjectService.selectedId(takingSubjectId);
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		if(selectedId.isPresent())
		{
			TakingSubject takingSubject = selectedId.get();
			InClass studentClass = inClassRepo.findByStudentAndSchoolYear(takingSubject.getStudent(),takingSubject.getSchoolYear());
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId())
			{
				model.addAttribute("takingSubject",takingSubjectViewTransform.takingSubjectTransform(takingSubject));
				model.addAttribute("studentClass",inClassViewTransform.inClassTransform(studentClass));
				return "teacherView/teacherMarkAdd";
			}
		}
		return "teacherView/teacherDeniedPage";
	}
	
	public String teacherMarkAdd(MarkAddRequest markAddRequest, HttpServletRequest request, RedirectAttributes redirectAttributes){
		Optional<TakingSubject> selectedId = takingSubjectService.selectedId(markAddRequest.getTakingSubjectId());
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		if(selectedId.isPresent())
		{
			TakingSubject takingSubject = selectedId.get();
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId())
			{				
				if(ediaryService.isCurrentSemester(takingSubject.getSchoolYear()))
				{
					Mark mark = markTransformer.transorfMarkAddRequest(markAddRequest);
					if(mark.getMarkScore() <= 5 && mark.getMarkScore() >= 1) {
						takingSubject.getMarks().add(mark);
						markRepo.save(mark);
						redirectAttributes.addFlashAttribute("alert", "Sikeres mentés!");
						return "redirect:/teacherPage";
					}
					return "teacherView/teacherError";
				}
			}
		}
		return "teacherView/teacherDeniedPage";
	}
}
