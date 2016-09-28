package com.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.rms.model.po.TRole;

/**
 * 
 * Title:TRoleMapper
 * Description:查询所有角色信息
 * @author    zwb
 * @date      2016年9月28日 下午4:06:27
 *
 */
public interface TRoleMapper {
	
	@Select("SELECT * FROM t_role")
	List<TRole> findAllRole();
}
