package com.training.ediary.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class DeniedPageController {
	
	@GetMapping("/deniedPage")
	public String form() {
		return "deniedPage";
	}
}
