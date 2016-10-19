package com.rms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rms.base.service.impl.BaseServiceImpl;
import com.rms.model.po.TRole;
import com.rms.service.IRoleService;

/**
 * 
 * Title:RoleServiceImpl
 * Description:角色service实现类
 * @author    zwb
 * @date      2016年10月19日 下午1:00:39
 *
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<TRole> implements IRoleService {

	@Override
	public List<Integer> findPrivilegeIdsByRoleId(Integer roleId) {
		return tRoleMapper.findPrivilegeIdsByRoleId(roleId);
	}
}
