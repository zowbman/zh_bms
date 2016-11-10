package com.bms.rms.model.vo;

import com.bms.rms.model.po.TTokenUrlInterceptor;

/**
 * 
 * Title:TTokenUrlInterceptorVo
 * Description:token拦截Vo包装类
 * @author    zwb
 * @date      2016年11月10日 下午4:01:21
 *
 */
public class TTokenUrlInterceptorVo {
	
	private TTokenUrlInterceptor tokenUrlInterceptor;
	
	private Integer[] privilegeIds;

	public TTokenUrlInterceptor getTokenUrlInterceptor() {
		return tokenUrlInterceptor;
	}

	public void setTokenUrlInterceptor(TTokenUrlInterceptor tokenUrlInterceptor) {
		this.tokenUrlInterceptor = tokenUrlInterceptor;
	}

	public Integer[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Integer[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
}
