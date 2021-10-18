package com.training.ediary.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SchoolClass {
	@Id
	@GeneratedValue
	private int classId;
	
	private String className;
	
	@ManyToOne
	private Teacher headTeacher;
	
}
