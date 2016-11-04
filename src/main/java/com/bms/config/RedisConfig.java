package com.bms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bms.redis.RedisClient;

/**
 * 
 * Title:RedisConfig
 * Description:redis缓存配置
 * @author    zwb
 * @date      2016年11月4日 下午2:27:01
 *
 */
@Configuration
public class RedisConfig {
	
	@Bean
	@ConfigurationProperties(prefix="redis")
	public RedisClient redisClient(){
		return new RedisClient();
	}
}
