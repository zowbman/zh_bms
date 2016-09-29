package com.rms.service;

import java.util.List;

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
	 * 根据status查询顶级从菜单
	 * @param status
	 * @return List<TMenuCustom>
	 */
	List<TMenuCustom> findTopSlaveMenus();
}
