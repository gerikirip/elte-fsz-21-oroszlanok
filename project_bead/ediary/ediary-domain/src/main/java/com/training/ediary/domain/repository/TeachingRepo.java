package com.training.ediary.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.ediary.domain.SchoolYear;
import com.training.ediary.domain.Subject;
import com.training.ediary.domain.TakingSubject;
import com.training.ediary.domain.Teacher;
import com.training.ediary.domain.Teaching;

@Repository
public interface TeachingRepo extends JpaRepository<Teaching, Integer>{
	
	@Query("select subject as sub from Teaching teaching where teaching.teacher = :teach group by teaching.subject")
	public List<Subject> findTeachersSubjects(@Param("teach") Teacher teacher);
}
