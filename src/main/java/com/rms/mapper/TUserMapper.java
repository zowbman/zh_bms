package com.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.rms.model.po.TUser;

/**
 * 
 * Title:TUserMapper
 * Description:用户mapper接口
 * @author    zwb
 * @date      2016年9月28日 下午6:30:31
 *
 */
public interface TUserMapper extends Mapper<TUser> {
	
	/**
	 * 根据用户id查询角色ids
	 * @param userId
	 * @return
	 */
	@Select("SELECT tr.id FROM t_role tr "
			+ "INNER JOIN t_user_role tur ON tr.id = tur.roleId "
			+ "INNER JOIN t_user tu ON tur.userId = tu.id WHERE tu.id = #{userId}")
	public List<Integer> findRoleIdsByUserId(Integer userId);
	
	/**
	 * 新增用户角色关联
	 * @param userId
	 * @param insertRole
	 */
	@Insert("INSERT INTO t_user_role(userId,RoleId) VALUES(#{0},#{1})")
	public void insertUserRole(Integer userId, Integer insertRole);
	
	/**
	 * 删除用户角色关联
	 * @param userId
	 * @param deleteRole
	 */
	@Delete("DELETE FROM t_user_role WHERE userId = #{0} AND roleId = #{1}")
	public void deleteUserRoleByUserId(Integer userId, Integer deleteRole);
	
	/**
	 * 根据用户id查询用户组ids
	 * @param userId
	 * @return
	 */
	@Select("SELECT tg.id FROM t_group tg "
			+ "INNER JOIN t_user_group tug ON tg.id = tug.groupId "
			+ "INNER JOIN t_user tu ON tug.userId = tu.id WHERE tu.id = #{userId}")
	public List<Integer> findGroupIdsByUserId(Integer userId);
	
	/**
	 * 新增用户用户组关联
	 * @param userId
	 * @param insertGroup
	 */
	@Insert("INSERT INTO t_user_group(userId,GroupId) VALUES(#{0},#{1})")
	public void insertUserGroup(Integer userId, Integer insertGroup);
	
	/**
	 * 删除用户用户组关联
	 * @param userId
	 * @param deleteGroup
	 */
	@Delete("DELETE FROM t_user_group WHERE userId = #{0} AND groupId = #{1}")
	public void deleteUserGroupByUserId(Integer userId, Integer deleteGroup);
}
