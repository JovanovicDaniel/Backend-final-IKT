package com.schoollog.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.schoollog.security.Views;

@JsonRootName(value = "gradeDetails") 
public class GradeDetailsDTO {
	@JsonView(Views.Student.class) 
	@JsonProperty("grade")
	private Integer grade;
	@JsonView(Views.Student.class) 
	@JsonProperty("semester")
	private Integer semester;
	@JsonView(Views.Parent.class) 
	@JsonProperty("student")
	private String student;
	@JsonView(Views.Teacher.class) 
	@JsonProperty("classSign")
	private String classSign;
	@JsonView(Views.Student.class)
	@JsonProperty("subject")
	private String subject;
	@JsonView(Views.Parent.class) 
	@JsonProperty("teacher")
	private String teacher;
	@JsonView(Views.Teacher.class) 
	@JsonProperty("parent")
	private String parent;
	@JsonView(Views.Teacher.class) 
	@JsonProperty("parentEmail")
	private String parentEmail;
	
	public GradeDetailsDTO() {
		super();
	}

	public GradeDetailsDTO(Integer grade, Integer semester, String student, String classSign, String subject,
			String teacher, String parent, String parentEmail) {
		super();
		this.grade = grade;
		this.semester = semester;
		this.student = student;
		this.classSign = classSign;
		this.subject = subject;
		this.teacher = teacher;
		this.parent = parent;
		this.parentEmail = parentEmail;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getClassSign() {
		return classSign;
	}

	public void setClassSign(String classSign) {
		this.classSign = classSign;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}	
	
	
	
}
