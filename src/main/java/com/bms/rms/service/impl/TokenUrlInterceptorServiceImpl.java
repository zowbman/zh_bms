package com.bms.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bms.base.service.impl.BaseServiceImpl;
import com.bms.rms.model.po.TTokenUrlInterceptor;
import com.bms.rms.service.ITokenUrlInterceptorService;

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
}
