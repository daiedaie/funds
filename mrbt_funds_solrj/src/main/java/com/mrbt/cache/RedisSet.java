package com.mrbt.cache;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import com.mrbt.cache.annotation.OperType;
import com.mrbt.cache.annotation.RedisOper;


/**
 * 缓存保存类
 * 
 * @author lzp
 *
 */
@Component
public class RedisSet {
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	RedisDao redisDao;

	/**
	 * spring el表达式解析类
	 */
	private ExpressionParser parser = new SpelExpressionParser();

	public boolean setRedisResult(RedisOper oper, String key,
			EvaluationContext ec, Object result) {
		if (oper != null) {
			if (oper.cmd() == OperType.HSET) {
				return setRedisHSetResult(oper, key, ec, result);
			}
			if (oper.cmd() == OperType.ZADD) {
				return setRedisZAddResult(oper, key, ec, result);
			}
		} else if (StringUtils.isNotBlank(key)) {
			return setRedisStringResult(key, result);
		}
		return false;
	}

	/**
	 * 设置key的值
	 * 
	 * @param key
	 * @param result
	 */
	public boolean setRedisStringResult(String key, Object result) {
		return redisDao.set(key, result);
	}

	/**
	 * 设置HSet结果
	 * 
	 * @param oper
	 * @param key
	 * @param ec
	 * @param result
	 */
	public boolean setRedisHSetResult(RedisOper oper, String key,
			EvaluationContext ec, Object result) {
		String setKey = oper.key();
		setKey = parser.parseExpression(setKey).getValue(ec, String.class);
		return redisDao.setHashByHSET(key, setKey, result);
	}

	/**
	 * 有序集合的zadd操作
	 * 
	 * @param oper
	 * @param key
	 * @param ec
	 * @param result
	 */
	public boolean setRedisZAddResult(RedisOper oper, String key,
			EvaluationContext ec, Object result) {
		String score = oper.score();
		if (StringUtils.isNotBlank(score)) {
			Long scoreVal = parser.parseExpression(score).getValue(ec,
					Long.class);
			return redisDao.ZAdd(key, scoreVal, result);
		}
		return false;
	}
}
