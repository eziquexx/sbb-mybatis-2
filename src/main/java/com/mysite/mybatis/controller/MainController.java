package com.mysite.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.mybatis.dto.User;
import com.mysite.mybatis.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	private UserService userService;

	@GetMapping
	public String home() {
		return "index";
	}
	
	// list
	@GetMapping("/user/list")
	public String getUserList() {
		return "user/userList";
	}
	
	// detail
	@GetMapping("/user/{id}")
	public String getUserDetail() {
		return "user/userDetail";
	}
	
	// create, signup
	@GetMapping("/user/signup")
	public String userCreate() {
		return "user/userSignup";
	}
	
	// login
	@GetMapping("/user/login")
	public String userLogin() {
		return "user/userLogin";
	}
}
