package com.rms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Title:StaticResourceConfig
 * Description:静态资源配置类
 * @author    zwb
 * @date      2016年9月22日 下午7:05:30
 *
 */
@Configuration
public class StaticResourceConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:webapp/");
	}
}
