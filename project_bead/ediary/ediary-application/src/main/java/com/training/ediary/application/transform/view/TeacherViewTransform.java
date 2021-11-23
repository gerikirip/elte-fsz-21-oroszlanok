package com.training.ediary.application.transform.view;

import org.springframework.stereotype.Service;

import com.training.ediary.application.webdomain.view.TeacherView;
import com.training.ediary.domain.Teacher;

@Service
public class TeacherViewTransform {
	
	public TeacherView teacherTransform(Teacher teacher)
	{
		TeacherView teacherView = new TeacherView();
		teacherView.setUsername(teacher.getUsername());
		teacherView.setName(teacher.getName());
		teacherView.setEmail(teacher.getEmail());
		return teacherView;
	}
}
