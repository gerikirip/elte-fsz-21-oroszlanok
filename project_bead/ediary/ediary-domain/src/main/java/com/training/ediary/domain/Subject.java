package com.training.ediary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Subject {
	@Id
	@GeneratedValue
	private int subjectId;
	private String subjectName;
}
