package com.training.ediary.application.webdomain.view;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;
import com.training.ediary.domain.MarkHistory;

public class MarkView {
	
	private int markId;
	private int markScore;
	private int month;
	private Date createDate;
	private List<MarkHistoryView> markHistories;
	
	public int getMarkId() {
		return markId;
	}
	public void setMarkId(int markId) {
		this.markId = markId;
	}
	public int getMarkScore() {
		return markScore;
	}
	public void setMarkScore(int markScore) {
		this.markScore = markScore;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public List<MarkHistoryView> getMarkHistories() {
		return markHistories;
	}
	
	public void setMarkHistories(List<MarkHistoryView> markHistories) {
		this.markHistories = markHistories;
	}
	
	public String getShortDate() {
		return createDate.toString().split("\\.")[0];
	}
}
