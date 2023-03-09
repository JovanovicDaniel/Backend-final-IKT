package com.schoollog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoollog.entities.ParentEntity;
import com.schoollog.entities.StudentEntity;
import com.schoollog.entities.UserEntity;
import com.schoollog.entities.dto.ParentDTO;
import com.schoollog.repositories.ParentRepository;

@Service
public class ParentService {

	@Autowired
	private ParentRepository repository;

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

	public ParentEntity addNewParent(ParentDTO parent) {
		ParentEntity data = new ParentEntity();
		data.setEmail(parent.getEmail());
		data.setFirstName(parent.getFirstName());
		data.setLastName(parent.getLastName());
		UserEntity user = userService.addNewUser(parent.getUserDto());
		data.setUser(user);

		repository.save(data);

		return data;
	}

	public ParentEntity changeParent(ParentDTO parent, Integer id) {
		ParentEntity data = repository.findById(id).get();
		data.setEmail(parent.getEmail());
		data.setFirstName(parent.getFirstName());
		data.setLastName(parent.getLastName());

		UserEntity user = userService.findUserByUsername(parent.getUserDto().getUsername());
		userService.changeUser(parent.getUserDto(), user.getId());

		repository.save(data);

		return data;
	}

	public void deleteParent(Integer id) throws Exception {
		ParentEntity data = repository.findById(id).get();
		List<StudentEntity> students = studentService.getStudentsForParent(id);

		if (students.size() == 0) {
			repository.delete(data);
		} else {
			throw new Exception("Deca roditelja moraju biti obrisana pre brisanja samog roditelja.");
		}
	}

	public Iterable<ParentEntity> getAllParents() {
		return repository.findAll();
	}

	public ParentEntity findParentById(Integer id) {
		return repository.findById(id).get();
	}
}
