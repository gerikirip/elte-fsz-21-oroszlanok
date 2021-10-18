package com.training.ediary.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Absent {
	
	@Id
	@GeneratedValue
	private int absentId;
	
	private Date absentDate;
	
}
