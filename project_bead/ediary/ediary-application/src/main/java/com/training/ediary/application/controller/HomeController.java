package com.training.ediary.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.training.ediary.application.service.EdiaryUserService;

@Controller
public class HomeController {
	
	@Autowired
	EdiaryUserService ediaryUserService;
	
	@GetMapping("/")
	public String home() {
		if(ediaryUserService.hasRole("ROLE_Student"))
		{
			return "redirect:/studentPage";
		}
		else if(ediaryUserService.hasRole("ROLE_Teacher"))
		{
			return "redirect:/teacherPage";
		}
		else if(ediaryUserService.hasRole("ROLE_Admin"))
		{
			return "redirect:/adminPage";
		}
		return "redirect:/loginPage";
	}
	
	
}
