package com.springboot.service

import com.springboot.bean.UserBean

interface UserService {
	
	int insertUser(UserBean userbean)
	
	List userList();
}
