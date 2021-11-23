package com.training.ediary.application.transform.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.SchoolClassView;
import com.training.ediary.domain.SchoolClass;

@Service
public class SchoolClassViewTransform {
	
	public SchoolClassView schoolClassTransform(SchoolClass schoolClass){
		SchoolClassView schoolClassView = new SchoolClassView();
		schoolClassView.setClassName(schoolClass.getClassName());
		schoolClassView.setClassId(schoolClass.getClassId());
		return schoolClassView;
	}
	
	public List<SchoolClassView> schoolClassListTransform(List<SchoolClass> schoolClasses){
		List<SchoolClassView> schoolClassViews = new ArrayList<>();
		schoolClasses.forEach(schoolClass -> { 
			schoolClassViews.add(schoolClassTransform(schoolClass));
		});
		return schoolClassViews;
	}
}
