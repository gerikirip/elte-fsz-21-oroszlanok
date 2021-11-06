package com.training.ediary.application.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.EdiaryUser;
import com.training.ediary.repository.EdiaryUserRepo;

@Service
public class EdiaryUserService {
	
	@Autowired
	private EdiaryUserRepo ediaryUserRepo;
	
	public EdiaryUser loginUser(HttpServletRequest request){
		return ediaryUserRepo.findByUsername(request.getUserPrincipal().getName());
	}
}
