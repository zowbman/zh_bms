package com.rms.service;

import java.util.List;

import com.rms.annotation.WriteOnlyConnection;
import com.rms.base.service.IBaseService;
import com.rms.model.po.TMenu;
import com.rms.model.po.TMenuCustom;


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
	 * 查询顶级从菜单(关联查询子菜单权限)
	 * @return List<TMenuCustom>
	 */
	List<TMenuCustom> findTopSlaveMenusAndPrivilege();
	
	/**
	 * 查询顶级从菜单
	 * @return
	 */
	List<TMenu> findTopSlaveMenus();
	
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
}
