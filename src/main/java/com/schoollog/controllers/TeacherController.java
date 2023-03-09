package com.schoollog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.schoollog.controllers.util.RESTError;
import com.schoollog.entities.RoleEntity;
import com.schoollog.entities.TeacherEntity;
import com.schoollog.entities.UserEntity;
import com.schoollog.entities.dto.TeacherDTO;
import com.schoollog.entities.dto.UserDTO;
import com.schoollog.services.TeacherService;
import com.schoollog.services.UserService;

@RestController
@RequestMapping(path="/api/schoollog/teachers")
public class TeacherController {
	@Autowired
	private TeacherService service;

	@Autowired
	private UserService userService;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable Integer id)
	{
		try
		{
			
			TeacherEntity teacher = service.findTeacherById(id);
			TeacherDTO teacherDto = new TeacherDTO();
			teacherDto.setFirstName(teacher.getFirstName());
			teacherDto.setJoinDate(teacher.getJoinDate());
			teacherDto.setLastName(teacher.getLastName());
			
			UserEntity user =teacher.getUser();
			UserDTO userDto = new UserDTO();
			userDto.setEmail(user.getEmail());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			RoleEntity role = user.getRole();
			userDto.setRoleId(role.getId());
			userDto.setUsername(user.getUsername());
			teacherDto.setUserDto(userDto);

			if(teacher.getId().equals(id))
			{
				return new ResponseEntity<>(teacherDto, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(new RESTError(1, "Teacher not found"), HttpStatus.NOT_FOUND);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value="/add")
	public ResponseEntity<?> addNewTeacher(@RequestBody TeacherDTO teacherDto)
	{
		try
		{
			service.addNewTeacher(teacherDto);
		
			logger.info("Teacher successfully added");  
		
		return new ResponseEntity<>(teacherDto, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/change/{id}")
	public ResponseEntity<?> changeTeacher(@RequestBody TeacherDTO teacherDto, @PathVariable Integer id)
	{
		try
		{
			service.changeTeacher(teacherDto, id);
		
			logger.info("Teacher successfully changed"); 
			
			return new ResponseEntity<>(teacherDto, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
	public void deleteTeacher(@PathVariable Integer id)
	{
		try
		{
			TeacherEntity teacher = service.findTeacherById(id);
			UserEntity user = teacher.getUser();
			
			service.deleteTeacher(id);
			userService.deleteUser(user.getId());
			
			logger.info("Teacher successfully deleted");
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/get/all")
	public ResponseEntity<?> getAllTeachers()
	{
		try
		{
			
			Iterable<TeacherEntity> teachers = service.getAllTeachers();
			List<TeacherDTO> dtos = new ArrayList<TeacherDTO>();
			
			for(TeacherEntity teacher : teachers)
			{
				TeacherDTO teacherDto = new TeacherDTO();
				teacherDto.setFirstName(teacher.getFirstName());
				teacherDto.setJoinDate(teacher.getJoinDate());
				teacherDto.setLastName(teacher.getLastName());
				
				UserEntity user =teacher.getUser();
				UserDTO userDto = new UserDTO();
				userDto.setEmail(user.getEmail());
				userDto.setFirstName(user.getFirstName());
				userDto.setLastName(user.getLastName());
				RoleEntity role = user.getRole();
				userDto.setRoleId(role.getId());
				userDto.setUsername(user.getUsername());
				teacherDto.setUserDto(userDto);
				
				dtos.add(teacherDto);
			}


		    return new ResponseEntity<>(dtos, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
