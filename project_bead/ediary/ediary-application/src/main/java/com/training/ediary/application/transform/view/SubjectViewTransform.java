package com.training.ediary.application.transform.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.SubjectView;
import com.training.ediary.domain.Subject;

@Service
public class SubjectViewTransform {
	
	public SubjectView subjectTransform(Subject subject) {
		SubjectView subjectView = new SubjectView();
		subjectView.setSubjectId(subject.getSubjectId());
		subjectView.setSubjectName(subject.getSubjectName());
		return subjectView;
	}
	
	public List<SubjectView> subjectListTransform(List<Subject> subjects){
		List<SubjectView> subjectViews = new ArrayList<>();
		subjects.forEach(subject -> { 
			subjectViews.add(subjectTransform(subject));
		});
		return subjectViews;
	}
}
