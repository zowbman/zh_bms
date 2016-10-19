package com.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.rms.model.po.TRole;

/**
 * 
 * Title:TRoleMapper
 * Description:角色mapper接口
 * @author    zwb
 * @date      2016年10月19日 下午1:01:28
 *
 */
public interface TRoleMapper extends Mapper<TRole> {
	
	/**
	 * 根据角色id查询权限ids
	 * @param roleId
	 * @return
	 */
	@Select("SELECT tp.id FROM t_role tr "
			+ "INNER JOIN t_role_privilege trp ON tr.id = trp.roleId "
			+ "INNER JOIN t_privilege tp ON trp.privilegeId = tp.id WHERE tr.id = #{roleIds}")
	public List<Integer> findPrivilegeIdsByRoleId(Integer roleId);
}