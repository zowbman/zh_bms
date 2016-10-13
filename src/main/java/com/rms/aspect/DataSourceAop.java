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
	
    @Before("execution(* com.rms.mapper.*.save*(..))" + " or " +
    		"execution(* com.rms.mapper.*.insert*(..))" + " or " +
    		"execution(* com.rms.mapper.*.update*(..))" + " or " +
    		"execution(* com.rms.mapper.*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
    }
    
    @Before("execution(* com.rms.mapper.*.find*(..))" + " or " +
    		"execution(* com.rms.mapper.*.get*(..))" + " or " +
    		"execution(* com.rms.mapper.*.select*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
    }
}