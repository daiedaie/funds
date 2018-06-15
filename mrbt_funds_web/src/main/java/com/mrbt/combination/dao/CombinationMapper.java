package com.mrbt.combination.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *@author yiban sun
 *@date 2016年7月11日 下午5:24:48
 *@version 1.0
 *@description 组合宝
 *@since
 **/
public interface CombinationMapper {
	/**
	 * 根据组合ID查询所有该组合下的基金列表
	 *@Description  
	 *@param id
	 *@return
	 */
	public List<Map<String,Object>> queryCombinationList(BigDecimal id);
	/**
	 * 查询所有上线组合宝类型
	 *@Description  
	 *@return COMBOID 类型ID,  COMBONAME 类型名称 
	 */
	public List<Map<String,Object>> queryCombinationType();
}
