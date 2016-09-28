package com.rms.model.po;

/**
 * 
 * Title:TDepartment
 * Description:部门信息
 * @author    zwb
 * @date      2016年9月28日 下午4:39:55
 *
 */
public class TDepartment {
	private Integer id;
	private String departmentname;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
}
