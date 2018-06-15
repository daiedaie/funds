package com.mrbt.mvc.service.redis;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mrbt.cache.RedisDao;
import com.mrbt.config.AppInfo;
import com.mrbt.mvc.service.database.FundDBHandlerService;

@Service
public class FundNetValueRedisServiceImpl implements FundNetValueRedisService {
	@Autowired
	private RedisDao redisDao;

	@Autowired
	private FundDBHandlerService fundDBHandlerService;
	/****
	 * 保存基金超市列表的基金的一个月净值到Redis
	 * 
	 * 在定时任务中调用
	 */
	@Override
	public double saveAllFundMonthNetValueToRedis() {
		double count = 0;
		//查询所有基金的一个月净值
		Map<String, List<String>> monthNetValueLists = fundDBHandlerService.selectAllMonthNetValueAll();
		if (monthNetValueLists == null || monthNetValueLists.size() == 0) {
			return -1;
		}
		for (Map.Entry<String,List<String>> entry : monthNetValueLists.entrySet()) {
			//基金代码
			String fundcode = entry.getKey();
			//基金最新净值:时间&净值
			List<String> netvalues = entry.getValue();
			if (StringUtils.isEmpty(fundcode) || CollectionUtils.isEmpty(netvalues)) {
				continue;
			}
			//Boolean result = redisInternalSaveValueList(fundcode,netvalues);
			if(redisDao.hasKey(AppInfo.NETVALUEREDISKEYPREFIX+fundcode)){
				redisDao.delete(AppInfo.NETVALUEREDISKEYPREFIX+fundcode);
			}
			Boolean result = redisDao.setList(AppInfo.NETVALUEREDISKEYPREFIX+fundcode,netvalues);
			redisDao.expire(AppInfo.NETVALUEREDISKEYPREFIX+fundcode,AppInfo.REDIS_NETVALUE_EXPIRE,AppInfo.REDIS_NETVALUE_EXPIRE_UNIT);
			if (result)
				count++;
		}
		return count;
	}
}
