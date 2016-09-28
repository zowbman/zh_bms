package com.rms.service;

import java.util.List;

import com.rms.model.po.TRole;

public interface IRoleService {
	
	/**
	 * 查询所有角色信息
	 * @return List<TRole>
	 */
	List<TRole> findAllRole();
}
