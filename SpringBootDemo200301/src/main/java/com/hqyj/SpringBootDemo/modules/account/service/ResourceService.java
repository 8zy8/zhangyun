package com.hqyj.SpringBootDemo.modules.account.service;

import java.util.List;


import com.hqyj.SpringBootDemo.modules.account.entity.Resource;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

public interface ResourceService {

	
	List<Resource> getResourceByResourceId(int resourceId);
	
	List<Resource> getResourceByName(String resourceName);
	
	Result<Resource> insertResource(Resource resource);
	
	Result<Resource> deleteResourceByResourceId(int resourceId);
	
	Result<Resource> updateResourceById(Resource resource);
	
}
