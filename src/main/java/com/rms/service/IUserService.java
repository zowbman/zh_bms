package com.rms.service;

import java.util.List;

import com.rms.base.service.IBaseService;
import com.rms.model.po.TDepartmentCustom;
import com.rms.model.po.TUser;
import com.rms.model.po.TUserCustom;

public interface IUserService extends IBaseService<TUser> {
	
	/**
	 * 查询全部用户信息和对应部门信息
	 * @return
	 */
	List<TUserCustom> findAllUserAndDepartment();
	
	/**
	 * 查询全部用户信息和对应部门信息
	 * @return
	 */
	List<TDepartmentCustom> findAllDepartmentAndUsers();
}
