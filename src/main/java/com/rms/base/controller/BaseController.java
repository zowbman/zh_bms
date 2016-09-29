package com.rms.base.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.rms.service.IMenuService;
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
}
