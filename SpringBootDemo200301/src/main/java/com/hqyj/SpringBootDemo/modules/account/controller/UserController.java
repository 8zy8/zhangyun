package com.hqyj.SpringBootDemo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 127.0.0.1/user/query?userId=1
	 */
	@RequestMapping("/query")
	public List<User> getUserByUserId(@RequestParam int userId) {
		return userService.getUserByUserId(userId);
	}

	
	/**
	 * 127.0.0.1/user/query
	 */
	@PostMapping(value="/query", consumes="application/x-www-form-urlencoded")
	public List<User> getUserByNamePassword(String userName, String password) {
		 List<User> users = userService.getUserByNamePassword(userName, password);
		 System.out.println("--------------------------------------------------------"+users);
		 return users;
	}
	
	
	/**
	 * 127.0.0.1/user/add
	 */
	@PostMapping(value = "/add",consumes="application/x-www-form-urlencoded")
	public Result<User> insertUser(@ModelAttribute User user) {
		return userService.insertUser(user);
	}


	/**
	 * 127.0.0.1/user/del/1
	 */
	@DeleteMapping("/del/{userId}")
	public Result<User> deleteUserByUserId(@PathVariable int userId) {
		return userService.deleteUserByUserId(userId);
	}

	
	/**
	 * 127.0.0.1/user/up             put
	 */
	@PutMapping(value = "/up", consumes="application/x-www-form-urlencoded")
	public Result<User> updateUserById(@ModelAttribute User user) {
		return userService.updateUserById(user);
	}
	
}








