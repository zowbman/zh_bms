package com.rms.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * Title:InitListner
 * Description:初始化监听器
 * @author    zwb
 * @date      2016年9月29日 上午11:54:22
 *
 */
public class InitListner implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("hello");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
