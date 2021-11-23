package com.training.ediary.application.controller;

import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.application.service.AbsentService;
import com.training.ediary.application.webdomain.request.AbsentAddRequest;

@Controller
public class AbsentController {
	
	@Autowired
	AbsentService absentService;
	
	@GetMapping("/teacherAbsent/{id}")
	public String addAbsentView(Model model, @PathVariable(name="id") int takingSubjectId, HttpServletRequest request) {
		return absentService.addAbsentView(model, takingSubjectId, request);
	}
	
	@PostMapping("/teacherAbsent")
	public String markAdd(AbsentAddRequest absentAddRequest, HttpServletRequest request){
		return absentService.addAbsent(absentAddRequest, request);
	}
}
