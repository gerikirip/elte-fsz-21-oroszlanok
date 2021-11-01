package com.training.ediary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class InClass {
	@Id
	@GeneratedValue
	private int inClassId;
	
	@OneToOne
	private Student student;
	
	@OneToOne
	private SchoolYear schoolYear;
	
	@OneToOne
	private SchoolClass schoolClass;
	
	@ManyToOne
	private Teacher headTeacher;
	
	public Teacher getHeadTeacher() {
		return headTeacher;
	}
	
	public void setHeadTeacher(Teacher headTeacher) {
		this.headTeacher = headTeacher;
	}

	public int getInClassId() {
		return inClassId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}
}
