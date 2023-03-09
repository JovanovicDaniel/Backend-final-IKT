package com.schoollog.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoollog.entities.GradeEntity;
import com.schoollog.entities.StudentEntity;
import com.schoollog.entities.SubjectEntity;
import com.schoollog.entities.TeacherEntity;
import com.schoollog.entities.dto.GradeDTO;
import com.schoollog.repositories.GradeRepository;
import com.schoollog.repositories.StudentRepository;
import com.schoollog.repositories.SubjectRepository;
import com.schoollog.repositories.TeacherRepository;

@Service
public class GradeService {

	@Autowired
	private GradeRepository repository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	
	public GradeEntity addNewGrade(GradeDTO grade)
	{
		GradeEntity data = new GradeEntity();
		data.setNumber(grade.getNumber());
		data.setSemester(grade.getSemester());
		
		StudentEntity student = studentRepository.findById(grade.getStudentId()).get();
		data.setStudent(student);
		
		TeacherEntity teacher = teacherRepository.findById(grade.getTeacherId()).get();
		data.setTeacher(teacher);
		
		SubjectEntity subject = subjectRepository.findById(grade.getSubjectId()).get();
		data.setSubject(subject);
		
		repository.save(data);
		
		return data;		 
	}
	
	public GradeEntity changeGrade(GradeDTO grade, Integer id)
	{
		GradeEntity data = repository.findById(id).get();
		data.setNumber(grade.getNumber());
		data.setSemester(grade.getSemester());
		
		repository.save(data);
		
		return data;
	}
	
	public void deleteGrade(Integer id)
	{
		GradeEntity data = repository.findById(id).get();
		repository.delete(data);
	}
	
	public List<GradeEntity> getGradesForStudent(Integer studentId)
	{
		StudentEntity student = studentRepository.findById(studentId).get();
		List<GradeEntity> grades = repository.findAllByStudent(student);
		
		return grades;
	}
	
	public List<GradeEntity> getGradesForSubject(Integer subjectId)
	{
		SubjectEntity subject = subjectRepository.findById(subjectId).get();
		List<GradeEntity> grades = repository.findAllBySubject(subject);
		
		return grades;
	}
	
	public List<GradeEntity> getGradesForTeacher(Integer teacherId)
	{
		TeacherEntity teacher = teacherRepository.findById(teacherId).get();
		List<GradeEntity> grades = repository.findAllByTeacher(teacher);
		
		return grades;
	}
	
	public Iterable<GradeEntity> getAllGrades()
	{
		return repository.findAll();
	}
	
	public GradeEntity findGradeById(Integer id)
	{
		return repository.findById(id).get();
	}
}
