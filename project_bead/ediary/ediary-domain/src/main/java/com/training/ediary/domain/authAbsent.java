package com.training.ediary.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class authAbsent {
	
	@Id
	@GeneratedValue
	private int authAbsentId;
	
	private Date authAbsentDate;
}
