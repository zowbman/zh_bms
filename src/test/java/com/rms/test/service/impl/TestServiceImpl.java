package com.rms.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.test.mapper.UserMapper;
import com.rms.test.model.po.User;
import com.rms.test.service.ITestSerive;

@Service
public class TestServiceImpl implements ITestSerive {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
}
