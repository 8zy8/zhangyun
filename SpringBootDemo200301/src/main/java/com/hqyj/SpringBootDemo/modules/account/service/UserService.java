package com.hqyj.SpringBootDemo.modules.account.service;

import java.util.List;


import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.test.entity.City;

public interface UserService {

	
	List<User> getUserByUserId(int userId);
	
	List<User> getUserByNamePassword(String userName, String password);
	
	Result<User> insertUser(User user);
	
	Result<User> deleteUserByUserId(int userId);
	
	Result<User> updateUserById(User user);
}
