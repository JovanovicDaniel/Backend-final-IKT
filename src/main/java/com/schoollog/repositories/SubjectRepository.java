package com.schoollog.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.schoollog.entities.SubjectEntity;
import com.schoollog.entities.TeacherEntity;

public interface SubjectRepository   extends CrudRepository<SubjectEntity, Integer>{
	List<SubjectEntity> findAllByTeacher(TeacherEntity teacher);
}
