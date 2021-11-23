package com.training.ediary.application.transform;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.application.service.EdiaryService;
import com.training.ediary.application.webdomain.request.MarkAddRequest;
import com.training.ediary.domain.Mark;

@Service
public class MarkTransform {
	
	@Autowired
	EdiaryService ediaryService;
	
	public Mark transorfMarkAddRequest(MarkAddRequest markAddRequest) 
	{
		Mark mark = new Mark();
		mark.setMarkScore(markAddRequest.getMarkScore());
		mark.setMonth(ediaryService.getCurrentMonth());
		mark.setCreateDate(new Date());
		return mark;
	}
}
