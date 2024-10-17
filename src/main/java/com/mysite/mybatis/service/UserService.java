package com.mysite.mybatis.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.mybatis.dto.User;
import com.mysite.mybatis.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	// list
	public List<User> getUserList() {
		return userMapper.getUserList();
	}
	
	// detail
	public User getUser(Integer id) {
		return userMapper.getUserById(id);
	}
	
	// signup
//	public void setUser(User user) {
//		userMapper.setUser(user);
//	}
	
	//signup2
	public void setUser2(User user) {
		String salt = BCrypt.gensalt();
		String encPwd = BCrypt.hashpw(user.getUserpwd(), salt);
		user.setUserpwd(encPwd);
		
		userMapper.setUser(user);
	}
	
	// login
	public boolean checkUser(User user) {
		User member = getUser2(user);
		return BCrypt.checkpw(user.getUserpwd(), member.getUserpwd());
	}

	public User getUser2(User user) {
		return userMapper.getUserByUsername(user.getUsername());
	}
	
	public User getUserPwd(String username) {
		return userMapper.getUserPwdByUsername(username);
	}
	
	
}
