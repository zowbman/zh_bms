package com.bms.base.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bms.rms.service.IDepartmentService;
import com.bms.rms.service.IGroupService;
import com.bms.rms.service.IMenuService;
import com.bms.rms.service.IPrivilegeButtonService;
import com.bms.rms.service.IPrivilegeService;
import com.bms.rms.service.IRoleService;
import com.bms.rms.service.IUserService;

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
	
	/**
	 * 按钮级别权限控制接口
	 */
	@Autowired
	protected IPrivilegeButtonService iPrivilegeButtonService;
	
	/*@Autowired
	protected RedisClient redisClient;*/
}
