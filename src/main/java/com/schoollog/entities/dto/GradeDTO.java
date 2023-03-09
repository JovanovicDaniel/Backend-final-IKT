package com.schoollog.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "grade") 
public class GradeDTO {

	@JsonProperty("number")
	private Integer number;
	@JsonProperty("semester")
	private Integer semester;
	@JsonProperty("studentId")
	private Integer studentId;
	@JsonProperty("subjectId")
	private Integer subjectId;
	@JsonProperty("teacherId")
	private Integer teacherId;
	
	public GradeDTO() {
		super();
	}

	public GradeDTO(Integer number, Integer semester, Integer studentId, Integer subjectId, Integer teacherId) {
		super();
		this.number = number;
		this.semester = semester;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.teacherId = teacherId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer grade) {
		this.number = grade;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	

	
}
