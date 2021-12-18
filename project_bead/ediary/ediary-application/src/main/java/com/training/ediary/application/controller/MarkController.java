package com.training.ediary.application.controller;



import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.ediary.application.service.MarkService;
import com.training.ediary.application.webdomain.request.MarkAddRequest;
import com.training.ediary.application.webdomain.request.MarkChangeRequest;

@Controller
public class MarkController {
	
	@Autowired
	private MarkService markService;
	
	@GetMapping("/studentPage/studentChange/{id}")
	public String studentMarkView(Model model, @PathVariable(name="id") int markId, HttpServletRequest request) {		
		return markService.studentMarkChangeView(model, markId, request);
	}
	
	@GetMapping("/teacherPage/teacherMarkChange/{id}")
	public String teacherMarkView(Model model, @PathVariable(name="id") int markId, HttpServletRequest request) {		
		return markService.teacherMarkChangeView(model, markId, request);
	}
	
	@PostMapping("/teacherPage/teacherMarkChange")
	public String teacherMarkChange(MarkChangeRequest markChangeRequest, HttpServletRequest request, RedirectAttributes redirectAttributes){
		return markService.teacherMarkChange(markChangeRequest, request, redirectAttributes);
	}
	
	@GetMapping("/teacherPage/teacherMarkAdd/{id}")
	public String teacherMarkAddView(Model model, @PathVariable(name="id") int takingSubjectId, HttpServletRequest request) {
		return markService.teacherMarkAddView(model, takingSubjectId, request);
	}
	
	@PostMapping("/teacherPage/teacherMarkAdd")
	public String teacherMarkAdd(MarkAddRequest markAddRequest, HttpServletRequest request, RedirectAttributes redirectAttributes){
		return markService.teacherMarkAdd(markAddRequest, request, redirectAttributes);
	}
}
