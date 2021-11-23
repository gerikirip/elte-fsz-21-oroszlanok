package com.training.ediary.application.transform.view;

import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.StudentView;
import com.training.ediary.domain.Student;

@Service
public class StudentViewTransform {
		
		public StudentView studentTransform(Student student)
		{
			StudentView studentView = new StudentView();
			studentView.setUsername(student.getUsername());
			studentView.setName(student.getName());
			studentView.setEmail(student.getEmail());
			return studentView;
		}
	
}
