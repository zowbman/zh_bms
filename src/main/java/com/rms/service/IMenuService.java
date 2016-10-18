package com.rms.service;

import java.util.List;

import com.rms.base.service.IBaseService;
import com.rms.model.po.TMenu;
import com.rms.model.po.TMenuCustom;
import com.rms.model.vo.MenuTypeEnum;


/**
 * 
 * Title:IMenuService
 * Description:菜单service接口
 * @author    zwb
 * @date      2016年9月28日 下午6:28:45
 *
 */
public interface IMenuService  extends IBaseService<TMenu>{
	
	/**
	 * 根据status查询主菜单
	 * @return List<Menu>
	 */
	List<TMenu> findMasterMenusByStatus(Byte status);
	
	/**
	 * 根据status查询主菜单（不包含自己）
	 * @param status
	 * @return
	 */
	List<TMenu> findMasterMenusByStatusAndNotMe(Byte status, Integer menuId);
	
	/**
	 * 查询顶级从菜单(关联查询子菜单权限)
	 * @return List<TMenuCustom>
	 */
	List<TMenuCustom> findTopSlaveMenusAndPrivilege();
	
	/**
	 * 查询顶级从菜单
	 * @return
	 */
	List<TMenu> findTopMenus(MenuTypeEnum menuTypeEnum);
	
	/**
	 * 查询子级从菜单
	 * @param parentId 父级id
	 * @return
	 */
	List<TMenu> findChildrenSlaveMenus(Integer parentId);
	
	/**
	 * 查询从菜单
	 * @return
	 */
	List<TMenu> findSlaveMenus();
	
	/**
	 * 查询从菜单（不含自己）
	 * @param menuId 菜单id
	 * @return
	 */
	List<TMenu> findSlaveMenusIsNotMe(Integer menuId);
	
	/**
	 * 根据masterMenuId查询不包含自己父级菜单
	 * @param masterMenuId 主菜单
	 * @param isNotMenuId 自己id
	 * @return
	 */
	List<TMenu> findParentMenusByMasterMenuIdIsNotMe(Integer masterMenuId, Integer isNotMenuId);
	
	/**
	 * 根据masterMenuId查询
	 * @param masterMenuId 主菜单
	 * @return
	 */
	List<TMenu> findParentMenusByMasterMenuId(Integer masterMenuId);
	
	/**
	 * 更新从从菜单到主菜单
	 * @param masterMenuId 主master菜单id
	 * @param parentMenuId 父菜单id
	 */
	void updateSlaveToMasterMenu(Integer masterMenuId, Integer parentMenuId);
	
	/**
	 * 查询最大排序号
	 * @return
	 */
	Byte findMenuMaxSort();
	
	/**
	 * 递归查询菜单和子菜单
	 * @param id
	 * @return
	 */
	 List<Integer> findMenuAndChildrenMenusForRecursion(Integer id,List<Integer> ids);
}
