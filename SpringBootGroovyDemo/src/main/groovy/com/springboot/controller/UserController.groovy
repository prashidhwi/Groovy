package com.springboot.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.springboot.bean.UserBean
import com.springboot.service.UserService

@RestController
@RequestMapping("/user")
class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/list")
	List users() {
		userService.userList()	
	}
	
	@PostMapping("/create")
	String createUser(@RequestBody UserBean user) {
		userService.insertUser(user);
	}
}
