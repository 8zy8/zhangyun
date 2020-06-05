package com.hqyj.SpringBootDemo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;

@Mapper
public interface CityDao {

	public List<City> getCitiesByCountryId(int countryId);
	
	@Select("select * from m_city where country_id = #{countryId}")
	public List<City> getCitiesByCountryId2(int countryId);
	
	
	@Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName} ")
	public City getCityByName(String cityName,String localCityName);
	
	@Select("<script>" + 
			"select * from m_city "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ " order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by city_id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	public List<City> getCitiesBySearchVo(SearchVo searchVo);
	
	@Insert("Insert into m_city (city_name,country_id,district) value(#{cityName},#{countryId},#{district})")
	@Options(useGeneratedKeys=true, keyColumn="city_id",keyProperty="cityId")
	void insertCity(City city);
	
	@Delete("delete from m_city where city_id = #{num}")
	void deleteCityByCityId(int num);
	
	@Update("update m_city set city_name=#{cityName},country_id=#{countryId} where city_id = #{cityId}")
	void updateCityById(City city);
}
















