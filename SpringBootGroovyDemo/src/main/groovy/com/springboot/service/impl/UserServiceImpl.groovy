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
		if(userbean.userId) {
			userbean.userId = new Random().nextInt();
		}
		userDao.save(userbean);
		return userbean.userId;
	}

	@Override
	List userList() {
		return userDao.findAll();
	}

	@Override
	UserBean getUser(user) {
		Optional<UserBean> userBean = Optional.of(new UserBean());
		if(user.isInteger()) {
			userBean =  userDao.findById(user)
		} else {
			userBean = userDao.findUserBeanByUsernameOrFullNameOrEmail(user, user, user)
		}
		if(!userBean.isPresent()) {
			null
		} else {
			userBean.get()
		}
	}
}
