package com.schoollog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoollog.entities.GradeEntity;
import com.schoollog.entities.ParentEntity;
import com.schoollog.entities.StudentEntity;
import com.schoollog.entities.TeacherEntity;
import com.schoollog.entities.UserEntity;
import com.schoollog.entities.dto.StudentDTO;
import com.schoollog.repositories.ParentRepository;
import com.schoollog.repositories.StudentRepository;
import com.schoollog.repositories.TeacherRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private GradeService gradeService;

	@Autowired
	private UserService userService;

	public StudentEntity addNewStudent(StudentDTO student) {
		StudentEntity data = new StudentEntity();
		data.setBirthDate(student.getBirthDate());
		data.setClassSign(student.getClassSign());
		data.setFirstName(student.getFirstName());
		data.setLastName(student.getLastName());

		ParentEntity parent = parentRepository.findById(student.getParentId()).get();
		data.setParent(parent);

		TeacherEntity teacher = teacherRepository.findById(student.getTeacherId()).get();
		data.setTeacher(teacher);

		UserEntity user = userService.addNewUser(student.getUserDto());
		data.setUser(user);

		repository.save(data);

		return data;
	}

	public StudentEntity changeStudent(StudentDTO student, Integer id) {
		StudentEntity data = repository.findById(id).get();
		data.setBirthDate(student.getBirthDate());
		data.setClassSign(student.getClassSign());
		data.setFirstName(student.getFirstName());
		data.setLastName(student.getLastName());

		ParentEntity parent = parentRepository.findById(student.getParentId()).get();
		data.setParent(parent);

		TeacherEntity teacher = teacherRepository.findById(student.getTeacherId()).get();
		data.setTeacher(teacher);

		UserEntity user = userService.findUserByUsername(student.getUserDto().getUsername());
		userService.changeUser(student.getUserDto(), user.getId());

		repository.save(data);

		return data;
	}

	public void deleteStudent(Integer id) {
		StudentEntity data = repository.findById(id).get();
		List<GradeEntity> grades = gradeService.getGradesForStudent(id);

		for (GradeEntity grade : grades) {
			gradeService.deleteGrade(grade.getId());
		}

		repository.delete(data);
	}

	public Iterable<StudentEntity> findAllStudents() {
		return repository.findAll();
	}

	public StudentEntity findStudentById(Integer id) {
		return repository.findById(id).get();
	}

	public List<StudentEntity> getStudentsForTeacher(Integer teacherId) {
		TeacherEntity teacher = teacherRepository.findById(teacherId).get();

		return repository.findAllByTeacher(teacher);
	}

	public List<StudentEntity> getStudentsForParent(Integer parentId) {
		ParentEntity parent = parentRepository.findById(parentId).get();

		return repository.findAllByParent(parent);
	}
}
