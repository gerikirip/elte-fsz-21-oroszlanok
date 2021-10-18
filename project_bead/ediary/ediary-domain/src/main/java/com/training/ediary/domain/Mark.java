package com.training.ediary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mark {
	@Id
	@GeneratedValue
	private int markId;
	
	private int markScore;
	
	private int month;
	
	@ManyToOne
	private TakingSubject felClass;
}
