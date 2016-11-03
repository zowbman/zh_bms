package com.bms.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 
 * Title:RedisTestCase
 * Description:
 * @author    zwb
 * @date      2016年10月27日 下午6:08:04
 *
 */
public class RedisTestCase {
	
	/**
	 * redis 入门
	 */
	@Test
	public void redisConTest(){
		Jedis jedis = new Jedis("localhost");
		jedis.set("foo", "bar");
		String value =jedis.get("foo");
		System.out.println(value);
	}
}
