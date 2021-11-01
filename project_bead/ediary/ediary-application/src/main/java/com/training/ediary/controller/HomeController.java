package com.training.ediary.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		if(hasRole("ROLE_Student"))
		{
			return "redirect:/studentPage";
		}
		else if(hasRole("ROLE_Teacher"))
		{
			return "redirect:/teacherPage";
		}
		else if(hasRole("ROLE_Admin"))
		{
			return "redirect:/adminPage";
		}
		return "redirect:/loginPage";
	}
	
	public static boolean hasRole (String roleName)
	{
	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
	            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
	}
}
