package com.mysite.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mysite.mybatis.dto.User;

@Mapper
public interface UserMapper {
	List<User> getUserList();
	User getUserById(Integer id);
	void setUser(User user);
	User getUserByUsername(String username);
	User getUserPwdByUsername(String username);
}
