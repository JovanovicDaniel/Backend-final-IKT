package com.schoollog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schoollog.entities.SubjectEntity;
import com.schoollog.entities.TeacherEntity;
import com.schoollog.entities.dto.SubjectDTO;
import com.schoollog.repositories.SubjectRepository;
import com.schoollog.repositories.TeacherRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository repository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	public SubjectEntity addNewSubject(SubjectDTO subject)
	{
		SubjectEntity data = new SubjectEntity();
		data.setClassFund(subject.getClassFund());
		data.setName(subject.getName());
		
		TeacherEntity teacher = teacherRepository.findById(subject.getTeacherId()).get();
		data.setTeacher(teacher);
		repository.save(data);
		
		return data;
				
	}
	
	public SubjectEntity changeSubject(SubjectDTO subject, Integer id)
	{
		SubjectEntity data = repository.findById(id).get();
		data.setClassFund(subject.getClassFund());
		data.setName(subject.getName());
		
		TeacherEntity teacher = teacherRepository.findById(subject.getTeacherId()).get();
		data.setTeacher(teacher);
		repository.save(data);
		
		return data;
				
	}
	
	public void deleteSubject(Integer id)
	{
		SubjectEntity data = repository.findById(id).get();
		repository.delete(data);
	}
	
	public Iterable<SubjectEntity> findAllSubjects() {
		return repository.findAll();
	}
	
	public SubjectEntity findSubjectById(Integer id)
	{
		return repository.findById(id).get();
	}
	
	public List<SubjectEntity> findSubjectByTeacher(Integer teacherId)
	{
		TeacherEntity teacher = teacherRepository.findById(teacherId).get();
		
		return repository.findAllByTeacher(teacher);
	}
}
