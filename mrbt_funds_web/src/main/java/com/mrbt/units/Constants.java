package com.mrbt.units;

import org.apache.commons.lang.StringUtils;

/**
 *@author yiban sun
 *@date 2016年7月5日 上午10:39:34
 *@version 1.0
 *@description 常量
 *@since
 **/
public class Constants {
	/**
	 * 基金超市排序使用常量
	 */
	//基金代码 1
	public static String FUND_CODE = "market_fund_code";
	
	//热销基金 2
	public static String FUND_HOTSALE = "market_fund_hotsale";
	
	//最新净值 3
	public static String LATEST_NETVALUE = "market_fund_latestnetvalue";
	
	//日涨幅 4
	public static String DAY_GAIN = "market_fund_daygain";
	
	//近一个月 5
	public static String ONE_MONTH_GAIN = "market_fund_nearlymonthgain";
	
	//近三个月 6
	public static String THREE_MONTH_GAIN = "market_fund_nearlythreemonthgain";
	
	//今年来 7
	public static String YEAR_GAIN = "market_fund_yeargain";
	
	//近三年 8
	public static String THREE_YEAR_GAIN = "market_fund_threeyeargain";
	
	//基金规模 9
	public static String FUND_SCALE = "market_fund_scale";
	
	public static String getMarketOrderNameByCode(String code) {
		if(StringUtils.isBlank(code)){
			return FUND_CODE;
		}
		switch (code) {
		case "1":
			return FUND_CODE;
		case "2":
			return FUND_HOTSALE;
		case "3":
			return LATEST_NETVALUE;
		case "4":
			return DAY_GAIN;
		case "5":
			return ONE_MONTH_GAIN;
		case "6":
			return THREE_MONTH_GAIN;
		case "7":
			return YEAR_GAIN;
		case "8":
			return THREE_YEAR_GAIN;
		case "9":
			return FUND_SCALE;
		default:
			return FUND_CODE;
		}
	}
	
	//基金超市首字母redis map保存key常量
	public static String MC_FIRSTWORD = "mcFirstWord";
	
	//组合宝 组合业绩走势redis KEY前缀:格式  前缀+ID
	public static String PORTFOLIO_PERFORMANCE_TREND = "FUNDS_PACKAGE_CURVE_";
	
	//组合宝配置比例redis KEY前缀:格式  前缀+ID
	public static String COMBO_RATIO= "FUNDS_PACKAGE_FUNDS_TYPE_RATIO_";
	
	//最新净值rendis前缀
	public static String NET_VALUE = "solrnetvalue";
	
}
