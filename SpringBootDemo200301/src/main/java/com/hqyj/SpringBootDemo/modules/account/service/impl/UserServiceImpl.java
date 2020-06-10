package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.account.dao.UserDao;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public List<User> getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}

	public List<User> getUserByNamePassword(String userName, String password) {
		return userDao.getUserByNamePassword(userName, password);
	}
	
	public Result<User> insertUser(User user) {
		userDao.insertUser(user);
		return new Result<User>(ResultStatus.SUCCESS.status, "success", user);
	}

	public Result<User> deleteUserByUserId(int userId) {
		userDao.deleteCityByUserId(userId);
		return new Result<User>(ResultStatus.SUCCESS.status, "success");
	}

	public Result<User> updateUserById(User user) {
		userDao.updateUserById(user);
		return new Result<User>(ResultStatus.SUCCESS.status, "success",user);
	}


	

}
