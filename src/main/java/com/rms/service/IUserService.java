package com.rms.service;

import java.util.List;

import com.rms.base.service.IBaseService;
import com.rms.model.po.TUser;

/**
 * 
 * Title:IUserService
 * Description:用户service接口
 * @author    zwb
 * @date      2016年9月28日 下午6:29:07
 *
 */
public interface IUserService extends IBaseService<TUser> {
	/**
	 * 根据用户查询角色ids
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer userId);
	
	/**
	 * 根据用户id更新角色
	 * @param userId
	 * @param newRoleIds
	 */
	void updateUserRoleByUserId(Integer userId, List<Integer> newRoleIds);
	
	/**
	 * 根据用户查询用户组ids
	 * @param userId
	 * @return
	 */
	List<Integer> findGroupIdsByUserId(Integer userId);
	
	/**
	 * 根据用户id更新用户组
	 * @param userId
	 * @param newGroupIds
	 */
	void updateUserGroupByUserId(Integer userId, List<Integer> newGroupIds);
}
