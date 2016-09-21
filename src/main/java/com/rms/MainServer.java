package com.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


/**
 * 
 * Title:MainServer
 * Description:启动类
 * @author    zwb
 * @date      2016年9月21日 上午11:55:09
 *
 */
@SpringBootApplication
public class MainServer extends SpringBootServletInitializer {
	
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainServer.class);
	}

	public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }
    
    /**
     * 使用jetty容器启动
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory(){
    	JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
    	factory.setPort(8080);
    	return factory;
    }
}
