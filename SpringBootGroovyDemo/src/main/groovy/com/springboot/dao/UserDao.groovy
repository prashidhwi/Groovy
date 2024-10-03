package com.springboot.dao

import org.springframework.data.jpa.repository.JpaRepository

import com.springboot.bean.UserBean

interface UserDao extends JpaRepository<UserBean,String> {
	
}
