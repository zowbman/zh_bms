package com.rms.service;

import java.util.List;

import com.rms.base.service.IBaseService;
import com.rms.model.po.TGroup;

/**
 * 
 * Title:IGroupService
 * Description:组别service接口
 * @author    zwb
 * @date      2016年9月28日 下午6:28:34
 *
 */
public interface IGroupService extends IBaseService<TGroup> {
	 
	/**
	 * 根据用户组查询角色ids
	 * @param groupId
	 * @return
	 */
	List<Integer> findRoleIdsByGroupId(Integer groupId);
	
	/**
	 * 根据用户组id更新角色
	 * @param groupId
	 * @param newRoleIds
	 */
	void updateGroupRoleByGroupId(Integer groupId, List<Integer> newRoleIds);
	
	/**
	 * 根据用户组查询用户ids
	 * @param groupId
	 * @return
	 */
	List<Integer> findUserIdsByGroupId(Integer groupId);
	
	/**
	 * 根据用户组id更新角用户
	 * @param groupId
	 * @param newUserIds
	 */
	void updateGroupUserByGroupId(Integer groupId, List<Integer> newUserIds);
}
