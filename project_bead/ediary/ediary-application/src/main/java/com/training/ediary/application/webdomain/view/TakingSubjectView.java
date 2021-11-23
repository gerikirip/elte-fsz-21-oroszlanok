package com.training.ediary.application.webdomain.view;

import java.util.ArrayList;
import java.util.List;

import com.training.ediary.domain.Mark;


public class TakingSubjectView {
	private int takingSubjectId;
	private SchoolYearView schoolYear;
	private SubjectView subject;
	private StudentView student;
	private TeacherView teacher;
	private List<AbsentView> absents;
	private List<MarkView> marks = new ArrayList<>();
	private Boolean suYear;	
	private int endMark;
	
	public Boolean getSuYear() {
		return suYear;
	}
	public void setSuYear(Boolean suYear) {
		this.suYear = suYear;
	}
	public int getEndMark() {
		return endMark;
	}
	public void setEndMark(int endMark) {
		this.endMark = endMark;
	}
	public int getTakingSubjectId() {
		return takingSubjectId;
	}
	public void setTakingSubjectId(int takingSubjectId) {
		this.takingSubjectId = takingSubjectId;
	}
	public SchoolYearView getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(SchoolYearView schoolYear) {
		this.schoolYear = schoolYear;
	}
	public SubjectView getSubject() {
		return subject;
	}
	public void setSubject(SubjectView subject) {
		this.subject = subject;
	}
	public StudentView getStudent() {
		return student;
	}
	public void setStudent(StudentView student) {
		this.student = student;
	}
	public TeacherView getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherView teacher) {
		this.teacher = teacher;
	}
	public List<AbsentView> getAbsents() {
		return absents;
	}
	public void setAbsents(List<AbsentView> absents) {
		this.absents = absents;
	}
	public List<MarkView> getMarks() {
		return marks;
	}
	public void setMarks(List<MarkView> marks) {
		this.marks = marks;
	}
	
	public double getFirstAvg() {
		double avg = 0.0;
		double markCount = 0.0;
		for(MarkView mark : marks) {
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
		for(MarkView mark : marks) {
				avg += mark.getMarkScore();
				markCount++;
		}
		
		if(markCount == 0)
		{
			return 0;
		}
		
		return avg / markCount;
	}
}
