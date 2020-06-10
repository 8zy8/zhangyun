package com.hqyj.SpringBootDemo.modules.account.service;

import java.util.List;


import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

public interface RoleService {

	
	List<Role> getRoleByRoleId(int roleId);
	
	
	List<Role> getRoleByName(String roleName);
	
	Result<Role> insertRole(Role role);
	
	Result<Role> deleteRoleByRoleId(int roleId);
	
	Result<Role> updateRoleById(Role role);
}
