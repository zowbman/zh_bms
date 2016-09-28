package com.rms.model.po;

import java.util.List;

/**
 * 
 * Title:TGroupCustom
 * Description:组别扩展类
 * @author    zwb
 * @date      2016年9月28日 下午5:22:01
 *
 */
public class TGroupCustom extends TGroup {
	private List<TUserCustom> users;

	public List<TUserCustom> getUsers() {
		return users;
	}

	public void setUsers(List<TUserCustom> users) {
		this.users = users;
	}
}
