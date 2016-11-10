package com.bms.rms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bms.base.service.impl.BaseServiceImpl;
import com.bms.rms.model.po.TTokenUrlInterceptor;
import com.bms.rms.service.ITokenUrlInterceptorService;
import com.boboface.base.util.BaseUtil;

/**
 * 
 * Title:TokenUrlInterceptorServiceImpl
 * Description:token url拦截service实现类
 * @author    zwb
 * @date      2016年11月8日 下午7:42:25
 *
 */
@Service
public class TokenUrlInterceptorServiceImpl extends BaseServiceImpl<TTokenUrlInterceptor> implements ITokenUrlInterceptorService {

	@Override
	public List<Integer> findAllSysDefaultUrlTokenInterceptor() {
		Example example = new Example(TTokenUrlInterceptor.class);
		example.createCriteria().andIsNotNull("privilegeid");
		List<TTokenUrlInterceptor> list = tTokenUrlInterceptorMapper.selectByExample(example);
		List<Integer> idsList = new ArrayList<Integer>();
		for (TTokenUrlInterceptor tTokenUrlInterceptor : list) {
			if(tTokenUrlInterceptor.getPrivilegeid() != null){
				idsList.add(tTokenUrlInterceptor.getPrivilegeid());
			}
		}
		return idsList;
	}

	@Override
	public List<TTokenUrlInterceptor> findAllCustomUrlTokenInterceptor() {
		Example example = new Example(TTokenUrlInterceptor.class);
		example.createCriteria().andIsNull("privilegeid");
		return tTokenUrlInterceptorMapper.selectByExample(example);
	}

	@Override
	public void saveSysDefaultUrlTokenInterceptor(List<Integer> newPrivilegeIds) {
		List<Integer> oldPrivilegeIds = tTokenUrlInterceptorMapper.findAllPrivilegeIds();
		Map<String, Object> map = BaseUtil.compareArry(oldPrivilegeIds, newPrivilegeIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		if(add_arry != null && add_arry.size() > 0){
			tTokenUrlInterceptorMapper.saveSysDefaultUrlTokenInterceptor(add_arry);//添加
		}
		tTokenUrlInterceptorMapper.deleteByPrivilegeIds(delete_arry);//删除
	}

	@Override
	public boolean deleteTokenUrlInterceptor(Integer id) {
		int effectRow = tTokenUrlInterceptorMapper.deleteByPrimaryKey(id);
		if(effectRow > 0)
			return true;
		return false;
	}
}
