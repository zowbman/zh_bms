package com.rms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.rms.base.service.impl.BaseServiceImpl;
import com.rms.model.po.TPrivilege;
import com.rms.model.po.TPrivilegeCustom;
import com.rms.service.IPrivilegeService;

/**
 * 
 * Title:PrivilegeServiceImpl
 * Description:权限service实现类
 * @author    zwb
 * @date      2016年9月28日 下午6:29:34
 *
 */
@Service
public class PrivilegeServiceImpl extends BaseServiceImpl<TPrivilege> implements IPrivilegeService {

	@Override
	public List<TPrivilege> findChildrenPrivileges(Integer parentId) {
		Example example = new Example(TPrivilege.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("parentid", parentId);
		return tPrivilegeMapper.selectByExample(example);
	}

	@Override
	public List<TPrivilegeCustom> findPrivilegesForRecursion() {
		return tPrivilegeMapper.findPrivilegesForRecursion();
	}

	@Override
	public List<TPrivilege> findTopPrivileges() {
		Example example = new Example(TPrivilege.class);
		Criteria criteria = example.createCriteria();
		criteria.andIsNull("parentid");
		return tPrivilegeMapper.selectByExample(example);
	}
}
