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

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class TakingSubject {
	
	@Id
	@GeneratedValue
	private int takingSubjectId;
	
	//@ManyToOne
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
	
	@OneToMany
	private List<authAbsent> authAbsents;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Mark> marks = new ArrayList<>();
	
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

	public List<authAbsent> getAuthAbsents() {
		return authAbsents;
	}

	public void setAuthAbsents(List<authAbsent> authAbsents) {
		this.authAbsents = authAbsents;
	}
	
	public void addMark(Mark mark){
		marks.add(mark);
	}
}
