package com.rms.model.po;

import java.util.List;

/**
 * 
 * Title:TMenuCustom
 * Description:菜单扩展类
 * @author    zwb
 * @date      2016年9月29日 下午2:12:32
 *
 */
public class TMenuCustom extends TMenu {
	
	private List<TMenuCustom> slaveChildrenMenus;//从菜单的子菜单

	public List<TMenuCustom> getSlaveChildrenMenus() {
		return slaveChildrenMenus;
	}

	public void setSlaveChildrenMenus(List<TMenuCustom> slaveChildrenMenus) {
		this.slaveChildrenMenus = slaveChildrenMenus;
	}	
}
