package com.training.ediary.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Mark {
	@Id
	@GeneratedValue
	private int markId;
	private int markScore;
	private int month;
	private Date createDate;

	public int getMarkId() {
		return markId;
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
	
	@OneToMany
	private List<MarkHistory> markHistories;
	
	public List<MarkHistory> getMarkHistories() {
		return markHistories;
	}
	
	public void setMarkHistories(List<MarkHistory> markHistories) {
		this.markHistories = markHistories;
	}
}
