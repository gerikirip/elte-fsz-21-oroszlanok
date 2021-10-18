package com.training.ediary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
