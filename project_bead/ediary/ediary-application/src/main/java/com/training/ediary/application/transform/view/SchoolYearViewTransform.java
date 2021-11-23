package com.training.ediary.application.transform.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.SchoolYearView;
import com.training.ediary.domain.SchoolYear;

@Service
public class SchoolYearViewTransform {

	public SchoolYearView schoolYearTransform(SchoolYear schoolYear) {
		SchoolYearView schoolYearView = new SchoolYearView();
		schoolYearView.setSchoolYearId(schoolYear.getSchoolYearId());
		schoolYearView.setStartSchoolYear(schoolYear.getStartSchoolYear());
		schoolYearView.setEndSchoolYear(schoolYear.getEndSchoolYear());
		return schoolYearView;
	}
	
	public List<SchoolYearView> schoolClassListTransform(List<SchoolYear> schoolYears){
		List<SchoolYearView> schoolYearViews = new ArrayList<>();
		schoolYears.forEach(schoolYear -> { 
			schoolYearViews.add(schoolYearTransform(schoolYear));
		});
		return schoolYearViews;
	}
}
