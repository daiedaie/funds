package com.mrbt.units;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class SolrUnits {
	private static final Logger logger = Logger.getLogger(SolrUnits.class);
	
	private static String foundRecom = PropertiesUtil.getPropertiesByKey("foundRecom");
	
	private static String foundDetails = PropertiesUtil.getPropertiesByKey("foundDetails");
	
	private static String foundContrast = PropertiesUtil.getPropertiesByKey("foundContrast");
	
	
	public static void getData1() {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("start", "0");
		paramsMap.put("rows", "10");
		
		try {
			String xml = CrawlerWebSousePost.post(foundRecom, paramsMap);
			
			JSONObject json = JSONObject.fromObject(xml);
			logger.info(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 通过基金代码获取详情
	 * http://192.168.1.88:9999/mrbt_funds_solrj/detail/get?id=502013
	 * @param paramsMap {"id","502013"}
	 */
	
	public static JSONObject getFundsDetails(Map<String, String> paramsMap) {
		
		try {
			String xml = CrawlerWebSousePost.post(foundDetails, paramsMap);
			logger.info("获取详情数据：" + foundDetails + "\t" + paramsMap.toString() + "\n" + xml);
			JSONObject json = JSONObject.fromObject(xml);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 通过基金代码获取详情
	 * http://192.168.1.88:9999/mrbt_funds_solrj/detail/get?id=502013
	 * @param paramsMap {"id","502013"}
	 */
	
	public static JSONObject getFundsFromIds(Map<String, String> paramsMap) {
		
		try {
			String xml = CrawlerWebSousePost.post(foundContrast, paramsMap);
			System.out.println(xml);
			logger.info("获取详情数据：" + foundContrast + "\t" + paramsMap.toString() + "\n" + xml);
			
			JSONObject json = JSONObject.fromObject(xml);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 *@Description 获取SOLR数据 
	 *@param paramsMap 
	 *@param pUrlName url.properties中对应地址名
	 *@return JSON数据
	 */
	public static JSONObject getSolrFundsByUrl(Map<String,String> paramsMap,String pUrlName) {
		try {
			String url = PropertiesUtil.getPropertiesByKey(pUrlName);
			System.out.println(pUrlName + "\t" + url + "\t" + paramsMap.toString());
			String xml = CrawlerWebSousePost.post(url, paramsMap);
			if(StringUtils.isNotBlank(xml)){
				JSONObject json = JSONObject.fromObject(xml);
				return json;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
