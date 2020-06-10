package com.hqyj.SpringBootDemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBootDemo.modules.account.entity.Resource;

@Mapper
public interface ResourceDao {

	@Select("select * from resource where resource_id = #{resourceId}")
	List<Resource> getResourceByResourceId(int resourceId);
	
	
	@Select("select * from resource where resource_name=#{resourceName}")
	List<Resource> getResourceByName(String resourceName);
	
	
	@Insert("Insert into resource (resource_uri,resource_name,permission) value(#{resourceUri},#{resourceName},#{permission})")
	@Options(useGeneratedKeys=true, keyColumn="resource_id",keyProperty="resourceId")
	void insertResource(Resource resource);
	
	@Delete("delete from resource where resource_id = #{resourceId}")
	void deleteResourceByResourceId(int resourceId);
	
	@Update("update resource set resource_uri=#{resourceUri},resource_name=#{resourceName},permission=#{permission} where resource_id = #{resourceId}")
	void updateResourceById(Resource resource);
	
}
