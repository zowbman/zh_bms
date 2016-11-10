package com.bms.rms.mapper.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.boboface.base.data.type.conversion.StringTypeConversion;

/**
 * 
 * Title:TTokenUrlInterceptorSqlProvider
 * Description:TTokenUrlInterceptor sql构造
 * @author    zwb
 * @date      2016年11月10日 下午4:27:19
 *
 */
public class TTokenUrlInterceptorSqlProvider {
	
	/**
	 * 批量插入系统默认权限token拦截
	 * @param map
	 * @return
	 */
	public String saveSysDefaultUrlTokenInterceptorSql(Map<String, Object> map){
		final List<Integer> privilegeIds =   (List<Integer>) map.get("privilegeIds");
		StringBuilder stringBuilder = new StringBuilder(256);
		stringBuilder.append("INSERT INTO t_token_url_interceptor(privilegeId) values");
		MessageFormat messageFormat = new MessageFormat("(#'{'privilegeIds[{0}]}')");
		for (int i = 0; i < privilegeIds.size(); i++) {
			stringBuilder.append(messageFormat.format(new Integer[]{i}));
			stringBuilder.append(",");
		}
		stringBuilder.setLength(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}
	
	/**
	 * 根据权限id删除token拦截
	 * @param map
	 */
	public String deleteByPrivilegeIdsSql(Map<String, Object> map){
		final List<Integer> privilegeIds =   (List<Integer>) map.get("privilegeIds");
		return new SQL(){{
			String whereStr = "1 = 0";
			if (privilegeIds != null && privilegeIds.size() > 0) {	
				whereStr = "privilegeId in (" + StringTypeConversion.listStrToString(privilegeIds, ",") + ")";
			}
			DELETE_FROM("t_token_url_interceptor");
			WHERE(whereStr);
		}}.toString();
	}
}
