package com.training.ediary.application.transform.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.InClassView;
import com.training.ediary.application.webdomain.view.TakingSubjectView;
import com.training.ediary.domain.InClass;
import com.training.ediary.domain.TakingSubject;

@Service
public class InClassViewTransform {
	
	@Autowired
	SchoolClassViewTransform schoolClassViewTransform;
	
	@Autowired
	TeacherViewTransform teacherViewTransform;
	
	@Autowired
	SchoolYearViewTransform schoolYearViewTransform;
	
	@Autowired
	StudentViewTransform studentViewTransform;
	
	public InClassView inClassTransform(InClass inClass) {
		InClassView inClassView = new InClassView();
		inClassView.setInClassId(inClass.getInClassId());
		inClassView.setSchoolClass(schoolClassViewTransform.schoolClassTransform(inClass.getSchoolClass()));
		inClassView.setHeadTeacher(teacherViewTransform.teacherTransform(inClass.getHeadTeacher()));
		inClassView.setSchoolYear(schoolYearViewTransform.schoolYearTransform(inClass.getSchoolYear()));
		inClassView.setStudent(studentViewTransform.studentTransform(inClass.getStudent()));
		return inClassView;
	}
	
	public List<InClassView> inClassListTransform(List<InClass> InClasses) {
		List<InClassView> inClassesView = new ArrayList<>();
		InClasses.forEach(inClass -> { 
			inClassesView.add(inClassTransform(inClass));
		});
		return inClassesView;
	}
}
