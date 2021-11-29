package com.training.ediary.domain;

import java.util.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Absent {
	
	@Id
	@GeneratedValue
	private int id;
	
	private LocalDateTime date;
	
	private Time endTime;
	
	private boolean authAbsent;
	
	private Date authAbsentDate;
	
	public boolean isAuthAbsent() {
		return authAbsent;
	}
	public void setAuthAbsent(boolean authAbsent) {
		this.authAbsent = authAbsent;
	}
	public Date getAuthAbsentDate() {
		return authAbsentDate;
	}
	public void setAuthAbsentDate(Date authAbsentDate) {
		this.authAbsentDate = authAbsentDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}
