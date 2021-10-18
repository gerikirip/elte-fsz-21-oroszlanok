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
}
