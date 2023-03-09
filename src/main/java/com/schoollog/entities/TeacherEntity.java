package com.schoollog.entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class TeacherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "teacher_id")
	private Integer id;
	@Column(name = "teacher_first_name")
	private String firstName;
	@Column(name = "teacher_last_name")
	private String lastName;
	@Column(name = "teacher_join_date")
	private Date joinDate;
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<GradeEntity> grades = new ArrayList<GradeEntity>();
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<StudentEntity> students = new ArrayList<StudentEntity>();
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	public TeacherEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public List<GradeEntity> getGrades() {
		return grades;
	}

	public List<StudentEntity> getStudents() {
		return students;
	}

	public List<SubjectEntity> getSubjects() {
		return subjects;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}	
	
	
}
