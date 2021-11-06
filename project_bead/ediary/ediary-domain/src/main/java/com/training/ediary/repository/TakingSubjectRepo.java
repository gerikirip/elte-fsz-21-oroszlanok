package com.training.ediary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.Mark;
import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.Student;
import com.training.ediary.domain.Subject;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.Teaching;
import com.training.ediary.domain.projection.TakingSubjectId;

@Repository
public interface TakingSubjectRepo extends JpaRepository<TakingSubject, Integer>{
	
	public List<TakingSubject> findBySchoolYearAndStudent(SchoolYear schoolYear, Student student);
	
	public List<TakingSubject> findBySubjectAndSchoolYearAndTeacher(Subject subject, SchoolYear schoolYear, Teacher teacher);
	
	@Query("select takingsubject from TakingSubject takingsubject where :mark member of takingsubject.marks")
	public TakingSubject findTakingSubjectIdByMarkId(@Param("mark") Mark markId);
}
