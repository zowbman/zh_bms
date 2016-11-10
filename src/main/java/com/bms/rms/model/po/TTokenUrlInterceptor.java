package com.bms.rms.model.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * Title:TTokenUrlInterceptor
 * Description:tokenurl拦截
 * @author    zwb
 * @date      2016年11月8日 下午7:39:50
 *
 */
public class TTokenUrlInterceptor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer privilegeid;

    private String interceptorurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(Integer privilegeid) {
        this.privilegeid = privilegeid;
    }

    public String getInterceptorurl() {
        return interceptorurl;
    }

    public void setInterceptorurl(String interceptorurl) {
        this.interceptorurl = interceptorurl == null ? null : interceptorurl.trim();
    }
}