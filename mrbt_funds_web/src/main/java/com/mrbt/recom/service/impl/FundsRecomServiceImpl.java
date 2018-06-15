package com.mrbt.recom.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.cache.RedisDao;
import com.mrbt.recom.dao.RecomMapper;
import com.mrbt.recom.model.TheTakingOf;
import com.mrbt.recom.service.FundsRecomService;
import com.mrbt.units.DateUnits;
import com.mrbt.units.SolrUnits;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author yiban sun
 * @date 2016年6月24日 上午10:02:23
 * @version 1.0
 * @description
 * @since
 **/
@Service("fundsRecomService")
public class FundsRecomServiceImpl implements FundsRecomService {

	private Logger logger = Logger.getLogger(FundsRecomServiceImpl.class);

	@Resource
	private RecomMapper recomMapper;

	@Autowired
	private RedisDao redisDao;

	@Override
	public JSONObject getFirstThreeRecomFunds(Map<String, String> paramsMap, String pUrlName) {
		return SolrUnits.getSolrFundsByUrl(paramsMap, pUrlName);
	}

	/**
	 * 上期调出数据(luo)
	 */
	@Override
	public JSONArray queryPriorPeriodCalloutList() {

		JSONArray jsona = new JSONArray();

		Object redis_value = null;
		// 查询redis
		String redis_key = "query_prior_period_callout";
		redis_value = redisDao.getList(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);

		if (redis_value == null) {
			logger.info("从数据库中查询数据");
			List<TheTakingOf> resList = recomMapper.getCallout();
			
			if(resList != null && resList.size() > 0){
				jsona = JSONArray.fromObject(resList);
				// 数据的查询结果写入到redis中
				redisDao.set(redis_key, jsona);
				redisDao.expire(redis_key, DateUnits.getTomorrowDate());
			}
		}else{
			jsona = JSONArray.fromObject(redis_value);
		}

		return jsona;
	}

	/**
	 * 本期调入数据(luo)
	 */
	@Override
	public JSONArray queryCurrentTransferredList() {
		
		JSONArray jsona = new JSONArray();

		Object redis_value = null;

		// 查询redis
		String redis_key = "query_current_trans_ferred";
		redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);

		if (redis_value == null) {
			logger.info("从数据库中查询数据");
			List<TheTakingOf> resList = recomMapper.getTransferred();
			if(resList != null && resList.size() > 0){
				jsona = JSONArray.fromObject(resList);
				// 数据的查询结果写入到redis中
				redisDao.set(redis_key, jsona);
				redisDao.expire(redis_key, DateUnits.getTomorrowDate());
			}
		}else{
			jsona = JSONArray.fromObject(redis_value);
		}

		return jsona;

	}

}
