package com.rms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rms.base.service.impl.BaseServiceImpl;
import com.rms.model.po.TDepartmentCustom;
import com.rms.model.po.TUser;
import com.rms.model.po.TUserCustom;
import com.rms.service.IUserService;

@Service
public class UserServerImpl extends BaseServiceImpl<TUser> implements IUserService {

	@Override
	public List<TUserCustom> findAllUserAndDepartment() {
		return tUserMapper.findAllUserAndDepartment();
	}

	@Override
	public List<TDepartmentCustom> findAllDepartmentAndUsers() {
		return tUserMapper.findAllDepartmentAndUsers();
	}
}
