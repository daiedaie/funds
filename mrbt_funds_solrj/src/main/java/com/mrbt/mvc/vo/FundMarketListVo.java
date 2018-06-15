package com.mrbt.mvc.vo;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.solr.client.solrj.beans.Field;

import com.mrbt.utils.FastJsonUtils;

public class FundMarketListVo implements Serializable {
	private static final long serialVersionUID = 1L;
	static DecimalFormat dcmFmt = new DecimalFormat("0.0000");
	/**
	 * 基金超市列表索引 返回的数据包括11个属性
	 * 基金类型，基金名称，基金代码，基金最新净值，基金日涨幅，基金近一月，基金近三月，基金今年来，基金近三年来,基金规模和申购费率
	 * 
	 * 
	 * 修改、仅返回页面需要的类型
	 * 
	 */
	
	//成立时间
	
	// 基金类型
	@Field(value = "market_fund_type")
	private String market_fund_type;
	// 基金简称
	@Field(value = "market_fund_shortname")
	private String market_fund_shortname;
	// 基金名称
	@Field(value = "market_fund_name")
	private String market_fund_name;
	// 基金代码
	@Field(value = "market_fund_code")
	private String market_fund_code;
	// 基金规模
	@Field(value = "market_fund_scale")
	private double market_fund_scale;
	// 申购费率
	@Field(value = "market_fund_buyrate")
	private double market_fund_buyrate;
	// 最新净值
	@Field(value = "market_fund_latestnetvalue")
	private double market_fund_latestnetvalue;
	// 日涨幅
	@Field(value = "market_fund_daygain")
	private double market_fund_daygain;
	// 周涨幅
	@Field(value = "market_fund_weekgain")
	private double market_fund_weekgain;
	// 六个月涨幅
	@Field(value = "market_fund_nearlysixmonthgain")
	private double market_fund_nearlysixmonthgain;
	// 近一个月
	@Field(value = "market_fund_nearlymonthgain")
	private double market_fund_nearlymonthgain;
	// 近三个月
	@Field(value = "market_fund_nearlythreemonthgain")
	private double market_fund_nearlythreemonthgain;
	// 今年来
	@Field(value = "market_fund_yeargain")
	private double market_fund_yeargain;
	// 近三年
	@Field(value = "market_fund_threeyeargain")
	private double market_fund_threeyeargain;
	// 是否定投
	// @Field(value="market_fund_castsurely")
	// private String market_fund_castsurely;
	// 基金公司
	@Field(value = "market_fund_company")
	private String market_fund_company;
	
	//基金公司简称
	@Field(value = "market_fund_shortcompany")
	private String market_fund_shortcompany;
	
	//基金成立时间
	@Field(value = "market_fund_upDate")
	private String market_fund_upDate;
	

	// 基金主题
	// @Field(value="market_fund_theme")
	// private String market_fund_theme;

	// 基金热销标志,默认为0
	// @Field(value="market_fund_hotsale")
	// private double market_fund_hotsale;

	// 基金推荐标志
	// @Field(value="market_fund_recommend")
	// private String market_fund_recommend;
	// 基金经理
	// @Field(value="market_fund_manager")
	// private String market_fund_manager;

	public String getMarket_fund_type() {
		return market_fund_type;
	}

	public void setMarket_fund_type(String market_fund_type) {
		this.market_fund_type = market_fund_type;
	}

	public String getMarket_fund_name() {
		return market_fund_name;
	}

	public void setMarket_fund_name(String market_fund_name) {
		this.market_fund_name = market_fund_name;
	}

	public String getMarket_fund_code() {
		return market_fund_code;
	}

	public void setMarket_fund_code(String market_fund_code) {
		this.market_fund_code = market_fund_code;
	}

	public double getMarket_fund_scale() {
		return Double.parseDouble(dcmFmt.format(market_fund_scale / 100000000));
	}

	public void setMarket_fund_scale(double market_fund_scale) {
		this.market_fund_scale = market_fund_scale;
	}

	public double getMarket_fund_buyrate() {
		return market_fund_buyrate;
	}

	public void setMarket_fund_buyrate(double market_fund_buyrate) {
		this.market_fund_buyrate = market_fund_buyrate;
	}

	public double getMarket_fund_latestnetvalue() {
		return market_fund_latestnetvalue;
	}

	public void setMarket_fund_latestnetvalue(double market_fund_latestnetvalue) {
		this.market_fund_latestnetvalue = market_fund_latestnetvalue;
	}

	public double getMarket_fund_daygain() {
		return market_fund_daygain;
	}

	public void setMarket_fund_daygain(double market_fund_daygain) {
		this.market_fund_daygain = market_fund_daygain;
	}

	public double getMarket_fund_nearlymonthgain() {
		return market_fund_nearlymonthgain;
	}

	public void setMarket_fund_nearlymonthgain(
			double market_fund_nearlymonthgain) {
		this.market_fund_nearlymonthgain = market_fund_nearlymonthgain;
	}

	public double getMarket_fund_nearlythreemonthgain() {
		return market_fund_nearlythreemonthgain;
	}

	public void setMarket_fund_nearlythreemonthgain(
			double market_fund_nearlythreemonthgain) {
		this.market_fund_nearlythreemonthgain = market_fund_nearlythreemonthgain;
	}

	public double getMarket_fund_yeargain() {
		return market_fund_yeargain;
	}

	public void setMarket_fund_yeargain(double market_fund_yeargain) {
		this.market_fund_yeargain = market_fund_yeargain;
	}

	public double getMarket_fund_threeyeargain() {
		return market_fund_threeyeargain;
	}

	public void setMarket_fund_threeyeargain(double market_fund_threeyeargain) {
		this.market_fund_threeyeargain = market_fund_threeyeargain;
	}

	// public String getMarket_fund_castsurely() {
	// return market_fund_castsurely;
	// }
	// public void setMarket_fund_castsurely(String market_fund_castsurely) {
	// this.market_fund_castsurely = market_fund_castsurely;
	// }
	public String getMarket_fund_company() {
		return market_fund_company;
	}

	public void setMarket_fund_company(String market_fund_company) {
		this.market_fund_company = market_fund_company;
	}

	// public String getMarket_fund_theme() {
	// return market_fund_theme;
	// }
	// public void setMarket_fund_theme(String market_fund_theme) {
	// this.market_fund_theme = market_fund_theme;
	// }
	// public double getMarket_fund_hotsale() {
	// return market_fund_hotsale;
	// }
	// public void setMarket_fund_hotsale(double market_fund_hotsale) {
	// this.market_fund_hotsale = market_fund_hotsale;
	// }
	// public String getMarket_fund_recommend() {
	// return market_fund_recommend;
	// }
	// public void setMarket_fund_recommend(String market_fund_recommend) {
	// this.market_fund_recommend = market_fund_recommend;
	// }
	// public String getMarket_fund_manager() {
	// return market_fund_manager;
	// }
	// public void setMarket_fund_manager(String market_fund_manager) {
	// this.market_fund_manager = market_fund_manager;
	// }
	public String getMarket_fund_shortname() {
		return market_fund_shortname;
	}

	public void setMarket_fund_shortname(String market_fund_shortname) {
		this.market_fund_shortname = market_fund_shortname;
	}

	public double getMarket_fund_weekgain() {
		return market_fund_weekgain;
	}

	public void setMarket_fund_weekgain(double market_fund_weekgain) {
		this.market_fund_weekgain = market_fund_weekgain;
	}

	public double getMarket_fund_nearlysixmonthgain() {
		return market_fund_nearlysixmonthgain;
	}

	public void setMarket_fund_nearlysixmonthgain(
			double market_fund_nearlysixmonthgain) {
		this.market_fund_nearlysixmonthgain = market_fund_nearlysixmonthgain;
	}
	public String toString(){
		return FastJsonUtils.Obj2Json(this);
	}

	public String getMarket_fund_shortcompany() {
		return market_fund_shortcompany;
	}

	public void setMarket_fund_shortcompany(String market_fund_shortcompany) {
		this.market_fund_shortcompany = market_fund_shortcompany;
	}

	public String getMarket_fund_upDate() {
		return market_fund_upDate;
	}

	public void setMarket_fund_upDate(String market_fund_upDate) {
		this.market_fund_upDate = market_fund_upDate;
	}
	
	
}
