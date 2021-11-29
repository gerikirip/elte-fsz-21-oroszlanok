package com.training.ediary.application.webdomain.view;

public class InClassView {
	
private int inClassId;
	
	private StudentView student;
	
	private SchoolYearView schoolYear;
	
	private SchoolClassView schoolClass;
	
	private TeacherView headTeacher;
	
	public TeacherView getHeadTeacher() {
		return headTeacher;
	}

	public int getInClassId() {
		return inClassId;
	}

	public void setInClassId(int inClassId) {
		this.inClassId = inClassId;
	}

	public StudentView getStudent() {
		return student;
	}

	public void setStudent(StudentView student) {
		this.student = student;
	}

	public SchoolYearView getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYearView schoolYear) {
		this.schoolYear = schoolYear;
	}

	public SchoolClassView getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClassView schoolClass) {
		this.schoolClass = schoolClass;
	}

	public void setHeadTeacher(TeacherView headTeacher) {
		this.headTeacher = headTeacher;
	}
	
	
}
