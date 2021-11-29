package com.training.ediary.application.transform.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.MarkHistoryView;
import com.training.ediary.domain.MarkHistory;

@Service
public class MarkHistoryViewTransform {
	
	public MarkHistoryView markHistoryTransform(MarkHistory markHistory){
		MarkHistoryView markHistoryView = new MarkHistoryView();
		markHistoryView.setId(markHistory.getId());
		markHistoryView.setChangeDate(markHistory.getChangeDate());
		markHistoryView.setPostChangedMark(markHistory.getPostChangedMark());
		markHistoryView.setPreChangedMark(markHistory.getPreChangedMark());
		return markHistoryView;
	}
	
	public List<MarkHistoryView> markHistoryListTransform(List<MarkHistory> markHistories){
		List<MarkHistoryView> markHistoryViews = new ArrayList<>();
		markHistories.forEach(markHistory -> { 
			markHistoryViews.add(markHistoryTransform(markHistory));
		});
		return markHistoryViews;
	}
}
