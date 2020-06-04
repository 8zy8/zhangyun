package com.hqyj.SpringBootDemo.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.test.dao.CountryDao;
import com.hqyj.SpringBootDemo.modules.test.entity.Country;
import com.hqyj.SpringBootDemo.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryDao countryDao;
	
	public Country grtCountryByCountryId(int countryId) {
		return countryDao.getCountryByCountryId(countryId);
	}

	public Country getCountryByCountryName(String countryName) {
		return countryDao.getCountryByCountryName(countryName);
	}

	
}
