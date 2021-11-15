package com.training.ediary.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeniedPageController {
	
	@GetMapping("/deniedPage")
	public String form() {
		return "deniedPage";
	}
}
