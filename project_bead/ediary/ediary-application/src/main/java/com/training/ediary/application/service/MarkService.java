package com.training.ediary.application.service;


import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.domain.InClass;
import com.training.ediary.domain.Mark;
import com.training.ediary.domain.MarkHistory;
import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.repository.InClassRepo;
import com.training.ediary.domain.repository.MarkHistoryRepo;
import com.training.ediary.domain.repository.MarkRepo;
import com.training.ediary.domain.repository.TakingSubjectRepo;

@Service
public class MarkService {

	@Autowired
	private MarkRepo markRepo;
	
	@Autowired
	private TakingSubjectService takingSubjectService;
	
	@Autowired
	private EdiaryUserService ediaryUserService;
	
	@Autowired
	private CreateData createData;
	
	@Autowired
	private MarkHistoryRepo markHistoryRepo;
	
	@Autowired
	private InClassRepo inClassRepo;
	
	public Optional<Mark> selectedMark(int id){
		return markRepo.findById(id);
	}
	
	public String markChangeView(Model model, int markId, HttpServletRequest request) {
		TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markId);
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		InClass studentClass = inClassRepo.findByStudentAndSchoolYear(takingSubject.getStudent(),takingSubject.getSchoolYear());
		if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId()) 
		{
			model.addAttribute("isCurrentSemester", isCurrentSemester(takingSubject.getSchoolYear()));
			model.addAttribute("mark",selectedMark(markId).get());
			model.addAttribute("takingSubject",takingSubject);
			model.addAttribute("studentClass",studentClass.getSchoolClass().getClassName());
			return "teacherView/teacherMarkChange";
		}
		return "deniedPage";
	}
	
	public String markChange(int markId, int markScore, HttpServletRequest request)
	{
			TakingSubject takingSubject = takingSubjectService.takingSubjectIdByMarkId(markId);
			Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId()) 
			{
				if(isCurrentSemester(takingSubject.getSchoolYear()))
				{
					MarkHistory markHistory = new MarkHistory();
					Mark changeMark = selectedMark(markId).get();
					markHistory.setPreChangedMark(changeMark.getMarkScore());
					changeMark.setMarkScore(markScore);
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
	
	public String markAdd(int takingSubjectId, int markScore, HttpServletRequest request){
		Optional<TakingSubject> selectedId = takingSubjectService.selectedId(takingSubjectId);
		Teacher loginTeacher = (Teacher)ediaryUserService.loginUser(request);
		if(selectedId.isPresent())
		{
			TakingSubject takingSubject = selectedId.get();
			if(loginTeacher.getEdiaryUserId() == takingSubject.getTeacher().getEdiaryUserId())
			{				
				if(isCurrentSemester(takingSubject.getSchoolYear()))
				{
					Mark mark = createData.createMark(markScore, getCurrentMonth());
			        takingSubject.setMarks(createData.addMark(takingSubject.getMarks(), mark));
			        
			        createData.createMark(markScore, getCurrentMonth());
					markRepo.save(mark);
					return "redirect:/teacherPage/succesfullchange";
				}
			}
		}
		return "deniedPage";
	}
	
	public boolean isCurrentSemester(SchoolYear schoolYear)
	{
		int currentYear = getCurrentYear();
		int currentMonth = getCurrentMonth();
		if((currentMonth <13 && currentMonth > 8 && currentYear == schoolYear.getStartSchoolYear()) || 
				(currentMonth >0 && currentMonth < 7 && currentYear == schoolYear.getEndSchoolYear()))
		{
			return true;
		}
		return false;
	}
	
	public int getCurrentMonth() {
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}
	
	public int getCurrentYear() {
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}
}
