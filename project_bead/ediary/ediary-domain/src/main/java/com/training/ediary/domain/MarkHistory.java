package com.training.ediary.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MarkHistory {

	@Id
	@GeneratedValue
	private int id;
	
	private Date changeDate;
	
	private int preChangedMark;
	private int postChangedMark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public int getPreChangedMark() {
		return preChangedMark;
	}
	public void setPreChangedMark(int preChangedMark) {
		this.preChangedMark = preChangedMark;
	}
	public int getPostChangedMark() {
		return postChangedMark;
	}
	public void setPostChangedMark(int postChangedMark) {
		this.postChangedMark = postChangedMark;
	}
	
	public String getShortDate() {
		return changeDate.toString().split("\\.")[0];
	}
}
