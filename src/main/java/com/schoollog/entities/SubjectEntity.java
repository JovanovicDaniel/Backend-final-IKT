package com.schoollog.entities;

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
@Table(name = "subject")
public class SubjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "subject_id")
	private Integer id;
	@Column(name = "subject_name")
	private String name;
	@Column(name = "subject_class_fund")
	private Integer classFund;
	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<GradeEntity> grades = new ArrayList<GradeEntity>();
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private TeacherEntity teacher;
	
	public SubjectEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getClassFund() {
		return classFund;
	}

	public void setClassFund(Integer classFund) {
		this.classFund = classFund;
	}

	public List<GradeEntity> getGrades() {
		return grades;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}
	
	
}

