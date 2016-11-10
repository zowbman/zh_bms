package com.bms.rms.service;

import java.util.List;

import com.bms.base.service.IBaseService;
import com.bms.rms.model.po.TTokenUrlInterceptor;

/**
 * 
 * Title:ITokenUrlInterceptorService
 * Description:token url 拦截service 接口
 * @author    zwb
 * @date      2016年11月8日 下午7:43:03
 *
 */
public interface ITokenUrlInterceptorService extends IBaseService<TTokenUrlInterceptor> {
	
	/**
	 * 查询全部系统默认url token拦截
	 * @return
	 */
	public List<Integer> findAllSysDefaultUrlTokenInterceptor();
	
	/**
	 * 查询全部自定义url token拦截
	 * @return
	 */
	public List<TTokenUrlInterceptor> findAllCustomUrlTokenInterceptor();
	
	/**
	 * 保存系统默认urlToken拦截
	 * @param privilegeIds
	 */
	public void saveSysDefaultUrlTokenInterceptor(List<Integer> newPrivilegeIds);
	
	
	/**
	 * 删除token url 拦截
	 * @param id
	 * @return
	 */
	public boolean deleteTokenUrlInterceptor(Integer id);
}
