package com.bms.helper;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bms.exception.CustomException;
import com.bms.redis.RedisShardedInterface;
import com.bms.redis.cluster.RedisClusterInterface;

/**
 * 
 * Title:TokenHelper
 * Description:令牌
 * @author    zwb
 * @date      2016年11月8日 下午3:59:08
 *
 */
public class TokenHelper {
	private static final Logger LOG = Logger.getLogger(TokenHelper.class);
	private static final Random RANDOM = new Random();  
	private static final String TOKEN_NAMESPACE = "bms.boboface.com.tokens";//命名空间
	public static final String TOKEN_NAME_FIELD = "bms.boboface.com.token.name";  
	private static RedisShardedInterface redisShardedInterface;//非集群
	private static RedisClusterInterface redisClusterInterface;//集群
	
	/**
	 * 验证redis客户端连接
	 * @return
	 */
	public static boolean validRedisClientStatus(){
		if(TokenHelper.redisShardedInterface == null && TokenHelper.redisClusterInterface == null){
			return false;
		}
		return true;
	}
	
	public static void setRedisClient(RedisShardedInterface redisShardedInterface){
		TokenHelper.redisShardedInterface = redisShardedInterface;
	}
	
	public static void setRedisClient(RedisClusterInterface redisClusterInterface){
		TokenHelper.redisClusterInterface = redisClusterInterface;
	}
	
	/**
	 * 使用随机字符串做为token名字保存token
	 * @param request
	 * @return
	 * @throws CustomException 
	 */
	public static String setToken(HttpServletRequest request) throws CustomException{
		return setToken(request, generateGUID());
	}
	
	private static String setToken(HttpServletRequest request, String tokenName) throws CustomException{
		String tokenValue = generateGUID();
		cacheToken(request, tokenName, tokenValue);
		return tokenName;
	}
	
	/**
	 * 缓存token
	 * @param request
	 * @param tokenName
	 * @param tokenValue
	 * @throws CustomException
	 */
	private static void cacheToken(HttpServletRequest request, String tokenName, String tokenValue) throws CustomException{
		if(!validRedisClientStatus()){
			throw new CustomException("Redis Client Is Null!");
		}
		String cacheTokenName = createCacheTokenName(tokenName);
		if(redisShardedInterface != null){
			redisShardedInterface.expire(cacheTokenName, 5 * 60);//5分钟
			redisShardedInterface.lpush(cacheTokenName, tokenValue);
		}else if(redisClusterInterface != null){
			redisClusterInterface.lpush(cacheTokenName, tokenValue);
		}
		request.setAttribute(TOKEN_NAME_FIELD, tokenName);
		request.setAttribute(tokenName, tokenValue);
	}
	
	private static String createCacheTokenName(String tokenName){
		return TOKEN_NAMESPACE + "." + tokenName;
	}
	
	/**
	 * 从请求参数中获取token名字
	 * @param request
	 * @return
	 */
	private static String getTokenName(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();
		if(!params.containsKey(TOKEN_NAME_FIELD)){
			LOG.warn("Could not find token name in params.");  
			return null;
		}
		String[] tokens = (String[])params.get(TOKEN_NAME_FIELD);
		String token;
		if(tokens == null || tokens.length < 1){
			LOG.warn("Got a null or empty token name.");
			return null;
		}
		token = tokens[0];
		return token;
	}
	
	/**
	 * 
	 * 根据请求域中获取给定的token名字获取token的值，key->value
	 * @param request
	 * @param tokenName
	 * @return
	 */
	private static String getTokenValue(HttpServletRequest request, String tokenName){
		if(tokenName == null){
			return null;
		}
		Map<String, String[]> params = request.getParameterMap();
		String[] tokens = (String[])params.get(tokenName);
		String token;
		if(tokens == null || tokens.length < 1){
			LOG.warn("Could not find token mapped to token name " + tokenName);
			return null;
		}
		token = tokens[0];
		return token;
	}
	
	/**
	 * 校验token合法性
	 * @param request
	 * 请求域 http://xxx?TOKEN_NAME_FIELD=tokenName&tokenName=tokenValue	
	 * @return
	 */
	public static boolean validToken(HttpServletRequest request){
		if(!validRedisClientStatus()){
			if(LOG.isDebugEnabled()){
				LOG.debug("Redis Client Is Null!");
			}
			return false;
		}
		
		String tokenName = getTokenName(request);
		if(tokenName == null){
			LOG.debug("no token name found -> Invalid token ");
			return false;
		}
		String tokenValue = getTokenValue(request, tokenName);
		if(tokenValue == null){
			if(LOG.isDebugEnabled()){
				LOG.debug("no token found for token name " + tokenName + " -> Invalid token ");
			}
			return false;
		}
		String cacheTokenName = createCacheTokenName(tokenName);
		String cacheTokenValue = null;
		if(redisShardedInterface != null){
			cacheTokenValue = redisShardedInterface.lpop(cacheTokenName);
		}else if(redisClusterInterface != null){
			cacheTokenValue = redisClusterInterface.lpop(cacheTokenName);
		}
		if(!tokenValue.equals(cacheTokenValue)){
			LOG.warn("bms.boboface.com.internal.invalid.token Form token " + tokenValue + " does not match the session token " + cacheTokenValue + ".");  
            return false;
		}
		// remove the token so it won't be used again  
		return true;
	}
	
	/**
	 * 获取随机数
	 * @return
	 */
	private static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	} 
	
}
