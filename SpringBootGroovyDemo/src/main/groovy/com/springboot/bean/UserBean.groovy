package com.springboot.bean

import groovy.transform.ToString
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@ToString
@Entity
@Table(name="user")
class UserBean implements Serializable{
	
	@Id
	@Column(name="user_id")
	int userId;
	@Column(name="username")
	String username;
	@Column(name="full_name")
	String fullName;
	@Column(name="email")
	String email;
	
}

