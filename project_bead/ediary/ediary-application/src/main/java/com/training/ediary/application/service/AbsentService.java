package com.training.ediary.application.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.training.ediary.application.transform.AbsentTransform;
import com.training.ediary.application.webdomain.request.AbsentAddRequest;
import com.training.ediary.domain.Absent;
import com.training.ediary.domain.InClass;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.repository.AbsentRepo;
import com.training.ediary.domain.repository.InClassRepo;

@Service
public class AbsentService {

	@Autowired
	private TakingSubjectService takingSubjectService;
	
	@Autowired
    private EdiaryUserService ediaryUserService;
	
	@Autowired
	private EdiaryService ediaryService;
	
	@Autowired
	private AbsentTransform absentTransform;
	
	@Autowired
	private InClassRepo inClassRepo;
	
	@Autowired
	private AbsentRepo absentRepo;
	
	public String addAbsentView(Model model, int takingSubjectId, HttpServletRequest request) {
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
				return "teacherView/teacherAbsent";
			}
		}
		return "deniedPage";
	}
	
	public String addAbsent(AbsentAddRequest absentAddRequest, HttpServletRequest request){
		Optional<TakingSubject> selectedId = takingSubjectService.selectedId(absentAddRequest.getTakingSubjectId());
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		if(selectedId.isPresent())
		{
			TakingSubject takingSubject = selectedId.get();
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId())
			{				
				if(ediaryService.isCurrentSemester(takingSubject.getSchoolYear()) && ediaryService.outDate(absentAddRequest.getAbsentDate(), 3))
				{
					Absent absent = absentTransform.transformAbsentAddRequest(absentAddRequest);
					takingSubject.getAbsents().add(absent);
					absentRepo.save(absent);
					return "redirect:/teacherPage/succesfullchange";
				}
			}
		}
		return "deniedPage";
	}
}
