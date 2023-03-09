package com.schoollog.repositories;

import org.springframework.data.repository.CrudRepository;

import com.schoollog.entities.TeacherEntity;

public interface TeacherRepository   extends CrudRepository<TeacherEntity, Integer>{

}
