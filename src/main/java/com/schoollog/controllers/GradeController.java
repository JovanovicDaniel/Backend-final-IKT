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
import com.schoollog.entities.GradeEntity;
import com.schoollog.entities.ParentEntity;
import com.schoollog.entities.StudentEntity;
import com.schoollog.entities.SubjectEntity;
import com.schoollog.entities.TeacherEntity;
import com.schoollog.entities.dto.GradeDTO;
import com.schoollog.entities.dto.GradeDetailsDTO;
import com.schoollog.models.EmailObject;
import com.schoollog.services.EmailService;
import com.schoollog.services.GradeService;


@RestController
@RequestMapping(path="/api/schoollog/grades")
public class GradeController {

	@Autowired
	private GradeService service;
	
	@Autowired
	private EmailService emailService;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_PARENT", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
	public ResponseEntity<?> getGradeById(@PathVariable Integer id)
	{
		try
		{
			
			GradeEntity grade = service.findGradeById(id);
			GradeDetailsDTO gradeDto = new GradeDetailsDTO();
			gradeDto.setGrade(grade.getNumber());
			gradeDto.setSemester(grade.getSemester());
			StudentEntity student = grade.getStudent();
			gradeDto.setStudent(student.getFirstName() + " " + student.getLastName());
			SubjectEntity subject = grade.getSubject();
			gradeDto.setSubject(subject.getName());
			TeacherEntity teacher = grade.getTeacher();
			gradeDto.setTeacher(teacher.getFirstName() + " " + teacher.getLastName());
			gradeDto.setClassSign(student.getClassSign());
			ParentEntity parent = student.getParent();
			gradeDto.setParent(parent.getFirstName() + " " + parent.getLastName());
			gradeDto.setParentEmail(parent.getEmail());
			
			if(grade.getId().equals(id))
			{
				return new ResponseEntity<>(gradeDto, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(new RESTError(1, "Grade not found"), HttpStatus.NOT_FOUND);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_PARENT", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.GET, value = "/get/student/{id}")
	public ResponseEntity<?> getGradesForStudent(@PathVariable Integer id)
	{
		try
		{
			List<GradeDetailsDTO> details = new ArrayList<GradeDetailsDTO>();
			List<GradeEntity> grades = service.getGradesForStudent(id);
			
			for(GradeEntity grade : grades)
			{
				GradeDetailsDTO gradeDto = new GradeDetailsDTO();
				gradeDto.setGrade(grade.getNumber());
				gradeDto.setSemester(grade.getSemester());
				StudentEntity student = grade.getStudent();
				gradeDto.setStudent(student.getFirstName() + " " + student.getLastName());
				SubjectEntity subject = grade.getSubject();
				gradeDto.setSubject(subject.getName());
				TeacherEntity teacher = grade.getTeacher();
				gradeDto.setTeacher(teacher.getFirstName() + " " + teacher.getLastName());
				gradeDto.setClassSign(student.getClassSign());
				ParentEntity parent = student.getParent();
				gradeDto.setParent(parent.getFirstName() + " " + parent.getLastName());
				gradeDto.setParentEmail(parent.getEmail());	 
				
				details.add(gradeDto);
			
			}
			return new ResponseEntity<>(details, HttpStatus.OK);
			
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.GET, value = "/get/subject/{id}")
	public ResponseEntity<?> getGradesForSubject(@PathVariable Integer id)
	{
		try
		{
			List<GradeDetailsDTO> details = new ArrayList<GradeDetailsDTO>();
			List<GradeEntity> grades = service.getGradesForSubject(id);
			
			for(GradeEntity grade : grades)
			{
				GradeDetailsDTO gradeDto = new GradeDetailsDTO();
				gradeDto.setGrade(grade.getNumber());
				gradeDto.setSemester(grade.getSemester());
				StudentEntity student = grade.getStudent();
				gradeDto.setStudent(student.getFirstName() + " " + student.getLastName());
				SubjectEntity subject = grade.getSubject();
				gradeDto.setSubject(subject.getName());
				TeacherEntity teacher = grade.getTeacher();
				gradeDto.setTeacher(teacher.getFirstName() + " " + teacher.getLastName());
				gradeDto.setClassSign(student.getClassSign());
				ParentEntity parent = student.getParent();
				gradeDto.setParent(parent.getFirstName() + " " + parent.getLastName());
				gradeDto.setParentEmail(parent.getEmail());	 
				
				details.add(gradeDto);
			
			}
			return new ResponseEntity<>(details, HttpStatus.OK);
			
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.GET, value = "/get/teacher/{id}")
	public ResponseEntity<?> getGradesForTeacher(@PathVariable Integer id)
	{
		try
		{
			List<GradeDetailsDTO> details = new ArrayList<GradeDetailsDTO>();
			List<GradeEntity> grades = service.getGradesForTeacher(id);
			
			for(GradeEntity grade : grades)
			{
				GradeDetailsDTO gradeDto = new GradeDetailsDTO();
				gradeDto.setGrade(grade.getNumber());
				gradeDto.setSemester(grade.getSemester());
				StudentEntity student = grade.getStudent();
				gradeDto.setStudent(student.getFirstName() + " " + student.getLastName());
				SubjectEntity subject = grade.getSubject();
				gradeDto.setSubject(subject.getName());
				TeacherEntity teacher = grade.getTeacher();
				gradeDto.setTeacher(teacher.getFirstName() + " " + teacher.getLastName());
				gradeDto.setClassSign(student.getClassSign());
				ParentEntity parent = student.getParent();
				gradeDto.setParent(parent.getFirstName() + " " + parent.getLastName());
				gradeDto.setParentEmail(parent.getEmail());	 
				
				details.add(gradeDto);
			
			}
			return new ResponseEntity<>(details, HttpStatus.OK);
			
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.POST, value="/add")
	public ResponseEntity<?> addNewGrade(@RequestBody GradeDTO gradeDto)
	{
		try
		{
			if(gradeDto.getNumber() >= 1 && gradeDto.getNumber() <= 5)
			{
				GradeEntity grade = service.addNewGrade(gradeDto);
				StudentEntity student = grade.getStudent();
				ParentEntity parent = student.getParent();
				SubjectEntity subject = grade.getSubject();
				TeacherEntity teacher = grade.getTeacher();
				EmailObject object = new EmailObject();
				//object.setTo("jovanovicdaniel@gmail.com");
				object.setTo(parent.getEmail());
				object.setSubject("Nova ocena za studenta " + student.getFirstName() + " " + student.getLastName());
				object.setText("Nastavnik " + teacher.getFirstName() + " " + teacher.getLastName() + " je studentu " + student.getFirstName()
				+ " " + student.getLastName() + " dao novu ocenu " + grade.getNumber().toString() + " iz predmeta " + subject.getName() +". ");
				
				emailService.sendTemplateMessage(object);
				
				logger.info("Grade successfully added");  
		
				return new ResponseEntity<>(gradeDto, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(new RESTError(1, "Grade must be between 1 and 5"), HttpStatus.NOT_ACCEPTABLE);
			}
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.POST, value = "/change/{id}")
	public ResponseEntity<?> changeGrade(@RequestBody GradeDTO gradeDto, @PathVariable Integer id)
	{
		try
		{
			if(gradeDto.getNumber() >= 1 && gradeDto.getNumber() <= 5)
			{
				GradeEntity oldGrade = service.findGradeById(id);
				String oGrade = oldGrade.getNumber().toString();
				GradeEntity grade = service.changeGrade(gradeDto, id);
				StudentEntity student = grade.getStudent();
				ParentEntity parent = student.getParent();
				SubjectEntity subject = grade.getSubject();
				TeacherEntity teacher = grade.getTeacher();
				EmailObject object = new EmailObject();
				//object.setTo("jovanovicdaniel@gmail.com");
				object.setTo(parent.getEmail());
				object.setSubject("Promena ocene za studenta " + student.getFirstName() + " " + student.getLastName());
				object.setText("Nastavnik " + teacher.getFirstName() + " " + teacher.getLastName() + " je studentu " + student.getFirstName()
				+ " " + student.getLastName() + " dao ocenu " + grade.getNumber().toString() + " iz predmeta " + subject.getName() +". "
				+ "Prethodna ocena je bila " + oGrade);
				
				emailService.sendTemplateMessage(object);
				
				logger.info("Grade successfully changed"); 
			
				return new ResponseEntity<>(gradeDto, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(new RESTError(1, "Grade must be between 1 and 5"), HttpStatus.NOT_ACCEPTABLE);
			}
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
	public void deleteGrade(@PathVariable Integer id)
	{
		try
		{
			service.deleteGrade(id);
			
			logger.info("Grade successfully deleted");
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
		}
	}
	
	
}
