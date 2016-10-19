package com.rms.base.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.rms.service.IDepartmentService;
import com.rms.service.IGroupService;
import com.rms.service.IMenuService;
import com.rms.service.IPrivilegeService;
import com.rms.service.IRoleService;
import com.rms.service.IUserService;

public class BaseController {
	
	/**
	 * 用户service接口
	 */
	@Autowired
	protected IUserService iUserService;
	
	/**
	 * 菜单接口
	 */
	@Autowired
	protected IMenuService iMenuService;
	
	/**
	 * 权限接口
	 */
	@Autowired
	protected IPrivilegeService iPrivilegeService;
	
	/**
	 * 角色接口
	 */
	@Autowired
	protected IRoleService iRoleService;
	
	/**
	 * 部门接口
	 */
	@Autowired
	protected IDepartmentService iDepartmentService;
	
	/**
	 * 用户组接口
	 */
	@Autowired
	protected IGroupService iGroupService;
}
