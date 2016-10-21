package com.bms.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bms.rms.model.po.TRole;

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
	
	/**
	 * 新增角色权限关联
	 * @param roleId
	 * @param insertPrivilege
	 */
	@Insert("INSERT INTO t_role_privilege(roleId,privilegeId) VALUES(#{0},#{1})")
	public void insertRolePrivilege(Integer roleId, Integer insertPrivilege);
	
	/**
	 * 删除角色权限关联
	 * @param roleId
	 * @param deletePrivilege
	 */
	@Delete("DELETE FROM t_role_privilege WHERE roleId = #{0} AND privilegeId = #{1}")
	public void deleteRolePrivilegeByRoleId(Integer roleId, Integer deletePrivilege);
	
	/**
	 * 根据角色id查询用户ids
	 * @param roleId
	 * @return
	 */
	@Select("SELECT tu.id FROM t_role tr "
			+ "INNER JOIN t_user_role tur ON tr.id = tur.roleId "
			+ "INNER JOIN t_user tu ON tur.userId = tu.id WHERE tr.id = #{roleId}")
	public List<Integer> findUserIdsByRoleId(Integer roleId);
	
	/**
	 * 新增角色用户关联
	 * @param roleId
	 * @param insertUser
	 */
	@Insert("INSERT INTO t_user_role(roleId,userId) VALUES(#{0},#{1})")
	public void insertRoleUser(Integer roleId, Integer insertUser);
	
	/**
	 * 删除角色用户关联
	 * @param roleId
	 * @param deleteUser
	 */
	@Delete("DELETE FROM t_user_role WHERE roleId = #{0} AND userId = #{1}")
	public void deleteRoleUserByRoleId(Integer roleId, Integer deleteUser);
	
	/**
	 * 根据角色id查询用户组ids
	 * @param roleId
	 * @return
	 */
	@Select("SELECT tg.id FROM t_role tr "
			+ "INNER JOIN t_group_role tgr ON tr.id = tgr.roleId "
			+ "INNER JOIN t_group tg ON tgr.groupId = tg.id WHERE tr.id = #{roleId}")
	public List<Integer> findGroupIdsByRoleId(Integer roleId);
	
	/**
	 * 新增角色用户组关联
	 * @param roleId
	 * @param insertGroup
	 */
	@Insert("INSERT INTO t_group_role(roleId,groupId) VALUES(#{0},#{1})")
	public void insertRoleGroup(Integer roleId, Integer insertGroup);
	
	/**
	 * 删除角色用户组关联
	 * @param roleId
	 * @param deleteGroup
	 */
	@Delete("DELETE FROM t_group_role WHERE roleId = #{0} AND groupId = #{1}")
	public void deleteRoleGroupByRoleId(Integer roleId, Integer deleteGroup);
}