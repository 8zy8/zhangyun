package com.hqyj.SpringBootDemo.modules.test.service;

import java.util.List;


import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;


public interface CityService {
	
	public List<City> getCitiesByCountryId2(int countryId);
	
	public City getCityByName(String cityName,String localCityName);
	
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);
	
	PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);
	
	Result<City> insertCity(City city);
	
	Result<Object> deleteCityByCityId(int num);
	
	Result<City> updateCityById(City city);
	
	Object migrateCitiesByCountryId(int countryId);
}