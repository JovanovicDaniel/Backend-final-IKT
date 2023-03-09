package com.schoollog.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.schoollog.entities.ParentEntity;
import com.schoollog.entities.StudentEntity;
import com.schoollog.entities.TeacherEntity;

public interface StudentRepository  extends CrudRepository<StudentEntity, Integer>{
	List<StudentEntity> findAllByParent(ParentEntity parent);
	List<StudentEntity> findAllByTeacher(TeacherEntity teacher);	
}
