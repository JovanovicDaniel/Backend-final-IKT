package com.schoollog.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "teacher") 
public class TeacherDTO {
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("joinDate")
	private Date joinDate;
	@JsonProperty("user")
	private UserDTO userDto;
	
	public TeacherDTO() {
		super();
	}

	public TeacherDTO(String firstName, String lastName, Date joinDate, UserDTO userDto) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinDate = joinDate;
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

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
}
