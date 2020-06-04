package com.hqyj.SpringBootDemo.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;


public interface CityService {
	
	public List<City> getCitiesByCountryId2(int countryId);
	
	public City getCityByName(String cityName,String localCityName);
	
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);
	
	PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);
}













