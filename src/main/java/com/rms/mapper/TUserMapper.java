package com.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.mapper.common.Mapper;

import com.rms.model.po.TDepartment;
import com.rms.model.po.TDepartmentCustom;
import com.rms.model.po.TGroup;
import com.rms.model.po.TUser;
import com.rms.model.po.TUserCustom;
import com.rms.sqlprovider.TUserSqlProvider;


/**
 * 
 * Title:TUserMapper
 * Description:用户mapper
 * @author    zwb
 * @date      2016年9月28日 上午10:08:40
 *
 */
public interface TUserMapper extends Mapper<TUser> {
	
	/**
	 * 查询全部用户信息
	 * @return List<TUser>
	 */
	@SelectProvider(type = TUserSqlProvider.class, method = "findAllUserSql")
	List<TUser> findAllUser();
	
	/**
	 * 根据id和用户名查询用户信息
	 * @param id
	 * @param userAccount 用户名
	 * @return
	 */
	@SelectProvider(type = TUserSqlProvider.class, method = "findUserByIdAndUseraccountSql")
	TUser findUserByIdAndUseraccount(Integer id, String userAccount);
	
	//一对一（用户->部门）
	@Select("SELECT * FROM t_user")
	@Results({
		@Result(property = "department",
				column = "departmentid",
				one =  @One(select = "findDepartmentById"))//如果不是同一个mapper请补上完全限定名称
	})
	List<TUserCustom> findAllUserAndDepartment();
	
	@Select("SELECT * FROM t_department WHERE id = #{departmentId}")
	TDepartment findDepartmentById(Integer departmentId);
	
	//一对多（部门->用户）
	@Select("SELECT * FROM t_department")
	@Results({
		@Result(property = "users", 
				column = "id",
				many = @Many(select = "findUserBydepartmentId"))
	})
	List<TDepartmentCustom> findAllDepartmentAndUsers();
	
	@Select("SELECT * FROM t_user WHERE departmentId = #{departmentId}")
	TUser findUserBydepartmentId(Integer departmentId);
	
	//多对多(用户->组别)
	@Select("SELECT * FROM t_user")
	@Results({
		@Result(property = "groups",
				column = "id",
				many = @Many(select = "findAllGroupsAndUsers"))
	})
	List<TUserCustom> findAllUserAndGroups();
	
	@Select("SELECT * FROM t_group tg inner join t_user_group tug on tg.id = tug.groupId inner join t_user tu on tug.userId = tu.id WHERE tu.id = #{userId}")
	List<TGroup> findAllGroupsAndUsers(Integer userId);
	
	
	
	
	
	
	
}
