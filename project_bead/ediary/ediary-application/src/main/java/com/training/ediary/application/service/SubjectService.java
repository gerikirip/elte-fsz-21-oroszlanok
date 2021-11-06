package com.training.ediary.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.Subject;
import com.training.ediary.repository.SubjectRepo;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepo subjectRepo;
	
	public Optional<Subject> selectedSubject(int selectSubject){
		return subjectRepo.findById(selectSubject);
	}
}
