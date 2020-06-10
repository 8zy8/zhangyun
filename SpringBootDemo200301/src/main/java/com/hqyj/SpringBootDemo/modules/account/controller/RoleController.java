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

import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.service.RoleService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * 127.0.0.1/role/query?roleId=2
	 */
	@RequestMapping("/query")
	public List<Role> getRoleByRoleId(@RequestParam int roleId) {
		return roleService.getRoleByRoleId(roleId);
	}

	/**
	 * 127.0.0.1/role/queryes/boss
	 */
	@RequestMapping("/queryes/{roleName}")
	public List<Role> getRoleByName(@PathVariable String roleName) {
		return roleService.getRoleByName(roleName);
	}

	/**
	 * 127.0.0.1/role/insert
	 */
	@PostMapping(value ="/insert", consumes="application/x-www-form-urlencoded")
	public Result<Role> insertRole(@ModelAttribute Role role) {
		return roleService.insertRole(role);
	}

	/**
	 * 127.0.0.1/role/del/2
	 */
	@DeleteMapping("/del/{roleId}")
	public Result<Role> deleteRoleByRoleId(@PathVariable int roleId) {
		return roleService.deleteRoleByRoleId(roleId);
	}

	/**
	 * 127.0.0.1/role/up
	 */
	@PutMapping(value="up", consumes="application/x-www-form-urlencoded")
	public Result<Role> updateRoleById(@ModelAttribute Role role) {
		return roleService.updateRoleById(role);
		
	}
	
	
	
}
