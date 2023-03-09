package com.schoollog.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "subject") 
public class SubjectDTO {
	@JsonProperty("name")
	private String name;
	@JsonProperty("classFund")
	private Integer classFund;
	@JsonProperty("teacherId")
	private Integer teacherId;
	
	public SubjectDTO() {
		super();
	}

	public SubjectDTO(String name, Integer classFund, Integer teacherId) {
		super();
		this.name = name;
		this.classFund = classFund;
		this.teacherId = teacherId;
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

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
}
