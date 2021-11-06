package com.training.ediary.application.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.training.ediary.domain.Mark;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.repository.MarkRepo;

@Service
public class MarkService {

	@Autowired
	private MarkRepo markRepo;
	
	@Autowired
	private TakingSubjectService takingSubjectService;
	
	@Autowired
	private EdiaryUserService ediaryUserService;
	
	public Optional<Mark> selectedMark(int id){
		return markRepo.findById(id);
	}
	
	public String markView(Model model, int markId, HttpServletRequest request) {
		TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markId);
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId()) 
		{
			model.addAttribute("mark",selectedMark(markId).get());
			return "teacherView/teacherMarkChange";
		}
		return "deniedPage";
	}
	
	public String markChange(Model model, int markId, int markScore, HttpServletRequest request)
	{
			TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markId);
			Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId()) 
			{
				Mark changeMark = selectedMark(markId).get();
				changeMark.setMarkScore(markScore);
				markRepo.save(changeMark);
				return "redirect:/teacherPage/succesfullchange";
			}
		return "deniedPage";
	}
}
