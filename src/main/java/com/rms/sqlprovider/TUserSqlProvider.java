package com.rms.sqlprovider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

/**
 * 
 * Title:TUserSqlProvider
 * Description:user sql构造类
 * @author    zwb
 * @date      2016年9月28日 上午10:04:33
 *
 */
public class TUserSqlProvider {
	
	/**
	 * 查询全部用户信息
	 * @return String
	 */
	public String findAllUserSql(){
		//return "SELECT * FROM t_user";
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
		}}.toString();
	}
	
	/**
	 * 根据id和用户名查询用户信息
	 * @return String
	 */
	public String findUserByIdAndUseraccountSql(Map<String, Object> map){
		/*Integer id = (Integer) map.get("param1");
		String useraccount = (String) map.get("param2");*/
		Integer id = (Integer) map.get("0");
		String useraccount = (String) map.get("1");
		/*System.out.println(id);
		System.out.println(useraccount);*/
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE("id = #{id} AND userAccount = #{useraccount}");
		}}.toString();
	}
	
	/**
	 * 根据id查询用户信息和部门信息
	 * @param userId
	 * @return
	 */
	public String findUserAndDepartmentByUserIdSql(Integer userId){
		return new SQL(){{
			SELECT("*");
			FROM("t_user tu");
			INNER_JOIN("t_department td on tu.departmentid = td.id");
			WHERE("tu.id = #{userId}");
		}}.toString();
	}
}
