package com.mrbt.recom.service;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author yiban sun
 * @date 2016年6月24日 上午10:02:04
 * @version 1.0
 * @description 基金推荐service
 * @since
 **/
public interface FundsRecomService {
	// 查询基金推荐列表
	public JSONObject getFirstThreeRecomFunds(Map<String, String> paramsMap, String pUrlName);

	// 查询上期调出列表
	public JSONArray queryPriorPeriodCalloutList();

	// 查询本期调入列表
	public JSONArray queryCurrentTransferredList();

}
