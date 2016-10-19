package com.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.rms.model.po.TPrivilege;
import com.rms.model.po.TPrivilegeCustom;

/**
 * 
 * Title:TPrivilegeMapper
 * Description:权限Mapper接口
 * @author    zwb
 * @date      2016年10月19日 上午10:30:16
 *
 */
public interface TPrivilegeMapper extends Mapper<TPrivilege> {
	
	/**
	 * 递归查询权限
	 * @return List<TPrivilegeCustom>
	 */
	@Select("SELECT * FROM t_privilege WHERE parentId is NULL")
	@Results({
		@Result(property = "id", column="id"),
		@Result(property = "childrenPrivileges",
				column = "id",
				many = @Many(select = "findPrivilegesByParentId")),
	})
	public List<TPrivilegeCustom> findPrivilegesForRecursion();
	
	/**
	 * 根据父id查询权限
	 * @param parentId 父权限id
	 * @return
	 */
	@Select("SELECT * FROM t_privilege WHERE parentId = #{parentId}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "childrenPrivileges",
				column = "id",
				many = @Many(select = "findPrivilegesByParentId"))
	})
	public List<TPrivilegeCustom> findPrivilegesByParentId(Integer parentId);
	
}