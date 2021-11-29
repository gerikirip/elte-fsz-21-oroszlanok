package com.training.ediary.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class TakingSubject {
	
	@Id
	@GeneratedValue
	private int takingSubjectId;
	
	@OneToOne 
	private SchoolYear schoolYear;
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private Teacher teacher;
	
	@OneToMany
	private List<Absent> absents;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Mark> marks = new ArrayList<>();
	
	private Boolean suYear;
	
	private int endMark;
	
	
	public double getFirstAvg() {
		double avg = 0.0;
		double markCount = 0.0;
		for(Mark mark : marks) {
			if(mark.getMonth() > 8 && mark.getMonth() < 13) {
				avg += mark.getMarkScore();
				markCount++;
			}
		}
		
		if(markCount == 0)
		{
			return 0;
		}
		
		return avg / markCount;
	}
	
	public double getSecondAvg() {
		double avg = 0.0;
		double markCount = 0.0;
		for(Mark mark : marks) {
				avg += mark.getMarkScore();
				markCount++;
		}
		
		if(markCount == 0)
		{
			return 0;
		}
		
		return avg / markCount;
	}
	
	
	public int getEndMark() {
		return endMark;
	}
	
	public void setEndMark(int endMark) {
		this.endMark = endMark;
	}
	
	public Boolean getSuYear() {
		return suYear;
	}
	
	public void setSuYear(Boolean suYear) {
		this.suYear = suYear;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public List<Mark> getMarks() {
		return marks;
	}
	
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
	
	public int getTakingSubjectId() {
		return takingSubjectId;
	}

	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Absent> getAbsents() {
		return absents;
	}

	public void setAbsents(List<Absent> absents) {
		this.absents = absents;
	}
}
