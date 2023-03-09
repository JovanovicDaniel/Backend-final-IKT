package com.schoollog.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoollog.entities.RoleEntity;
import com.schoollog.entities.UserEntity;
import com.schoollog.entities.dto.UserDTO;
import com.schoollog.repositories.RoleRepository;
import com.schoollog.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;
	
	public UserEntity addNewUser(UserDTO user)
	{
		UserEntity data = new UserEntity();
		data.setEmail(user.getEmail());
		data.setFirstName(user.getFirstName());
		data.setLastName(user.getLastName());
		data.setUsername(user.getUsername());
		data.setPassword(user.getPassword());
		RoleEntity role = roleRepository.findById(user.getRoleId()).get();
		data.setRole(role);
		repository.save(data);
		
		return data;
	}
	
	public UserEntity changeUser(UserDTO user, Integer id)
	{
		UserEntity data = repository.findById(id).get();
		data.setEmail(user.getEmail());
		data.setFirstName(user.getFirstName());
		data.setLastName(user.getLastName());
		data.setUsername(user.getUsername());
		data.setPassword(user.getPassword());
		RoleEntity role = roleRepository.findById(user.getRoleId()).get();
		data.setRole(role);
		repository.save(data);
		
		return data;
	}
	
	public void deleteUser(Integer id)
	{
		UserEntity data = repository.findById(id).get();		
		repository.delete(data);
	}
	
	public Iterable<UserEntity> getAllUsers()
	{
		return repository.findAll();
	}
	
	public UserEntity findUserById(Integer id)
	{
		return repository.findById(id).get();
	}
	
	public UserEntity findUserByUsername(String username)
	{
		return repository.findFirstByUsername(username);
	}
}
