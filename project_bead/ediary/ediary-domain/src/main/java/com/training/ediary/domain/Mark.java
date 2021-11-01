package com.training.ediary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mark {
	@Id
	@GeneratedValue
	private int markId;
	private int markScore;
	private int month;

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


	
	/*
	@ManyToOne
	private TakingSubject felClass;
	*/
}
