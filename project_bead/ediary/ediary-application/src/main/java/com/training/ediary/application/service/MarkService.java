package com.training.ediary.application.service;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.training.ediary.application.transform.MarkTransform;
import com.training.ediary.application.webdomain.request.MarkAddRequest;
import com.training.ediary.application.webdomain.request.MarkChangeRequest;
import com.training.ediary.domain.InClass;
import com.training.ediary.domain.Mark;
import com.training.ediary.domain.MarkHistory;
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
	
	public Optional<Mark> selectedMark(int id){
		return markRepo.findById(id);
	}
	
	public String markChangeView(Model model, int markId, HttpServletRequest request) {
		TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markId);
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		InClass studentClass = inClassRepo.findByStudentAndSchoolYear(takingSubject.getStudent(),takingSubject.getSchoolYear());
		if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId()) 
		{
			model.addAttribute("isCurrentSemester", ediaryService.isCurrentSemester(takingSubject.getSchoolYear()));
			model.addAttribute("mark",selectedMark(markId).get());
			model.addAttribute("takingSubject",takingSubject);
			model.addAttribute("studentClass",studentClass.getSchoolClass().getClassName());
			return "teacherView/teacherMarkChange";
		}
		return "deniedPage";
	}
	
	public String markChange(MarkChangeRequest markChangeRequest, HttpServletRequest request)
	{
			TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markChangeRequest.getMarkId());
			Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId()) 
			{
				if(ediaryService.isCurrentSemester(takingSubject.getSchoolYear()))
				{
					MarkHistory markHistory = new MarkHistory();
					Mark changeMark = selectedMark(markChangeRequest.getMarkId()).get();
					markHistory.setPreChangedMark(changeMark.getMarkScore());
					changeMark.setMarkScore(markChangeRequest.getMarkScore());
					markHistory.setPostChangedMark(changeMark.getMarkScore());
					markHistory.setChangeDate(new Date());
					markHistory = markHistoryRepo.save(markHistory);
					changeMark.getMarkHistories().add(markHistory);
					markRepo.save(changeMark);
					return "redirect:/teacherPage/succesfullchange";
				}
			}
		return "deniedPage";
	}
	
	public String markAddView(Model model, int takingSubjectId, HttpServletRequest request) {
		Optional<TakingSubject> selectedId = takingSubjectService.selectedId(takingSubjectId);
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		if(selectedId.isPresent())
		{
			TakingSubject takingSubject = selectedId.get();
			InClass studentClass = inClassRepo.findByStudentAndSchoolYear(takingSubject.getStudent(),takingSubject.getSchoolYear());
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId())
			{
				model.addAttribute("takingSubject",takingSubject);
				model.addAttribute("studentClass",studentClass.getSchoolClass().getClassName());
				return "teacherView/teacherMarkAdd";
			}
		}
		return "deniedPage";
	}
	
	public String markAdd(MarkAddRequest markAddRequest, HttpServletRequest request){
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
					takingSubject.getMarks().add(mark);
					markRepo.save(mark);
					return "redirect:/teacherPage/succesfullchange";
				}
			}
		}
		return "deniedPage";
	}
}
