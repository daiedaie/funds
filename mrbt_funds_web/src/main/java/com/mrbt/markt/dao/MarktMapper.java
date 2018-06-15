package com.mrbt.markt.dao;

import java.util.List;
import java.util.Map;

/**
 *@author yiban sun
 *@date 2016年7月6日 上午11:44:12
 *@version 1.0
 *@description 
 *@since
 **/
public interface MarktMapper {
	/**
	 * 查询基金最新净值前三十条记录
	 *@Description  
	 *@param fundCode 基金代码
	 *@return DATES ：日期，VALS 净值
	 */
	public List<Map<String,Object>> queryLatestNetValue(String fundCode);
	/**
	 * 查询基金公司简称
	 *@Description  
	 *@return 所有基金公司简称
	 */
	public List<String> queryFundCompanyShortName();
}
