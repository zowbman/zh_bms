package com.rms.test.mapper;

import java.util.List;

import com.rms.test.model.po.User;

/**
 * 
 * Title:UserMapper
 * Description:用户Mapper
 * @author    zwb
 * @date      2016年9月21日 下午5:43:14
 *
 */
public interface UserMapper {
	/**
	 * 查询全部用户
	 * @return
	 */
	List<User> findAll();
}
