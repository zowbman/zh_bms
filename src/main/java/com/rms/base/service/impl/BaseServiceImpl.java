package com.rms.base.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.common.Mapper;

import com.rms.base.service.IBaseService;
import com.rms.mapper.TRoleMapper;
import com.rms.mapper.TUserMapper;

@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	
	//泛型类
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		super();
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	/**
	 * 注入通用mapper
	 */
	@Autowired
	private Mapper<T> mapper;
	
	@Autowired
	protected TUserMapper tUserMapper;
	
	public void save(T entity) {
		mapper.insert(entity);
	}
	
	public void saveSeletive(T entity) {
		mapper.insertSelective(entity);
	}

	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			mapper.deleteByPrimaryKey(id);
		}
	}
	public void update(T entity) {
		mapper.updateByPrimaryKey(entity);
	}

	public void updateSeletive(T entity) {
		mapper.updateByPrimaryKeySelective(entity);
	}

	public T getById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	public List<T> getByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findAll() {
		return mapper.selectAll();
	}

	@Override
	public int getTotal(T entity) {
		return mapper.selectCount(entity);
	}
}
