package com.mrbt.combination.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 *@author yiban sun
 *@date 2016年7月11日 下午5:22:37
 *@version 1.0
 *@description 组合宝service
 *@since
 **/
public interface CombinationService {
	/**
	 * 数据库查询组合宝核心数据
	 *@Description  
	 *@return COMBOID：组合宝ID ，COMBONAME：组合宝名称，FUNDCODE 基金代码F002_THS001，
	 *		  INNERCODE 基金代码F002_THS035，RATIO 占比，TYPENAME 基金类型，SHORTNAME 基金简称
	 */
	public List<Map<String,Object>> queryCombinationList(BigDecimal id);
	/**
	 * 查询配置详情，曲线图数据 redis key = FUNDS_PACKAGE_CURVE_+基金组合类型id
	 *@Description  
	 *@return List
	 */
	public List<Map<String,Object>> queryConfigDetail(String key);
	/**
	 * 查询基金组合占比 redis key = FUNDS_PACKAGE_FUNDS_TYPE_RATIO_+基金组合类型id
	 *@Description  
	 *@return Map<String, Integer>
	 */
	public Map<String,Integer> queryCombinationRatio(String key);
	/**
	 * 查询所有上线组合宝类型
	 *@Description  
	 *@return COMBOID 类型ID,  COMBONAME 类型名称 
	 */
	public List<Map<String,Object>> queryCombinationType();
	/**
	 * 从solr查询组合宝最新净值，涨幅
	 *@Description  
	 *@param code 基金代码
	 *@return
	 */
	public JSONObject queryCombinationNY(String code);
}
