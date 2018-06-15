package com.mrbt.mvc.service.redis;



public interface FundNetValueRedisService {

//	double saveAllFundLatestNetValueToRedis();
//
//	double saveAllFundAllNetValueToRedis();
//
//	boolean redisInternalSaveValueList(String key, List<String> content);
//
//	boolean redisInternalSaveValueString(String key, String content);
//
//	boolean saveSingleFundAllNetValueToRedis(String fundcode);

	//全部基金一个月的净值
	double saveAllFundMonthNetValueToRedis();

//	double saveAllFundSpecifyDateNetValueToRedis(String startDate,
//			String endDate);
//
//	double saveAllFundNormalMonthNetValueToRedis();

}
