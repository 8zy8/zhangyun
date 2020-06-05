package com.hqyj.SpringBootDemo.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.dao.CityDao;
import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;
import com.hqyj.SpringBootDemo.utils.RedisUtils;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityDao cityDao;
	@Autowired
	private RedisUtils redisUtils;
	
	public List<City> getCitiesByCountryId2(int countryId) {
		return Optional.ofNullable(cityDao.getCitiesByCountryId2(countryId))
				.orElse(Collections.emptyList());
	}

	
	public City getCityByName(String cityName, String localCityName) {
		return cityDao.getCityByName(cityName, localCityName);
	}


	public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId) {
		PageHelper.startPage(currentPage,pageSize);
		
		return new PageInfo<City>(
				cityDao.getCitiesByCountryId2(countryId));
	}


	public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo(searchVo);
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<City>(
				Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}


	public Result<City> insertCity(City city) {
//		city.setDateCreated(new Date());
		cityDao.insertCity(city);
		return new Result<City>(ResultStatus.SUCCESS.status, "success", city);
	}


	public Result<Object> deleteCityByCityId(int num) {
		cityDao.deleteCityByCityId(num);
		return new Result<Object>(ResultStatus.SUCCESS.status, "success");
	}


	public Result<City> updateCityById(City city) {
		cityDao.updateCityById(city);;
		return new Result<City>(ResultStatus.SUCCESS.status, "success",city);
	}


	public Object migrateCitiesByCountryId(int countryId) {
		List<City> cities = getCitiesByCountryId2(countryId);
		redisUtils.set("cities", cities);
		return redisUtils.get("cities");
	}
}
