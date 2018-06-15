package com.mrbt.mvc.vo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;


public class FundRecommendVo implements Serializable{

	/**
	 * 推荐基金
	 */
	private static final long serialVersionUID = 1507204156622735414L;
	/**
	 * 基金类型，基金名称(简称)，基金代码，基金最新净值，基金日涨幅，基金近一个月，基金近三个月，基金推荐期涨跌幅,(推荐理由)
	 */
	//基金类型帅选
	
	//基金简称
	@Field(value="market_fund_shortname")
	private String recommend_fund_abbreviation;
	//基金推荐期涨跌幅
	@Field(value="market_fund_recommendgain")
	private double recommend_fund_recommendgain;
	//推荐理由
	@Field(value="market_fund_recommendreason")
	private String recommend_fund_recommendreason;	
		
		
	//基金类型
	@Field(value="market_fund_type")
	private String recommend_fund_type;
	
	//基金代码
	@Field(value="market_fund_code")
	private String recommend_fund_code;
	//基金申购费率
	@Field(value="market_fund_buyrate")
	private double recommend_fund_buyrate;
	//基金最新净值
	@Field(value="market_fund_latestnetvalue")
	private double recommend_fund_latestnetvalue;
	//基金近一个月
	@Field(value="market_fund_nearlymonthgain")
	private double recommend_fund_nearlymonthgain;
	//基金近三个月
	@Field(value="market_fund_nearlythreemonthgain")
	private double recommend_fund_nearlythreemonthgain;
	
	//日涨幅
	@Field(value="market_fund_daygain")
	private double recommend_fund_nearlydaygain;
	
	//建议基金的排序值
	@Field(value="market_fund_recommorder")
	private Integer recommend_fund_order;
	
	
	public String getRecommend_fund_type() {
		return recommend_fund_type;
	}
	public void setRecommend_fund_type(String recommend_fund_type) {
		this.recommend_fund_type = recommend_fund_type;
	}
	public String getRecommend_fund_abbreviation() {
		return recommend_fund_abbreviation;
	}
	public void setRecommend_fund_abbreviation(String recommend_fund_abbreviation) {
		this.recommend_fund_abbreviation = recommend_fund_abbreviation;
	}
	public String getRecommend_fund_code() {
		return recommend_fund_code;
	}
	public void setRecommend_fund_code(String recommend_fund_code) {
		this.recommend_fund_code = recommend_fund_code;
	}
	public double getRecommend_fund_buyrate() {
		return recommend_fund_buyrate;
	}
	public void setRecommend_fund_buyrate(double recommend_fund_buyrate) {
		this.recommend_fund_buyrate = recommend_fund_buyrate;
	}
	public double getRecommend_fund_latestnetvalue() {
		return recommend_fund_latestnetvalue;
	}
	public void setRecommend_fund_latestnetvalue(
			double recommend_fund_latestnetvalue) {
		this.recommend_fund_latestnetvalue = recommend_fund_latestnetvalue;
	}
	public double getRecommend_fund_nearlymonthgain() {
		return recommend_fund_nearlymonthgain;
	}
	public void setRecommend_fund_nearlymonthgain(
			double recommend_fund_nearlymonthgain) {
		this.recommend_fund_nearlymonthgain = recommend_fund_nearlymonthgain;
	}
	public double getRecommend_fund_nearlythreemonthgain() {
		return recommend_fund_nearlythreemonthgain;
	}
	public void setRecommend_fund_nearlythreemonthgain(
			double recommend_fund_nearlythreemonthgain) {
		this.recommend_fund_nearlythreemonthgain = recommend_fund_nearlythreemonthgain;
	}
	public double getRecommend_fund_recommendgain() {
		return recommend_fund_recommendgain;
	}
	public void setRecommend_fund_recommendgain(double recommend_fund_recommendgain) {
		this.recommend_fund_recommendgain = recommend_fund_recommendgain;
	}
	public String getRecommend_fund_recommendreason() {
		return recommend_fund_recommendreason;
	}
	public void setRecommend_fund_recommendreason(
			String recommend_fund_recommendreason) {
		this.recommend_fund_recommendreason = recommend_fund_recommendreason;
	}
	public double getRecommend_fund_nearlydaygain() {
		return recommend_fund_nearlydaygain;
	}
	public void setRecommend_fund_nearlydaygain(double recommend_fund_nearlydaygain) {
		this.recommend_fund_nearlydaygain = recommend_fund_nearlydaygain;
	}
	public Integer getRecommend_fund_order() {
		return recommend_fund_order;
	}
	public void setRecommend_fund_order(Integer recommend_fund_order) {
		this.recommend_fund_order = recommend_fund_order;
	}
	
	
	
}
