package com.license.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "user")
@NamedQueries({
		@NamedQuery(name = "user.findUser", query = "Select usr FROM user usr WHERE usr.username = :prm_username and usr.password = :prm_password"),
		@NamedQuery(name = "user.getUsers", query = "Select usr FROM user usr"),
		@NamedQuery(name = "user.getUserById", query = "Select usr FROM user usr where usr.id=:idUser"),
		@NamedQuery(name = "user.getUserByName", query = "Select usr FROM user usr where usr.username=:username"),
		@NamedQuery(name = "user.getRemainingUsers", query = "Select usr FROM user usr where usr.id not in (:listUsers)") })
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String string) {
		this.password = string;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
