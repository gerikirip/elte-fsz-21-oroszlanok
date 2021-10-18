package com.training.ediary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EdiaryUser {
	protected String email;
	protected String password;
	protected String name;
	
	@Id
	@GeneratedValue
	protected int ediaryUserId;
}
