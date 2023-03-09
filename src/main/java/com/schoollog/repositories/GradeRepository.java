package com.schoollog.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.schoollog.entities.GradeEntity;
import com.schoollog.entities.StudentEntity;
import com.schoollog.entities.SubjectEntity;
import com.schoollog.entities.TeacherEntity;


public interface GradeRepository extends CrudRepository<GradeEntity, Integer>{
	List<GradeEntity> findAllByStudent(StudentEntity student);
	List<GradeEntity> findAllBySubject(SubjectEntity subject);
	List<GradeEntity> findAllByTeacher(TeacherEntity teacher);
}
