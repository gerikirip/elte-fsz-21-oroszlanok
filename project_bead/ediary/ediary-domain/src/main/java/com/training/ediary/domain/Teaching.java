package com.training.ediary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Teaching {
	
	@Id
	@GeneratedValue
	private int teachingId;
	
	@OneToOne
	private Teacher teacher;
	
	@OneToOne
	private Subject subject;
	
	@OneToOne
	private SchoolYear schoolYear;
}
