package com.training.ediary.application.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.training.ediary.domain.Admin;
import com.training.ediary.domain.InClass;
import com.training.ediary.domain.Mark;
import com.training.ediary.domain.SchoolClass;
import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.Student;
import com.training.ediary.domain.Subject;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.Teaching;

@Component
public class CreateTestData {
	public Teacher createTeacher(String email, String password, String name, String username)
	{
		Teacher teacher = new Teacher();
		teacher.setUsername(username);
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setPassword(password);
        return teacher;
	}
	
	public Student createStudent(String email, String password, String name, String username)
	{
		Student student = new Student();
        student.setName(name);
        student.setUsername(username);
        student.setEmail(email);
        student.setPassword(password);
        return student;
	}
	
	public Admin createAdmin(String email, String password, String name, String username)
	{
		Admin admin = new Admin();
		admin.setName(name);
		admin.setUsername(username);
		admin.setEmail(email);
		admin.setPassword(password);
        return admin;
	}
	
	public Subject createSubject(String subjectName)
	{
		Subject subject = new Subject();
		subject.setSubjectName(subjectName);
		return subject;
	}
	
	public SchoolYear createSchoolYear(int startSchoolYear, int endSchoolYear)
	{
		SchoolYear schoolYear = new SchoolYear();
		schoolYear.setStartSchoolYear(startSchoolYear);
		schoolYear.setEndSchoolYear(endSchoolYear);
		return schoolYear;
	}
	
	public SchoolClass createSchoolClass(String className)
	{
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setClassName(className);

		return schoolClass;
	}
	
	public TakingSubject createTakingSubject(SchoolYear schoolYear, Subject subject, Student student, Teacher teacher)
	{
		TakingSubject takingSubject = new TakingSubject();
		takingSubject.setSchoolYear(schoolYear);
		takingSubject.setSubject(subject);
		takingSubject.setStudent(student);
		takingSubject.setTeacher(teacher);
		return takingSubject;
	}
	
	public InClass createInClass(Student student, SchoolYear schoolYear, SchoolClass schoolClass, Teacher headTeacher)
	{
		InClass inClass = new InClass();
		inClass.setStudent(student);
		inClass.setSchoolYear(schoolYear);
		inClass.setSchoolClass(schoolClass);
		inClass.setHeadTeacher(headTeacher);
		return inClass;
	}
	
	public Teaching createTeaching(Teacher teacher, Subject subject, SchoolYear schoolYear)
	{
		Teaching teaching = new Teaching();
		teaching.setTeacher(teacher);
		teaching.setSubject(subject);
		teaching.setSchoolYear(schoolYear);
		return teaching;
	}
	
	public Mark createMark(int markScore, int month) 
	{
		Mark mark = new Mark();
		mark.setMarkScore(markScore);
		mark.setMonth(month);
		return mark;
	}
	
	public List<Mark> addMark(List<Mark> marks, Mark mark)
	{
		marks.add(mark);
		return marks;
	}
}
