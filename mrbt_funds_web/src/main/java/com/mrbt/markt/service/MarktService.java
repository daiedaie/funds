package com.mrbt.markt.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface MarktService {
	/**
	 * 获取数据列表
	 *@Description
	 */
	public void getDataList();
	/**
	 * 获取热销基金
	 *@Description  
	 *@param paramsMap 请求参数
	 *@param pUrlName 请求对应PROPERTIES里的url名称
	 *@return Map类型的LIST列表
	 */
	public List<Map<String,Object>> getHotSellFund(Map<String,String> paramsMap,String pUrlName);
	/**
	 * 获取基金定投
	 *@Description  
	 *@param paramsMap 请求参数
	 *@param pUrlName 请求对应PROPERTIES里的url名称
	 *@return JSON对象
	 */
	public List<Map<String,Object>> getFundPledge(Map<String,String> paramsMap,String pUrlName);
	/**
	 * 获取基金超市列表
	 *@Description  
	 *@param paramsMap  请求参数
	 *@param pUrlName 请求对应PROPERTIES里的url名称
	 *@return JSON对象
	 */
	public JSONObject queryFundMarketList(Map<String,String> paramsMap,String pUrlName);
	/**
	 * 获取首页净值曲线图表数据
	 *@Description  
	 *@param fundCode 基金代码
	 *@return 日期：值 类型map
	 */
	public Map<String,Object> getLatestNetValue(String fundCode);
	/**
	 * 查询最新净值列表，取最新三十条记录
	 *@Description  
	 *@param fundCode 基金代码
	 *@return DATES：日期,VALS：净值
	 */
	public List<Map<String,Object>> queryLatestNetValueList(String fundCode);
	/**
	 * 查询基金公司简称
	 *@Description  
	 *@return 所有基金公司简称
	 */
	public Map<Object,Object> queryFundCompanyShortName();
	/**
	 * 查询redis中公司首字母--》公司名称 map
	 *@Description  
	 *@param key
	 *@return
	 */
	public Map<Object,Object> queryMarketCompanyNameMap(String key);
	
}
