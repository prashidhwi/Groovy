package com.springboot.bean

import groovy.transform.ToString
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@ToString
@Entity
@Table(name="user")
class UserBean implements Serializable{
	
	@Id
	@Column(name="user_id")
	int userId;
	
	@Column(name="username")
	@NotNull(message="Username is mandatory.")
	@Size(max=25 , message="Username must be less than 25 characters.")
	String username;
	
	@Column(name="full_name")
	@NotNull(message="Full Name is mandatory")
	@Size(max=25 , message="Full Name must be less than 25 characters.")
	String fullName;
	
	@Column(name="email")
	@NotNull(message="email is mandatory")
	@Email(message="Please enter valid email.")
	String email;
	
}

