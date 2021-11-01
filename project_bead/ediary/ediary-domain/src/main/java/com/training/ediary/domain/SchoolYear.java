package com.training.ediary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SchoolYear {
	@Id
	@GeneratedValue
	private int schoolYearId;
	private int startSchoolYear;
	private int endSchoolYear;
	
	public int getSchoolYearId() {
		return schoolYearId;
	}
	public int getStartSchoolYear() {
		return startSchoolYear;
	}
	public void setStartSchoolYear(int startSchoolYear) {
		this.startSchoolYear = startSchoolYear;
	}
	public int getEndSchoolYear() {
		return endSchoolYear;
	}
	public void setEndSchoolYear(int endSchoolYear) {
		this.endSchoolYear = endSchoolYear;
	}
}
