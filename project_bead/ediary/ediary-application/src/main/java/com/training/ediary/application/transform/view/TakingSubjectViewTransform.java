package com.training.ediary.application.transform.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.MarkView;
import com.training.ediary.application.webdomain.view.TakingSubjectView;
import com.training.ediary.domain.TakingSubject;

@Service
public class TakingSubjectViewTransform {
	
	@Autowired
	SchoolYearViewTransform schoolYearViewTransform;
	
	@Autowired
	StudentViewTransform studentViewTransform;
	
	@Autowired
	TeacherViewTransform teacherViewTransform;
	
	@Autowired
	SubjectViewTransform subjectViewTransform;
	
	@Autowired
	AbsentViewTransform absentViewTransform;
	
	@Autowired
	MarkViewTransform markViewTransform;
	


	public TakingSubjectView takingSubjectTransform(TakingSubject takingSubject){
		
		TakingSubjectView takingSubjectView = new TakingSubjectView();
		
		takingSubjectView.setTakingSubjectId(takingSubject.getTakingSubjectId());
		takingSubjectView.setSchoolYear(schoolYearViewTransform.schoolYearTransform(takingSubject.getSchoolYear()));
		takingSubjectView.setStudent(studentViewTransform.studentTransform(takingSubject.getStudent()));
		takingSubjectView.setTeacher(teacherViewTransform.teacherTransform(takingSubject.getTeacher()));
		takingSubjectView.setSubject(subjectViewTransform.subjectTransform(takingSubject.getSubject()));
		takingSubjectView.setMarks(markViewTransform.markListTransform(takingSubject.getMarks()));
		takingSubjectView.setAbsents(absentViewTransform.absentListTransform(takingSubject.getAbsents()));
		takingSubjectView.setSuYear(takingSubject.getSuYear());
		takingSubjectView.setEndMark(takingSubject.getEndMark());
		
		return takingSubjectView;
	}
	
	public List<TakingSubjectView> takingSubjectListTransform(List<TakingSubject> takingSubjects) {
		List<TakingSubjectView> takingSubjectViews = new ArrayList<>();
		takingSubjects.forEach(takingSubject -> { 
			takingSubjectViews.add(takingSubjectTransform(takingSubject));
		});
		return takingSubjectViews;
	}
}
