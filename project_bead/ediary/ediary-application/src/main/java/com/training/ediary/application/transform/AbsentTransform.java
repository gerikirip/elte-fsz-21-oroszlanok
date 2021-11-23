package com.training.ediary.application.transform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.application.service.EdiaryService;
import com.training.ediary.application.webdomain.request.AbsentAddRequest;
import com.training.ediary.domain.Absent;

@Service
public class AbsentTransform {

	@Autowired
	private EdiaryService ediaryService;
	
	public Absent transformAbsentAddRequest(AbsentAddRequest absentAddRequest)
	{
		Absent absent = new Absent();
		absent.setDate(absentAddRequest.getAbsentDate());
		absent.setEndTime(ediaryService.convertTime(absentAddRequest.getAbsentEndTime()));
		return absent;
	}
	
	
}
