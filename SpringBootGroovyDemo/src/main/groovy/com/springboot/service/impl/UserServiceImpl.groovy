package com.springboot.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.springboot.bean.UserBean
import com.springboot.dao.UserDao
import com.springboot.service.UserService

@Service
class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	int insertUser(UserBean userbean) {
		userDao.save(userbean);
		return userbean.userId;
	}

	@Override
	public List userList() {
		return userDao.findAll();
	}
}
