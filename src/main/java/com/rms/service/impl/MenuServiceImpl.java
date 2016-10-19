package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.rms.base.service.impl.BaseServiceImpl;
import com.rms.model.po.TMenu;
import com.rms.model.po.TMenuCustom;
import com.rms.model.vo.MenuTypeEnum;
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
	

	@Override
	public List<TMenu> findMasterMenusByStatusAndNotMe(Byte status, Integer menuId) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", status);
		criteria.andEqualTo("menutype", 0);
		criteria.andNotEqualTo("id", menuId);
		return tMenuMapper.selectByExample(example);
	}

	@Override
	public List<TMenuCustom> findTopSlaveMenusAndPrivilege() {
		return tMenuMapper.findTopSlaveMenusAndPrivilege();
	}

	@Override
	public List<TMenu> findTopMenus(MenuTypeEnum menuTypeEnum) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		if(MenuTypeEnum.master.equals(menuTypeEnum)){
			criteria.andEqualTo("menutype", menuTypeEnum.getValue());
		}else{
			criteria.andEqualTo("menutype", menuTypeEnum.getValue());
		}
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


	@Override
	public List<TMenu> findParentMenusByMasterMenuIdIsNotMe(Integer masterMenuId, Integer isNotMenuId) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("mastermenuid", masterMenuId);
		criteria.andNotEqualTo("id", isNotMenuId);
		return tMenuMapper.selectByExample(example);
	}


	@Override
	public void updateSlaveToMasterMenu(Integer masterMenuId, Integer parentMenuId) {
		List<TMenu> childrenMenus = findChildrenSlaveMenus(parentMenuId);
		//把当前节点的子节点的parentId为空，且把masterMenuId设置为当前节点id
		for (TMenu tMenu : childrenMenus) {
			tMenu.setParentid(null);
			tMenu.setMastermenuid(masterMenuId);
			tMenuMapper.updateByPrimaryKey(tMenu);
		}
	}


	@Override
	public List<TMenu> findParentMenusByMasterMenuId(Integer masterMenuId) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("mastermenuid", masterMenuId);
		return tMenuMapper.selectByExample(example);
	}


	@Override
	public Byte findMenuMaxSort() {
		Byte maxSort = tMenuMapper.findMenuMaxSort();
		if(maxSort == null){
			return 1;
		}
		return ++maxSort;
	}


	@Override
	public List<Integer> findMenuAndChildrenMenusForRecursion(Integer id, List<Integer> ids) {
		ids.add(id);
		Example example = new Example(TMenu.class);
		example.selectProperties("id");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("parentid", id);
		List<TMenu> tMenus = tMenuMapper.selectByExample(example);
		if(tMenus != null && tMenus.size() > 0){
			for (TMenu tMenu : tMenus) {
				findMenuAndChildrenMenusForRecursion(tMenu.getId(),ids);
			}
		}
		return ids;
	}


	@Override
	public List<TMenu> findAllBottomMenus() {
		List<TMenuCustom> tMenuCustoms = tMenuMapper.findAllForCascade();
		List<TMenu> bottomMenus = recursionFindBottomMenus(tMenuCustoms, new ArrayList<TMenu>(),null);
		return bottomMenus;
	}
	
	/**
	 * 递归获取底层菜单（没有子菜单的菜单）
	 * @param menuCustoms
	 * @param bottomMenus
	 * @param bottomMenu
	 * @return
	 */
	private List<TMenu> recursionFindBottomMenus(List<TMenuCustom> menuCustoms, List<TMenu> bottomMenus, TMenu bottomMenu) {
		if(menuCustoms != null && menuCustoms.size() > 0){
			for (TMenuCustom menuCustom : menuCustoms) {
				recursionFindBottomMenus(menuCustom.getSlaveChildrenMenus(),bottomMenus,menuCustom);
			}
		}else{
			if(bottomMenu != null){
				bottomMenus.add(bottomMenu);
			}
		}
		return bottomMenus;
	}
}
