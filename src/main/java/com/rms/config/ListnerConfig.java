package com.rms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rms.listener.InitListner;

/**
 * 
 * Title:ListnerConfig
 * Description:监听器配置
 * @author    zwb
 * @date      2016年10月11日 下午5:30:42
 *
 */
@Configuration
public class ListnerConfig {
	 //注册初始化监听器
    @Bean
    public InitListner initListner(){
    	return new InitListner();
    }
}
