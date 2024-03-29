package com.training.ediary.application.service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.stereotype.Component;

import com.training.ediary.application.webdomain.view.MarkView;
import com.training.ediary.domain.Absent;
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
public class CreateData {
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
	
	public TakingSubject createTakingSubject(SchoolYear schoolYear, Subject subject, Student student, Teacher teacher, Boolean suYear)
	{
		TakingSubject takingSubject = new TakingSubject();
		takingSubject.setSchoolYear(schoolYear);
		takingSubject.setSubject(subject);
		takingSubject.setStudent(student);
		takingSubject.setTeacher(teacher);
		takingSubject.setSuYear(suYear);
		takingSubject.setMarks(createRandomMark(1,5));
		if(suYear != null && suYear)
		{ 
			takingSubject.setEndMark(endAvg(takingSubject.getMarks())); 
		}
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
		mark.setCreateDate(new Date());
		return mark;
	}
	
	public Absent createAbsent(LocalDateTime date, Time endTime)
	{
		Absent absent = new Absent();
		absent.setDate(date);
		absent.setEndTime(endTime);
		return absent;
	}
	
	public List<Mark> createRandomMark(int min, int max) {
		Random random = new Random();
		List<Mark> marks = new ArrayList<Mark>();
		for(int i = 1; i < 13; i++) {
			int randomMark1 = random.nextInt(max - min + 1) + min;
			marks.add(createMark(randomMark1, i));
			
			int randomMark2 = random.nextInt(max - min + 1) + min;
			marks.add(createMark(randomMark2, i));
			
			int randomMark3 = random.nextInt(max - min + 1) + min;
			marks.add(createMark(randomMark3, i));
		}
		return marks;
	}
	
	public int endAvg(List<Mark> marks) {
		double avg = 0.0;
		double markCount = 0.0;
		for(Mark mark : marks) {
				avg += mark.getMarkScore();
				markCount++;
		}
		
		if(markCount == 0)
		{
			return 0;
		}
		
		return (int)Math.round(avg / markCount);
	}
}
