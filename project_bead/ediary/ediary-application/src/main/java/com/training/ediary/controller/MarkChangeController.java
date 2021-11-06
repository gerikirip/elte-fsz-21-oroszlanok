package com.training.ediary.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.application.service.EdiaryUserService;
import com.training.ediary.application.service.MarkService;
import com.training.ediary.application.service.TakingSubjectService;
import com.training.ediary.domain.Mark;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.repository.MarkRepo;

@Controller
public class MarkChangeController {
	
	@Autowired
	private MarkService markService;

	
	@GetMapping("/teacherMarkChange/{id}")
	public String markView(Model model, @PathVariable(name="id") int markId, HttpServletRequest request) {
		return markService.markView(model, markId, request);
	}
	
	@PostMapping("/teacherMarkChange")
	public String markChange(Model model, @RequestParam int markId, @RequestParam int markScore, HttpServletRequest request){
			return markService.markChange(model, markId, markScore, request);
	}
}
