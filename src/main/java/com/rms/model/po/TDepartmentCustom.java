package com.rms.model.po;

import java.util.List;

public class TDepartmentCustom extends TDepartment {
	private List<TUser> users;

	public List<TUser> getUsers() {
		return users;
	}

	public void setUsers(List<TUser> users) {
		this.users = users;
	}
}
