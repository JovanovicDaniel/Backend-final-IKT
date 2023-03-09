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
import com.schoollog.entities.ParentEntity;
import com.schoollog.entities.RoleEntity;
import com.schoollog.entities.StudentEntity;
import com.schoollog.entities.TeacherEntity;
import com.schoollog.entities.UserEntity;
import com.schoollog.entities.dto.StudentDTO;
import com.schoollog.entities.dto.UserDTO;
import com.schoollog.services.StudentService;
import com.schoollog.services.UserService;

@RestController
@RequestMapping(path="/api/schoollog/students")
public class StudentController {
	@Autowired
	private StudentService service;
	
	@Autowired
	private UserService userService;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER", "ROLE_PARENT"})
	@RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable Integer id)
	{
		try
		{
			
			StudentEntity student = service.findStudentById(id);
			StudentDTO studentDto = new StudentDTO();
			studentDto.setBirthDate(student.getBirthDate());
			studentDto.setClassSign(student.getClassSign());
			studentDto.setFirstName(student.getFirstName());
			studentDto.setLastName(student.getLastName());
			ParentEntity parent = student.getParent();
			studentDto.setParentId(parent.getId());
			TeacherEntity teacher = student.getTeacher();
			studentDto.setTeacherId(teacher.getId());
			
			UserEntity user =student.getUser();
			UserDTO userDto = new UserDTO();
			userDto.setEmail(user.getEmail());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			RoleEntity role = user.getRole();
			userDto.setRoleId(role.getId());
			userDto.setUsername(user.getUsername());
			studentDto.setUserDto(userDto);
			
			if(student.getId().equals(id))
			{
				return new ResponseEntity<>(studentDto, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(new RESTError(1, "Student not found"), HttpStatus.NOT_FOUND);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value="/add")
	public ResponseEntity<?> addNewStudent(@RequestBody StudentDTO studentDto)
	{
		try
		{
			service.addNewStudent(studentDto);
		
			logger.info("Student successfully added");  
		
		return new ResponseEntity<>(studentDto, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/change/{id}")
	public ResponseEntity<?> changeStudent(@RequestBody StudentDTO studentDto, @PathVariable Integer id)
	{
		try
		{
			service.changeStudent(studentDto, id);
		
			logger.info("Student successfully changed"); 
			
			return new ResponseEntity<>(studentDto, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
	public void deleteStudent(@PathVariable Integer id)
	{
		try
		{
			StudentEntity student = service.findStudentById(id);
			UserEntity user = student.getUser();
			
			service.deleteStudent(id);
			userService.deleteUser(user.getId());
			
			logger.info("Student successfully deleted");
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.GET, value = "/get/all")
	public ResponseEntity<?> getAllStudents()
	{
		try
		{
			
			Iterable<StudentEntity> students = service.findAllStudents();
			List<StudentDTO> dtos = new ArrayList<StudentDTO>();
			
			for(StudentEntity student : students)
			{
				StudentDTO studentDto = new StudentDTO();
				studentDto.setBirthDate(student.getBirthDate());
				studentDto.setClassSign(student.getClassSign());
				studentDto.setFirstName(student.getFirstName());
				studentDto.setLastName(student.getLastName());
				ParentEntity parent = student.getParent();
				studentDto.setParentId(parent.getId());
				TeacherEntity teacher = student.getTeacher();
				studentDto.setTeacherId(teacher.getId());
				
				UserEntity user =student.getUser();
				UserDTO userDto = new UserDTO();
				userDto.setEmail(user.getEmail());
				userDto.setFirstName(user.getFirstName());
				userDto.setLastName(user.getLastName());
				RoleEntity role = user.getRole();
				userDto.setRoleId(role.getId());
				userDto.setUsername(user.getUsername());
				studentDto.setUserDto(userDto);
			
				dtos.add(studentDto);
		    }			

			return new ResponseEntity<>(dtos, HttpStatus.OK);

		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
