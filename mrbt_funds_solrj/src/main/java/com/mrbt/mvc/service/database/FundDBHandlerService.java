package com.mrbt.mvc.service.database;

import java.util.List;
import java.util.Map;

public interface FundDBHandlerService {
	// 查询全部基金的一个月净值
	Map<String, List<String>> selectAllMonthNetValueAll();
	//单条基金的一个月净值
	Map<String, List<String>> selectSingleFund30NetValueByCode(String fundCode);
}
