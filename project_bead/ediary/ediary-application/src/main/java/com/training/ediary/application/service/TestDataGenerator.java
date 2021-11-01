package com.training.ediary.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.training.ediary.repository.AdminRepo;
import com.training.ediary.repository.InClassRepo;
import com.training.ediary.repository.MarkRepo;
import com.training.ediary.repository.SchoolClassRepo;
import com.training.ediary.repository.SchoolYearRepo;
import com.training.ediary.repository.StudentRepo;
import com.training.ediary.repository.SubjectRepo;
import com.training.ediary.repository.TakingSubjectRepo;
import com.training.ediary.repository.TeacherRepo;
import com.training.ediary.repository.TeachingRepo;

@Component
public class TestDataGenerator {
	
	@Autowired
	private InClassRepo  inClassRepo;
	
	@Autowired
	private MarkRepo markRepo;
	
	@Autowired
	private SchoolClassRepo schoolClassRepo;
	
	@Autowired
	private SchoolYearRepo schoolYearRepo;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private SubjectRepo subjectRepo;
	
	@Autowired 
	private TakingSubjectRepo takingSubjectRepo;
	
	@Autowired
	private TeacherRepo teacherRepo;
	
	@Autowired
	private TeachingRepo teachingRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CreateData createData;
	
	@Transactional
    public void createTestData(){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		Admin admin = createData.createAdmin("admin@admin.com", passwordEncoder.encode("123"), "Admin", "Admin");
		adminRepo.save(admin);
		
		
        Teacher teacher1 = createData.createTeacher("teach@teacher.com", passwordEncoder.encode("123"), "Jani", "Teacher");
        Teacher teacher2 = createData.createTeacher("feriteach@teacher.com", passwordEncoder.encode("123"), "Feri", "FeriTeacher");
        teacherRepo.save(teacher1);
        teacherRepo.save(teacher2);
        
        Student student1 = createData.createStudent("student@student.com", passwordEncoder.encode("123"), "Peti", "Stud");
        studentRepo.save(student1);
        
        Student student2 = createData.createStudent("katastudent@student.com", passwordEncoder.encode("123"), "Kata", "KataStud");
        studentRepo.save(student2);
        
        Student student3 = createData.createStudent("emilstudent@student.com", passwordEncoder.encode("123"), "Emil", "EmilStud");
        studentRepo.save(student3);
        
        Subject subject_Math = createData.createSubject("Matek");
        subjectRepo.save(subject_Math);
        
        Subject subject_Literature = createData.createSubject("Irodalom");
        subjectRepo.save(subject_Literature);
        
        Subject subject_Chem = createData.createSubject("Kémia");
        subjectRepo.save(subject_Chem);
        
        Subject subject_Bio = createData.createSubject("Biológia");
        subjectRepo.save(subject_Bio);
        
        SchoolYear schoolYear2019_2020 = createData.createSchoolYear(2019, 2020);
        schoolYearRepo.save(schoolYear2019_2020);
        SchoolYear schoolYear2020_2021 = createData.createSchoolYear(2020, 2021);
        schoolYearRepo.save(schoolYear2020_2021);
        
        SchoolClass schoolClass9a = createData.createSchoolClass("9/a");
        schoolClassRepo.save(schoolClass9a);
        
        SchoolClass schoolClass9c = createData.createSchoolClass("9/c");
        schoolClassRepo.save(schoolClass9c);
        
        SchoolClass schoolClass10a = createData.createSchoolClass("10/a");
        schoolClassRepo.save(schoolClass10a);
        
        SchoolClass schoolClass10c = createData.createSchoolClass("10/c");
        schoolClassRepo.save(schoolClass10c);
        
        InClass inClass9A_1 = createData.createInClass(student3, schoolYear2019_2020, schoolClass9a, teacher2);
        inClassRepo.save(inClass9A_1);
        
        InClass inClass9C_1 = createData.createInClass(student1, schoolYear2019_2020, schoolClass9c, teacher1);
        inClassRepo.save(inClass9C_1);
        
        InClass inClass10A_1 = createData.createInClass(student3, schoolYear2020_2021, schoolClass10a, teacher2);
        inClassRepo.save(inClass10A_1);
        
        InClass inClass10C_1 = createData.createInClass(student1, schoolYear2020_2021, schoolClass10c, teacher1);
        inClassRepo.save(inClass10C_1);
        
        InClass inClass9C_2 = createData.createInClass(student2, schoolYear2019_2020, schoolClass9c, teacher1);
        inClassRepo.save(inClass9C_2);
        
        InClass inClass10C_2 = createData.createInClass(student2, schoolYear2020_2021, schoolClass10c, teacher1);
        inClassRepo.save(inClass10C_2);
        
        Teaching teachingMath_1920 = createData.createTeaching(teacher1, subject_Math, schoolYear2019_2020);
        teachingRepo.save(teachingMath_1920);
        
        Teaching teachingMath = createData.createTeaching(teacher1, subject_Math, schoolYear2020_2021);
        teachingRepo.save(teachingMath);
        
        Teaching teachingMath2_1920 = createData.createTeaching(teacher2, subject_Math, schoolYear2019_2020);
        teachingRepo.save(teachingMath2_1920);
        
        Teaching teachingMath2 = createData.createTeaching(teacher2, subject_Math, schoolYear2020_2021);
        teachingRepo.save(teachingMath2);
        
        Teaching teachingChem = createData.createTeaching(teacher1, subject_Chem, schoolYear2020_2021);
        teachingRepo.save(teachingChem);
        
        Teaching teachingLit = createData.createTeaching(teacher2, subject_Literature, schoolYear2020_2021);
        teachingRepo.save(teachingLit);
        
        Teaching teachingBio = createData.createTeaching(teacher2, subject_Bio, schoolYear2020_2021);
        teachingRepo.save(teachingBio);

        
        TakingSubject takingSubject2019_2020Math = createData.createTakingSubject(schoolYear2019_2020, subject_Math, student1, teacher1);
        takingSubject2019_2020Math.addMark(createData.createMark(3, 9));
        takingSubject2019_2020Math.addMark(createData.createMark(2, 9));
        takingSubject2019_2020Math.addMark(createData.createMark(4, 9));
        takingSubject2019_2020Math.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Math.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Math.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Math.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Math.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Math.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Math.addMark(createData.createMark(1, 2));
        takingSubject2019_2020Math.addMark(createData.createMark(3, 3));
        takingSubject2019_2020Math.addMark(createData.createMark(5, 6));
        takingSubjectRepo.save(takingSubject2019_2020Math);
        
        TakingSubject takingSubject2019_2020Math_kata = createData.createTakingSubject(schoolYear2019_2020, subject_Math, student2, teacher2);
        takingSubject2019_2020Math_kata.addMark(createData.createMark(3, 9));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(2, 9));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(4, 9));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(1, 2));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(3, 3));
        takingSubject2019_2020Math_kata.addMark(createData.createMark(5, 6));
        takingSubjectRepo.save(takingSubject2019_2020Math_kata);
        
        TakingSubject takingSubject2019_2020Literature = createData.createTakingSubject(schoolYear2019_2020, subject_Literature, student1, teacher2);
        takingSubject2019_2020Literature.addMark(createData.createMark(5, 9));
        takingSubject2019_2020Literature.addMark(createData.createMark(2, 9));
        takingSubject2019_2020Literature.addMark(createData.createMark(4, 10));
        takingSubject2019_2020Literature.addMark(createData.createMark(2, 10));
        takingSubject2019_2020Literature.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Literature.addMark(createData.createMark(2, 10));
        takingSubject2019_2020Literature.addMark(createData.createMark(5, 11));
        takingSubject2019_2020Literature.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Literature.addMark(createData.createMark(4, 12));
        takingSubject2019_2020Literature.addMark(createData.createMark(4, 1));
        takingSubject2019_2020Literature.addMark(createData.createMark(2, 2));
        takingSubject2019_2020Literature.addMark(createData.createMark(3, 5));
        takingSubjectRepo.save(takingSubject2019_2020Literature);
        
        TakingSubject takingSubject2019_2020Lit_kata = createData.createTakingSubject(schoolYear2019_2020, subject_Literature, student2, teacher2);
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(3, 9));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(2, 9));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(4, 9));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(1, 2));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(3, 3));
        takingSubject2019_2020Lit_kata.addMark(createData.createMark(5, 6));
        takingSubjectRepo.save(takingSubject2019_2020Lit_kata);
        
        TakingSubject takingSubject2019_2020Lit_emil = createData.createTakingSubject(schoolYear2019_2020, subject_Literature, student3, teacher2);
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(4, 9));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(2, 9));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(4, 10));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(5, 10));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(2, 10));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(3, 10));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(3, 11));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(4, 12));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(3, 1));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(5, 2));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(3, 3));
        takingSubject2019_2020Lit_emil.addMark(createData.createMark(5, 6));
        takingSubjectRepo.save(takingSubject2019_2020Lit_emil);
        
        
        TakingSubject takingSubject2020_2021 = createData.createTakingSubject(schoolYear2020_2021, subject_Math, student1, teacher2);
        takingSubject2020_2021.addMark(createData.createMark(3, 9));
        takingSubject2020_2021.addMark(createData.createMark(3, 9));
        takingSubject2020_2021.addMark(createData.createMark(3, 9));
        takingSubject2020_2021.addMark(createData.createMark(5, 10));
        takingSubject2020_2021.addMark(createData.createMark(4, 10));
        takingSubject2020_2021.addMark(createData.createMark(5, 10));
        takingSubjectRepo.save(takingSubject2020_2021);
    }	
}
