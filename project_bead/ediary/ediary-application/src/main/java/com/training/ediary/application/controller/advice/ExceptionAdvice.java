package com.training.ediary.application.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.training.ediary.application.service.EdiaryUserService;

@ControllerAdvice
public class ExceptionAdvice {
	
	@Autowired
	EdiaryUserService ediaryUserService;
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, HttpServletRequest request) {
		ediaryUserService.loginUser(request);
		
		if(ediaryUserService.hasRole("ROLE_Student"))
		{
			return "studentView/studentError";
		}
		else if(ediaryUserService.hasRole("ROLE_Teacher"))
		{
			return "teacherView/teacherError";
		}
		else {
			return "error";
		}
		
	}
	
	
}
