package com.training.ediary.application.webdomain.request;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class AbsentAddRequest {
	private int takingSubjectId;
	
	 @DateTimeFormat(iso = ISO.DATE_TIME) 
 	 private LocalDateTime absentDate;
	
	 @DateTimeFormat(pattern = "HH:mm") 
	 private Date absentEndTime;

	public int getTakingSubjectId() {
		return takingSubjectId;
	}

	public void setTakingSubjectId(int takingSubjectId) {
		this.takingSubjectId = takingSubjectId;
	}

	public LocalDateTime getAbsentDate() {
		return absentDate;
	}

	public void setAbsentDate(LocalDateTime absentDate) {
		this.absentDate = absentDate;
	}

	public Date getAbsentEndTime() {
		return absentEndTime;
	}

	public void setAbsentEndTime(Date absentEndTime) {
		this.absentEndTime = absentEndTime;
	}
}
