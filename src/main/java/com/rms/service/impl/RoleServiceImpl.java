package com.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.mapper.TRoleMapper;
import com.rms.model.po.TRole;
import com.rms.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	protected TRoleMapper tRoleMapper;

	@Override
	public List<TRole> findAllRole() {
		return tRoleMapper.findAllRole();
	}
	
}
