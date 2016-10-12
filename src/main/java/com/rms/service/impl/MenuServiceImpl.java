package com.rms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.rms.annotation.WriteOnlyConnection;
import com.rms.base.service.impl.BaseServiceImpl;
import com.rms.model.po.TMenu;
import com.rms.model.po.TMenuCustom;
import com.rms.service.IMenuService;

/**
 * 
 * Title:MenuServiceImpl
 * Description:菜单service实现类
 * @author    zwb
 * @date      2016年9月28日 下午6:29:44
 *
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<TMenu> implements IMenuService {
	
	@Override
	public List<TMenu> findMasterMenusByStatus(Byte status) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", status);
		criteria.andEqualTo("menutype", 0);
		return tMenuMapper.selectByExample(example);
	}

	@WriteOnlyConnection
	@Override
	public List<TMenuCustom> findTopSlaveMenusAndPrivilege() {
		return tMenuMapper.findTopSlaveMenusAndPrivilege();
	}

	@Override
	public List<TMenu> findTopSlaveMenus() {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menutype", 1);
		criteria.andIsNull("parentid");
		return tMenuMapper.selectByExample(example);
	}

	@Override
	public List<TMenu> findChildrenSlaveMenus(Integer parentId) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menutype", 1);
		criteria.andEqualTo("parentid", parentId);
		return tMenuMapper.selectByExample(example);
	}

	@Override
	public List<TMenu> findSlaveMenus() {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menutype", 1);
		return tMenuMapper.selectByExample(example);
	}

	@Override
	public List<TMenu> findSlaveMenusIsNotMe(Integer menuId) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menutype", 1);
		criteria.andNotEqualTo("id", menuId);
		return tMenuMapper.selectByExample(example);
	}
}
