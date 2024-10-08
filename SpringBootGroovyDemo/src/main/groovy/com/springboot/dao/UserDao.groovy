package com.springboot.dao

import org.hibernate.query.NativeQuery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import com.springboot.bean.UserBean

interface UserDao extends JpaRepository<UserBean,String> {
	
	@Query(value = "SELECT * FROM user u WHERE u.username = :username OR u.full_name = :fullName OR u.email = :email LIMIT 1",nativeQuery = true)
	Optional<UserBean> findUserBeanByUsernameOrFullNameOrEmail(@Param("username") String username, @Param("fullName") String fullName, @Param("email") String email)
	
}
