package com.rms.model.po;

import java.util.List;

/**
 * 
 * Title:TUserCustom
 * Description:用户信息扩展类
 * @author    zwb
 * @date      2016年9月28日 下午4:39:25
 *
 */
public class TUserCustom extends TUser {
	private TDepartment department;
	
	private List<TGroup> groups;

	public TDepartment getDepartment() {
		return department;
	}

	public void setDepartment(TDepartment department) {
		this.department = department;
	}

	public List<TGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<TGroup> groups) {
		this.groups = groups;
	}
	
}
