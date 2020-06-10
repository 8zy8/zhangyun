package com.hqyj.SpringBootDemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;

@Mapper
public interface UserDao {

	@Select("select * from user where user_id = #{userId}")
	List<User> getUserByUserId(int userId);
	
	@Select("select * from user where user_name=#{userName} and password=#{password} ")
	List<User> getUserByNamePassword(String userName, String password);
	
	
	
	
	@Insert("Insert into user (user_name,password,create_date) value(#{userName},#{password},#{createDate})")
	@Options(useGeneratedKeys=true, keyColumn="user_id",keyProperty="userId")
	void insertUser(User user);
	
	
	@Delete("delete from user where user_id = #{userId}")
	void deleteCityByUserId(int userId);
	
	@Update("update user set user_name=#{userName},password=#{password} where user_id = #{userId}")
	void updateUserById(User user);
}

















