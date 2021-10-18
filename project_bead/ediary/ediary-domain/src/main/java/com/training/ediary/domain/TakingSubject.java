package com.training.ediary.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TakingSubject {
	
	@Id
	@GeneratedValue
	private int felClassId;
	
	//@ManyToOne
	@OneToOne 
	private SchoolYear schoolYear;
	
	//@ManyToOne
	@OneToOne
	private Subject subject;
	
	//ManyToOne
	@OneToOne
	private Student student;
	
	@OneToMany
	private List<Absent> absents;
	
	@OneToMany
	private List<authAbsent> authAbsents;
}
