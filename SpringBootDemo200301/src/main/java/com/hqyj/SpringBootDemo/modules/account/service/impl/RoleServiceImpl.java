package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.account.dao.RoleDao;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.service.RoleService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	public List<Role> getRoleByRoleId(int roleId) {
		return roleDao.getRoleByRoleId(roleId);
	}

	public List<Role> getRoleByName(String roleName) {
		return roleDao.getRoleByName(roleName);
	}

	public Result<Role> insertRole(Role role) {
		roleDao.insertRole(role);
		return new Result<Role>(ResultStatus.SUCCESS.status, "success", role);
	}

	public Result<Role> deleteRoleByRoleId(int roleId) {
		roleDao.deleteRoleByRoleId(roleId);
		return new Result<Role>(ResultStatus.SUCCESS.status, "success");
	}

	public Result<Role> updateRoleById(Role role) {
		roleDao.updateRoleById(role);
		return new Result<Role>(ResultStatus.SUCCESS.status, "success", role);
		
	}

}











