package com.rms.service;

import java.util.List;

import com.rms.base.service.IBaseService;
import com.rms.model.po.TPrivilege;
import com.rms.model.po.TPrivilegeCustom;

/**
 * 
 * Title:IPrivilegeService
 * Description:权限service接口
 * @author    zwb
 * @date      2016年9月28日 下午6:28:56
 *
 */
public interface IPrivilegeService extends IBaseService<TPrivilege> {
	/**
	 * 查询子级从权限
	 * @param parentId 父级id
	 * @return
	 */
	List<TPrivilege> findChildrenPrivileges(Integer parentId);
	
	/**
	 * 递归查询权限
	 * @return
	 */
	List<TPrivilegeCustom> findPrivilegesForRecursion();
	
	/**
	 * 查询顶级权限
	 * @return
	 */
	List<TPrivilege> findTopPrivileges();
}
