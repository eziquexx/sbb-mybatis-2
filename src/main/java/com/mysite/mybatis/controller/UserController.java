package com.mysite.mybatis.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.mybatis.dto.User;
import com.mysite.mybatis.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	// list
	@GetMapping("/list/api")
	public List<User> getUserList() {
		return userService.getUserList();
	}
	
	// detail
	@GetMapping("/{id}/api")
	public User getUser(@PathVariable("id") Integer id) {
		return userService.getUser(id);
	}
	
	// signup
//	@PostMapping("signup/api")
//	public String userSignup(@ModelAttribute("user") User user) { // RequestBody
//		userService.setUser(user);
//		
//		return "redirect:/";
//	}
	
	
	
//		public ResponseEntity<String> userSignup2(@RequestParam("username") String username,
//				@RequestParam("userpwd") String userpwd) { // RequestBody는 json 방식 // RequestParam은 formdata
	@PostMapping("signup/api")
	public ResponseEntity<String> userSignup2(@RequestBody User user) { // RequestBody는 json 방식 // RequestParam은 formdata
		
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setUserpwd(user.getUserpwd());
		userService.setUser2(newUser);
		
		// / 경로로 이동 시키기
//		return ResponseEntity.status(HttpStatus.FOUND)
//							.location(URI.create("/"))
//							.build();
		
		return ResponseEntity.ok("환영합니다.");
	}
	
	// login
	private boolean checkPsw(String userpwd, String memberpwd) {
		return BCrypt.checkpw(userpwd, memberpwd);
//		return psw1.equals(psw2);
	}
	
	@PostMapping("login/api")
	public ResponseEntity<?> userLogin(@RequestBody User user) {
		User member = userService.getUserPwd(user.getUsername());
		if (member != null) {
			if (checkPsw(user.getUserpwd(), member.getUserpwd())) {
				// 로그인 성공
				return ResponseEntity.ok().body(Map.of("message", "로그인 성공", "user", member));
			} else {
				// 로그인 실패
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "비밀번호가 일치하지 않습니다."));
			}
		} else {
			// 사용자를 찾을 수 없습니다.
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "사용자를 찾을 수 없습니다."));
		}
	}
}
