package com.schoollog.repositories;

import org.springframework.data.repository.CrudRepository;

import com.schoollog.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	UserEntity findFirstByUsername(String username); 
}
