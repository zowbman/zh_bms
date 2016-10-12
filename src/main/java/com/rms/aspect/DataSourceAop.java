package com.rms.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.rms.config.readwriteseparation.datasource.DataSourceContextHolder;

/**
 * 
 * Title:DataSourceAop
 * Description:Spring AOP切面 读写分离
 * @author    zwb
 * @date      2016年10月11日 下午6:41:07
 *
 */
@Aspect
@Component
public class DataSourceAop {
	
    @Before("execution(* com.rms.service.*.findMasterMenusByStatus(..))")
    public void setWriteDataSourceType() {
        System.out.println("write aop");
        DataSourceContextHolder.write();
    }
    
    @Before("execution(* com.rms.service.*.findTopSlaveMenusAndPrivilege(..))")
    public void setReadDataSourceType() {
        System.out.println("read aop");
        DataSourceContextHolder.read();
    }
}