package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.account.dao.ResourceDao;
import com.hqyj.SpringBootDemo.modules.account.entity.Resource;
import com.hqyj.SpringBootDemo.modules.account.service.ResourceService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;
	
	
	public List<Resource> getResourceByResourceId(int resourceId) {
		return resourceDao.getResourceByResourceId(resourceId);
	}

	
	public List<Resource> getResourceByName(String resourceName) {
		return resourceDao.getResourceByName(resourceName);
	}

	
	
	public Result<Resource> insertResource(Resource resource) {
		resourceDao.insertResource(resource);
		return new Result<Resource>(ResultStatus.SUCCESS.status, "success", resource);
	}


	public Result<Resource> deleteResourceByResourceId(int resourceId) {
		resourceDao.deleteResourceByResourceId(resourceId);
		return new Result<Resource>(ResultStatus.SUCCESS.status, "success");
	}


	
	public Result<Resource> updateResourceById(Resource resource) {
		resourceDao.updateResourceById(resource);
		return new Result<Resource>(ResultStatus.SUCCESS.status, "success", resource);
	}

}
