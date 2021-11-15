package com.training.ediary.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.InClass;
import com.training.ediary.domain.SchoolClass;
import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.Student;

@Repository
public interface InClassRepo extends JpaRepository<InClass, Integer>{
	public InClass findByStudentAndSchoolYear(Student student, SchoolYear schoolYear);
	
	@Query("select student as stud from InClass inClass where inClass.schoolYear = :year and inClass.schoolClass = :class")
	public List<Student> findStudentsInClass(@Param("year") SchoolYear schoolYear, @Param("class") SchoolClass schoolClass); 
}
