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
import com.schoollog.entities.SubjectEntity;
import com.schoollog.entities.TeacherEntity;
import com.schoollog.entities.dto.SubjectDTO;
import com.schoollog.services.SubjectService;

@RestController
@RequestMapping(path="/api/schoollog/subjects")
public class SubjectController {
	@Autowired
	private SubjectService service;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
	public ResponseEntity<?> getSubjectById(@PathVariable Integer id)
	{
		try
		{
			
			SubjectEntity subject = service.findSubjectById(id);
			SubjectDTO subjectDto = new SubjectDTO();	
			subjectDto.setClassFund(subject.getClassFund());
			subjectDto.setName(subject.getName());
			TeacherEntity teacher = subject.getTeacher();
			subjectDto.setTeacherId(teacher.getId());
			
			if(subject.getId().equals(id))
			{
				return new ResponseEntity<>(subjectDto, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(new RESTError(1, "Subject not found"), HttpStatus.NOT_FOUND);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value="/add")
	public ResponseEntity<?> addNewSubject(@RequestBody SubjectDTO subjectDto)
	{
		try
		{
			service.addNewSubject(subjectDto);
		
			logger.info("Subject successfully added");  
		
		return new ResponseEntity<>(subjectDto, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/change/{id}")
	public ResponseEntity<?> changeSubject(@RequestBody SubjectDTO subjectDto, @PathVariable Integer id)
	{
		try
		{
			service.changeSubject(subjectDto, id);
		
			logger.info("Subject successfully changed"); 
			
			return new ResponseEntity<>(subjectDto, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
	public void deleteSubject(@PathVariable Integer id)
	{
		try
		{
			service.deleteSubject(id);
			
			logger.info("Subject successfully deleted");
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.GET, value = "/get/all")
	public ResponseEntity<?> getAllSubjects()
	{
		try
		{
			
			Iterable<SubjectEntity> subjects = service.findAllSubjects();
			List<SubjectDTO> dtos = new ArrayList<SubjectDTO>();
			
			for(SubjectEntity subject : subjects)
			{
				SubjectDTO subjectDto = new SubjectDTO();	
				subjectDto.setClassFund(subject.getClassFund());
				subjectDto.setName(subject.getName());
				TeacherEntity teacher = subject.getTeacher();
				subjectDto.setTeacherId(teacher.getId());
				dtos.add(subjectDto);
			}
			
         	return new ResponseEntity<>(dtos, HttpStatus.OK);
			
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
