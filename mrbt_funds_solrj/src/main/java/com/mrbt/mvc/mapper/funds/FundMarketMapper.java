package com.mrbt.mvc.mapper.funds;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mrbt.mvc.model.funds.FundFuQuanNetValue;
import com.mrbt.mvc.model.funds.FundLatestNetValue;
import com.mrbt.mvc.model.funds.FundRecomPeriodGain;
import com.mrbt.mvc.model.funds.NetValueAndCode;
import com.mrbt.mvc.vo.FundMarketVo;

public interface FundMarketMapper {
	List<FundMarketVo> selectFundMarketList();
	
	
	//根据时间查询净值
	List<NetValueAndCode> selectNetValueByDate(@Param(value = "date") String date);
	
	//查询基金的最新净值,非当前时间
	List<FundLatestNetValue> selectNetValueLatest();
	
	//根据时间和基金代码查询指定时间的净值
	List<NetValueAndCode> selectNetValueByDateAndCode(@Param(value = "fundcode") String fundcode,
			@Param(value = "date") String date);
	
	//计算推荐基金的推荐期收益
	List<FundRecomPeriodGain> getFundRecomPeriodGain(@Param(value = "innercode") String fundcode,
			@Param(value = "date") String date);
	
	//查询复权净值
	List<FundFuQuanNetValue> selectNetValueFuQuan();
	
	//查询单个基金的最新复权净值
    FundFuQuanNetValue selectNetValueFuQuanByFundCode(@Param(value = "fundcode") String fundcode);
	
	//根据基金代码和时间查询复权净值
	FundFuQuanNetValue selectNetValueFuQuanByCodeAndDate(@Param(value = "fundcode") String fundcode,
			@Param(value = "date") String date);

	//根据基金代码和时间查询小于指定时间的复权净值
	List<FundFuQuanNetValue> selectNetValueFuQuanByCodeAndLessThanDate(@Param(value = "fundcode") String fundcode,
			@Param(value = "date") String date);
	
	//根据基金代码查询最新的复权净值
	FundFuQuanNetValue selectNetValueFuQuanByCodeLatest(@Param(value = "fundcode") String fundcode);
	
	//根据基金代码查询基金超市索引的部分内容
	FundMarketVo selectMarketVoByFundCode(@Param(value = "fundcode") String fundcode);
}
