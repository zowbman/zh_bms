package com.rms.service;

import java.util.List;

import com.rms.base.service.IBaseService;
import com.rms.model.po.TRole;

/**
 * 
 * Title:IRoleService
 * Description:角色接口
 * @author    zwb
 * @date      2016年10月19日 下午12:59:00
 *
 */
public interface IRoleService extends IBaseService<TRole> {
	
	/**
	 * 根据角色查询权限ids
	 * @param roleId
	 * @return
	 */
	List<Integer> findPrivilegeIdsByRoleId(Integer roleId);
	
	/**
	 * 根据角色id更新角色权限
	 * @param roleId
	 * @param newPrivilegeIds
	 */
	void updateRolePrivilegeByRoleId(Integer roleId, List<Integer> newPrivilegeIds);
	
	/**
	 * 根据角色查询用户ids
	 * @param roleId
	 * @return
	 */
	List<Integer> findUserIdsByRoleId(Integer roleId);
	
	/**
	 * 根据角色id更新角色用户
	 * @param roleId
	 * @param newUserIds
	 */
	void updateRoleUserByRoleId(Integer roleId, List<Integer> newUserIds);
}
