package com.bms.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bms.rms.mapper.provider.TTokenUrlInterceptorSqlProvider;
import com.bms.rms.model.po.TTokenUrlInterceptor;

/**
 * 
 * Title:TTokenUrlInterceptorMapper
 * Description:tokenurl拦截mapper
 * @author    zwb
 * @date      2016年11月8日 下午7:40:55
 *
 */
public interface TTokenUrlInterceptorMapper extends Mapper<TTokenUrlInterceptor> {
	
	/**
	 * 查询全部权限id
	 */
	@Select("SELECT privilegeId FROM t_token_url_interceptor WHERE privilegeID IS NOT NULL")
	public List<Integer> findAllPrivilegeIds();
	
	/**
	 * 新增系统默认权限token拦截
	 * @param privilegeIds
	 */
	@InsertProvider(type = TTokenUrlInterceptorSqlProvider.class, method = "saveSysDefaultUrlTokenInterceptorSql")
	public void saveSysDefaultUrlTokenInterceptor(@Param("privilegeIds")List<Integer> privilegeIds);
	
	/**
	 * 根据权限id批量删除
	 * @param privilegeIds
	 */
	@DeleteProvider(type = TTokenUrlInterceptorSqlProvider.class, method = "deleteByPrivilegeIdsSql")
	public void deleteByPrivilegeIds(@Param("privilegeIds")List<Integer> privilegeIds);
}