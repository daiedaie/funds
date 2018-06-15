package com.mrbt.config;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class AppInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//定投
	public static final String FUND_ISCASTSURELY = "5";
	//推荐
	public static final String FUND_ISRECOMMEND = "1";
	
	//======================================基金类型===================================
	//股票型
	public static final String STOCK_TYPE = "股票型";
	public static final String STOCK_TYPE_NUM = "0";
	//债券型
	public static final String BOND_TYPE = "债券型";
	public static final String BOND_TYPE_NUM = "1";
	//混合型	
	public static final String HYBIRD_TYPE = "混合型";
	public static final String HYBIRD_TYPE_NUM = "2";
	//货币型	
	public static final String CURRENCY_TYPE = "货币型";
	public static final String CURRENCY_TYPE_NUM = "3";
	//指数型	
	public static final String INDEX_TYPE = "指数型";
	public static final String INDEX_TYPE_NUM = "4";
	//QDII型	
	public static final String QDII_TYPE = "QDII";
	public static final String QDII_TYPE_NUM = "5";	
	//基金型
	public static final String FUND_TYPE = "基金型";
	public static final String FUND_TYPE_NUM = "6";
	//保本型
	public static final String CAPITALPRERVATION_TYPE = "保本型";
	public static final String CAPITALPRERVATION_TYPE_NUM = "7";
	//创新型
	public static final String INNOVATION_TYPE = "创新型";
	public static final String INNOVATION_TYPE_NUM = "8";
	//其他类型	
	public static final String OTHERS_TYPE = "-1";
	//所有类型	
	public static final String ALL_TYPE = "*";
	
	//public static final String SOLR_SERVER_URL = "http://192.168.1.223:8080/solr/core";
	
	public static final String SOLR_SERVER_URL = "http://localhost:6780/solr/core";
	
	public static final String DEFAULTVALUEZERO = "0";
	public static final double DEFAULTDOUBLEVALUEZERO = 0;
	//redis过期时间和单位
	public static final int REDIS_NETVALUE_EXPIRE = 24;
	public static final TimeUnit REDIS_NETVALUE_EXPIRE_UNIT = TimeUnit.HOURS;
	
	//定投标志
	public static final int CASTSURELYCASE = 5;
	
	public static final String DEFAULTVALUEBLANK = "";
	
	//基金推荐标志值
	public static final String FUNDMARKETRECOMMENDVAL = "1";
	public static final String UNFUNDMARKETRECOMMENDVAL = "0";
	
	public static final String DEFAULTBLANKVALUE = "";
	
	public static final String NETVALUEREDISKEYPREFIX = "solrnetvalue";
	
	public static final String FOUND_MARKET_ADD_MESSAGE_T = "{success:添加基金超市索引成功}";
	public static final String FOUND_MARKET_ADD_MESSAGE_F = "{failure:添加基金超市索引失败}";	
	
	public static final String FOUND_MARKET_ADD_MESSAGE_F_REDIS = "{failure:添加基金超市索引失败}";
	public static final String FOUND_MARKET_ADD_MESSAGE_F_LIST = "{failure:添加基金超市索引失败}";
	
	public static final String FOUND_MARKET_DELETE_MESSAGE_T = "{success:删除基金超市索引成功}";
	public static final String FOUND_MARKET_DELETE_MESSAGE_F = "{failure:删除基金超市索引失败}";	
	public static final String FOUND_MARKET_UPDATE_MESSAGE_T = "{success:更新基金超市索引成功}";
	public static final String FOUND_MARKET_UPDATE_MESSAGE_F = "{failure:更新基金超市索引失败}";	
	public static final String FOUND_MARKET_SELECT_MESSAGE_T = "{success:查询基金超市索引成功}";
	public static final String FOUND_MARKET_SELECT_MESSAGE_F = "{failure:查询基金超市索引失败}";
	
	public static final String FOUND_MARKET_IMPORTFULLINDEX_MESSAGE_T = "{success:基金超市full-import索引成功}";
	public static final String FOUND_MARKET_IMPORTFULLINDEX_MESSAGE_F = "{failure:基金超市full-import索引失败}";
	
	
	public static final String FOUND_HOTSALES_ADD_MESSAGE_T = "{success:添加热销基金索引成功}";
	public static final String FOUND_HOTSALES_ADD_MESSAGE_F = "{failure:添加热销基金索引失败}";
	public static final String FOUND_HOTSALES_UPDATE_MESSAGE_T = "{success:更新热销基金索引成功}";
	public static final String FOUND_HOTSALES_UPDATE_MESSAGE_F = "{failure:更新热销基金索引失败}";
	public static final String FOUND_HOTSALES_DELETE_MESSAGE_T = "{success:删除热销基金索引成功}";
	public static final String FOUND_HOTSALES_DELETE_MESSAGE_F = "{failure:删除热销基金索引失败}";
	public static final String FOUND_HOTSALES_SELECT_MESSAGE_T = "{success:查询热销基金索引成功}";
	public static final String FOUND_HOTSALES_SELECT_MESSAGE_F = "{failure:查询热销基金索引失败}";
	
	public static final String FOUND_CASTSURELY_ADD_MESSAGE_T = "{success:添加定投基金索引成功}";
	public static final String FOUND_CASTSURELY_ADD_MESSAGE_F = "{failure:添加定投基金索引失败}";	
	public static final String FOUND_CASTSURELY_UPDATE_MESSAGE_T = "{success:更新定投基金索引成功}";
	public static final String FOUND_CASTSURELY_UPDATE_MESSAGE_F = "{failure:更新定投基金索引失败}";	
	public static final String FOUND_CASTSURELY_DELETE_MESSAGE_T = "{success:删除定投基金索引成功}";
	public static final String FOUND_CASTSURELY_DELETE_MESSAGE_F = "{failure:删除定投基金索引失败}";	
	public static final String FOUND_CASTSURELY_SELECT_MESSAGE_T = "{success:查询定投基金索引成功}";
	public static final String FOUND_CASTSURELY_SELECT_MESSAGE_F = "{failure:查询定投基金索引失败}";
	
	public static final String FOUND_RECOMMEND_ADD_MESSAGE_T = "{success:添加推荐基金索引成功}";
	public static final String FOUND_RECOMMEND_ADD_MESSAGE_F = "{failure:添加推荐基金索引失败}";	
	public static final String FOUND_RECOMMEND_UPDATE_MESSAGE_T = "{success:更新推荐基金索引成功}";
	public static final String FOUND_RECOMMEND_UPDATE_MESSAGE_F = "{failure:更新推荐基金索引失败}";	
	public static final String FOUND_RECOMMEND_DELETE_MESSAGE_T = "{success:删除推荐基金索引成功}";
	public static final String FOUND_RECOMMEND_DELETE_MESSAGE_F = "{failure:删除推荐基金索引失败}";	
	public static final String FOUND_RECOMMEND_SELECT_MESSAGE_T = "{success:查询推荐基金索引成功}";
	public static final String FOUND_RECOMMEND_SELECT_MESSAGE_F = "{failure:查询推荐基金索引失败}";
	
	
	public static final int PREVIOUSDATEONE = -1;
	public static final int PREVIOUSDATETWO = -2;
	
	public static final int PREVIOUSDATEFIVE = -5;
	public static final int PREVIOUSDATESEVEN = -7;
	public static final int PREVIOUSDATETHREE = -3;
	public static final int PREVIOUSDATESIX = -6;
	public static final int DEFAULTDAYOFMONTH = -30;
	
	
	public static enum ImportType {
		FULL("full-import"), DELTA("delta-import");
		protected String type;

		private ImportType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}
	public final static String email = "912457694@qq.com";

}
