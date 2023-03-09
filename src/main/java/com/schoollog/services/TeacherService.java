package com.schoollog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoollog.entities.StudentEntity;
import com.schoollog.entities.SubjectEntity;
import com.schoollog.entities.TeacherEntity;
import com.schoollog.entities.UserEntity;
import com.schoollog.entities.dto.TeacherDTO;
import com.schoollog.repositories.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository repository;

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SubjectService subjectService;

	public TeacherEntity addNewTeacher(TeacherDTO teacher) {
		TeacherEntity data = new TeacherEntity();
		data.setFirstName(teacher.getFirstName());
		data.setJoinDate(teacher.getJoinDate());
		data.setLastName(teacher.getLastName());

		UserEntity user = userService.addNewUser(teacher.getUserDto());
		data.setUser(user);

		repository.save(data);

		return data;
	}

	public TeacherEntity changeTeacher(TeacherDTO teacher, Integer id) {
		TeacherEntity data = repository.findById(id).get();
		data.setFirstName(teacher.getFirstName());
		data.setJoinDate(teacher.getJoinDate());
		data.setLastName(teacher.getLastName());

		UserEntity user = userService.findUserByUsername(teacher.getUserDto().getUsername());
		userService.changeUser(teacher.getUserDto(), user.getId());

		data.setUser(user);

		repository.save(data);

		return data;
	}

	public void deleteTeacher(Integer id) throws Exception {
		TeacherEntity data = repository.findById(id).get();
		List<SubjectEntity> subjects = subjectService.findSubjectByTeacher(id);
		List<StudentEntity> students = studentService.getStudentsForParent(id);

		if (students.size() == 0 && subjects.size() == 0) {
			repository.delete(data);
		} else {
			throw new Exception("Da bi obrisali nastavnika morate prethodno obrisati njegove studente i predmete.");
		}
	}

	public Iterable<TeacherEntity> getAllTeachers() {
		return repository.findAll();
	}

	public TeacherEntity findTeacherById(Integer id) {
		return repository.findById(id).get();
	}
}
