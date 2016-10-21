package com.bms.rms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.bms.base.service.impl.BaseServiceImpl;
import com.bms.rms.model.po.TUser;
import com.bms.rms.service.IUserService;
import com.bms.util.BaseUtil;

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

	@Override
	public TUser findUserByUserAccountAndUserPassword(String userAccount, String userPassword) {
		Example example = new Example(TUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("useraccount", userAccount);
		criteria.andEqualTo("userpassword", DigestUtils.md5(userPassword));
		List<TUser> users = tUserMapper.selectByExample(example);
		if(users == null || users.size() != 1)
			return null;
		return users.get(0);
	}
}
