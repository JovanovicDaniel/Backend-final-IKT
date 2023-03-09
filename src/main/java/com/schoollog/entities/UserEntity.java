package com.schoollog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "user")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "user_id")
	private Integer id;
	@Column(name = "user_email") 
	private String email;
	@Column(name = "user_username") 
	private String username; 
	@Column(name = "user_password") 
	private String password;
	@Column(name = "user_first_name") 
	private String firstName;
	@Column(name = "user_last_name") 
	private String lastName;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private RoleEntity role;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<StudentEntity> students = new ArrayList<StudentEntity>();
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<TeacherEntity> teachers = new ArrayList<TeacherEntity>();
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<ParentEntity> parents = new ArrayList<ParentEntity>();
	
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	
	
}
