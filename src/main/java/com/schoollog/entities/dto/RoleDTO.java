package com.schoollog.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "role") 
public class RoleDTO {
	@JsonProperty("name")
	private String name;

	public RoleDTO() {
		super();
	}

	public RoleDTO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
