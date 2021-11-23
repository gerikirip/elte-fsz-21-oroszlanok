package com.training.ediary.application.webdomain.view;

import java.sql.Time;
import java.time.LocalDateTime;

public class AbsentView {
	
	private int id;
	private LocalDateTime date;
	private Time endTime;
	
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
