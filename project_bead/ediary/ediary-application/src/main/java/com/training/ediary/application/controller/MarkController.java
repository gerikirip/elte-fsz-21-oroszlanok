package com.training.ediary.application.controller;



import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.ediary.application.service.MarkService;
import com.training.ediary.application.webdomain.request.MarkAddRequest;
import com.training.ediary.application.webdomain.request.MarkChangeRequest;

@Controller
public class MarkController {
	
	@Autowired
	private MarkService markService;
	
	@GetMapping("/studentChange/{id}")
	public String studentMarkView(Model model, @PathVariable(name="id") int markId, HttpServletRequest request) {		
		return markService.studentMarkChangeView(model, markId, request);
	}
	
	@GetMapping("/teacherMarkChange/{id}")
	public String teacherMarkView(Model model, @PathVariable(name="id") int markId, HttpServletRequest request) {		
		return markService.teacherMarkChangeView(model, markId, request);
	}
	
	@PostMapping("/teacherMarkChange")
	public String teacherMarkChange(MarkChangeRequest markChangeRequest, HttpServletRequest request){
		return markService.teacherMarkChange(markChangeRequest, request);
	}
	
	@GetMapping("/teacherMarkAdd/{id}")
	public String teacherMarkAddView(Model model, @PathVariable(name="id") int takingSubjectId, HttpServletRequest request) {
		return markService.teacherMarkAddView(model, takingSubjectId, request);
	}
	
	@PostMapping("/teacherMarkAdd")
	public String teacherMarkAdd(MarkAddRequest markAddRequest, HttpServletRequest request){
		return markService.teacherMarkAdd(markAddRequest, request);
	}
}
