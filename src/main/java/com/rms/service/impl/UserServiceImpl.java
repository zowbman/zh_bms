package com.rms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rms.base.service.impl.BaseServiceImpl;
import com.rms.model.po.TUser;
import com.rms.service.IUserService;
import com.rms.util.BaseUtil;

/**
 * 
 * Title:UserServerImpl
 * Description:用户service实现类
 * @author    zwb
 * @date      2016年9月28日 下午6:29:23
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TUser> implements IUserService {

	@Override
	public List<Integer> findRoleIdsByUserId(Integer userId) {
		return tUserMapper.findRoleIdsByUserId(userId);
	}

	@Override
	public void updateUserRoleByUserId(Integer userId, List<Integer> newRoleIds) {
		List<Integer> oldRoleIds = findRoleIdsByUserId(userId);
		Map<String, Object> map = BaseUtil.compareArry(oldRoleIds, newRoleIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		
		for (Integer addId : add_arry) {
			tUserMapper.insertUserRole(userId, addId);
		}
		
		for (Integer deleteId : delete_arry) {
			tUserMapper.deleteUserRoleByUserId(userId, deleteId);
		}
	}

	@Override
	public List<Integer> findGroupIdsByUserId(Integer userId) {
		return tUserMapper.findGroupIdsByUserId(userId);
	}

	@Override
	public void updateUserGroupByUserId(Integer userId, List<Integer> newGroupIds) {
		List<Integer> oldGroupIds = findGroupIdsByUserId(userId);
		Map<String, Object> map = BaseUtil.compareArry(oldGroupIds, newGroupIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		
		for (Integer addId : add_arry) {
			tUserMapper.insertUserGroup(userId, addId);
		}
		
		for (Integer deleteId : delete_arry) {
			tUserMapper.deleteUserGroupByUserId(userId, deleteId);
		}
	}
}
