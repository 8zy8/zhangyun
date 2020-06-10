package com.hqyj.SpringBootDemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;

@Mapper
public interface RoleDao {

	@Select("select * from role where role_id = #{roleId}")
	List<Role> getRoleByRoleId(int roleId);
	
	
	@Select("select * from role where role_name=#{roleName}")
	List<Role> getRoleByName(String roleName);
	
	
	@Insert("Insert into role (role_name) value(#{roleName})")
	@Options(useGeneratedKeys=true, keyColumn="role_id",keyProperty="roleId")
	void insertRole(Role role);
	
	@Delete("delete from role where role_id = #{roleId}")
	void deleteRoleByRoleId(int roleId);
	
	@Update("update role set role_name=#{roleName} where role_id = #{roleId}")
	void updateRoleById(Role role);
	
	
}
