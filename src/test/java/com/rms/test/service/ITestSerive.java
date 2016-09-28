package com.rms.test.service;

import java.util.List;

import com.rms.test.model.po.User;

/**
 * 
 * Title:ITestSerive
 * Description:接口
 * @author    zwb
 * @date      2016年9月21日 下午5:40:59
 *
 */
public interface ITestSerive {
	
	/**
	 * 查询全部用户
	 * @return
	 */
	List<User> findAll();
}
