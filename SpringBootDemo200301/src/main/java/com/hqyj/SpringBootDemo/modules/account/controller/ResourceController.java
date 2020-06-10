package com.hqyj.SpringBootDemo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.SpringBootDemo.modules.account.entity.Resource;
import com.hqyj.SpringBootDemo.modules.account.service.ResourceService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;


@RestController
@RequestMapping("/resource")
public class ResourceController {

	
	@Autowired
	private ResourceService resourceService;
	
	
	/**
	 * 127.0.0.1/resource/query/1
	 */
	@RequestMapping("/query/{resourceId}")
	public List<Resource> getResourceByResourceId(@PathVariable int resourceId) {
		return resourceService.getResourceByResourceId(resourceId);
	}

	
	/**
	 * 127.0.0.1/resource/queryes?resourceName=baidu
	 */
	@RequestMapping("/queryes")
	public List<Resource> getResourceByName(@RequestParam String resourceName) {
		return resourceService.getResourceByName(resourceName);
	}

	
	/**
	 * 127.0.0.1/resource/insert
	 */
	@PostMapping(value="/insert",consumes="application/x-www-form-urlencoded")
	public Result<Resource> insertResource(@ModelAttribute Resource resource) {
		return resourceService.insertResource(resource);
	}


	/**
	 * 127.0.0.1/resource/del?resourceId=3
	 */
	@DeleteMapping("del")
	public Result<Resource> deleteResourceByResourceId(@RequestParam int resourceId) {
		return resourceService.deleteResourceByResourceId(resourceId);
	}


	/**
	 * 127.0.0.1/resource/up
	 */
	@PutMapping(value="/up",consumes="application/x-www-form-urlencoded")
	public Result<Resource> updateResourceById(@ModelAttribute Resource resource) {
		return resourceService.updateResourceById(resource);
	}

}
