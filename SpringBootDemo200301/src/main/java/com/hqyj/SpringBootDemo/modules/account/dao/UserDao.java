package com.hqyj.SpringBootDemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;

@Mapper
public interface UserDao {

	@Select("select * from user where user_id = #{userId}")
	List<User> getUserByUserId(int userId);
	
	
	
	@Insert("Insert into user (city_name,country_id,district) value(#{cityName},#{countryId},#{district})")
	@Options(useGeneratedKeys=true, keyColumn="city_id",keyProperty="cityId")
	void insertCity(City city);
	
	@Delete("delete from m_city where city_id = #{num}")
	void deleteCityByCityId(int num);
	
	@Update("update m_city set city_name=#{cityName},country_id=#{countryId} where city_id = #{cityId}")
	void updateCityById(City city);
}
















