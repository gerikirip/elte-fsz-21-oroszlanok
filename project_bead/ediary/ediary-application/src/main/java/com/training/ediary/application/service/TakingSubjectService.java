package com.training.ediary.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.Mark;
import com.training.ediary.domain.Student;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.repository.TakingSubjectRepo;

@Service
public class TakingSubjectService {
	
	@Autowired
	private TakingSubjectRepo takingSubjectRepo;
	
	@Autowired
	private SchoolYearService schoolYearService;
	
	@Autowired
	private EdiaryUserService ediaryUserService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MarkService markService;
	
	public Optional<TakingSubject> selectedId(int id){
		return takingSubjectRepo.findById(id);
	}

	public List<TakingSubject> takingSubjectsByStudent(HttpServletRequest request, int selectYear){
		if(schoolYearService.selectedYear(selectYear).isPresent() && ediaryUserService.loginUser(request) != null)
		{
			return takingSubjectRepo.findBySchoolYearAndStudent(schoolYearService.selectedYear(selectYear).get(), (Student)ediaryUserService.loginUser(request));
		}
		return null;
	}
	
	public List<TakingSubject> findBySubjectAndSchoolYearAndTeacher(HttpServletRequest request, int selectSubject, int selectYear){
		if(subjectService.selectedSubject(selectSubject).isPresent() && schoolYearService.selectedYear(selectYear).isPresent() && ediaryUserService.loginUser(request) != null)
		{
			return takingSubjectRepo.findBySubjectAndSchoolYearAndTeacher(subjectService.selectedSubject(selectSubject).get(), schoolYearService.selectedYear(selectYear).get(), (Teacher)ediaryUserService.loginUser(request));
		}
		return null;
	}
	
	public List<TakingSubject> findBySchoolYearAndTeacher(HttpServletRequest request, int selectYear){
		if(schoolYearService.selectedYear(selectYear).isPresent() && ediaryUserService.loginUser(request) != null){
			return takingSubjectRepo.findBySchoolYearAndTeacher(schoolYearService.selectedYear(selectYear).get(), (Teacher)ediaryUserService.loginUser(request));
		}
		return null;
	}
	
	public List<TakingSubject> takingSubjectFiltered(HttpServletRequest request, int selectSubject, int selectYear, int selectSchoolClass){
		List<TakingSubject> takingSubjectFiltered = new ArrayList<>();
		List<Student> studentList = studentService.studentListBySchoolClass(selectYear, selectSchoolClass);		
		List<TakingSubject> takingSubjects = findBySubjectAndSchoolYearAndTeacher(request, selectSubject, selectYear);
		for(TakingSubject takingSubject : takingSubjects)
		{
			if(studentList.contains(takingSubject.getStudent()))
			{
				takingSubjectFiltered.add(takingSubject);
			}
		}
		return takingSubjectFiltered;
	}
	
	public TakingSubject takingSubjectIdByMarkId(int id) {
		Optional<Mark> mark = markService.selectedMark(id);
		if(mark.isPresent())
		{
			return takingSubjectRepo.findTakingSubjectIdByMarkId(mark.get());
		}
		return null;
	}
	
	public void successOrNotSuccessYear(int id) {
		Optional<TakingSubject> takingSubject = selectedId(id);
		if(takingSubject.isPresent()) {
			double firstSemesterAvg = takingSubject.get().getFirstAvg();
			double secondSemesterAvg = takingSubject.get().getSecondAvg();
			if(firstSemesterAvg > 2.0 && secondSemesterAvg > 2.0) {
				takingSubject.get().setSuYear(true);
				takingSubject.get().setEndMark((int)Math.round(secondSemesterAvg));
			}
			else {
				takingSubject.get().setSuYear(false);
				takingSubject.get().setEndMark(1);
			}
			takingSubjectRepo.save(takingSubject.get());
		}
	}
}
