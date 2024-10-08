package com.springboot.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import com.springboot.bean.UserBean
import com.springboot.exception.bean.ErrorResponse
import com.springboot.service.UserService

import jakarta.validation.Valid

@RestController
@RequestMapping("/user")
class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/list")
	ResponseEntity<?> users() {
		userService.userList()	
	}
	
	@GetMapping("/{user}")
	ResponseEntity<?> users(@PathVariable user) {
		println user.getClass().name
		UserBean userBean = userService.getUser(user)
		if(!userBean)
			new ResponseEntity<>(new ErrorResponse(200, "No User Found for ${user}", null),HttpStatus.OK)
		else 
			new ResponseEntity<>(userBean,HttpStatus.OK)
	}
	
	@PostMapping("/create")
	String createUser(@Valid @RequestBody UserBean user) {
		userService.insertUser(user);
	}
}
