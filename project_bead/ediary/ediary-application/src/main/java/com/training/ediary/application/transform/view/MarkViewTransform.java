package com.training.ediary.application.transform.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.MarkView;
import com.training.ediary.domain.Mark;

@Service
public class MarkViewTransform {
	
	public MarkView markTransform(Mark mark){
		MarkView markView = new MarkView();
		markView.setMarkId(mark.getMarkId());
		markView.setMarkScore(mark.getMarkScore());
		markView.setMonth(mark.getMonth());
		markView.setCreateDate(mark.getCreateDate());
		return markView;
	}
	
	public List<MarkView> markListTransform(List<Mark> marks){
		List<MarkView> markViews = new ArrayList<>();
		marks.forEach(mark -> { 
			markViews.add(markTransform(mark));
		});
		return markViews;
	}
}
