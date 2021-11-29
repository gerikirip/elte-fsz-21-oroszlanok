package com.training.ediary.application.transform.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.AbsentView;
import com.training.ediary.domain.Absent;

@Service
public class AbsentViewTransform {
	
	public AbsentView absentTransform(Absent absent)
	{
		AbsentView absentView = new AbsentView();
		absentView.setId(absent.getId());
		absentView.setDate(absent.getDate());
		absentView.setEndTime(absent.getEndTime());
		absentView.setAuthAbsent(absent.isAuthAbsent());
		absentView.setAuthAbsentDate(absent.getAuthAbsentDate());
		return absentView;
	}
	
	public List<AbsentView> absentListTransform(List<Absent> absents){
		List<AbsentView> absentViews = new ArrayList<>();
		absents.forEach(absent -> { 
			absentViews.add(absentTransform(absent));
		});
		return absentViews;
	}
}
