package com.schoollog.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "student") 
public class StudentDTO {
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("birthDate")
	private Date birthDate; 
	@JsonProperty("classSign")
	private String classSign;
	@JsonProperty("parentId")
	private Integer parentId;
	@JsonProperty("teacherId")
	private Integer teacherId;
	@JsonProperty("user")
	private UserDTO userDto;
	
	public StudentDTO() {
		super();
	}

	public StudentDTO(String firstName, String lastName, Date birtDate, String classSign, Integer parentId,
			Integer teacherId, UserDTO userDto) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birtDate;
		this.classSign = classSign;
		this.parentId = parentId;
		this.teacherId = teacherId;
		this.userDto = userDto;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getClassSign() {
		return classSign;
	}

	public void setClassSign(String classSign) {
		this.classSign = classSign;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	
	
}
