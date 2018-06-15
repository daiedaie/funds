package com.mrbt.recom.dao;

import java.util.List;

import com.mrbt.recom.model.TheTakingOf;
public interface RecomMapper {

	/**
	 * 通过同花顺内部关键字查询基金推荐中上期调出数据
	 * 
	 * @param f003_ths001
	 * @return
	 */

	List<TheTakingOf> getCallout();

	/**
	 * 通过同花顺内部关键字查询基金推荐中本期调入数据
	 * 
	 * @param f003_ths001
	 * @return
	 */

	List<TheTakingOf> getTransferred();
}
