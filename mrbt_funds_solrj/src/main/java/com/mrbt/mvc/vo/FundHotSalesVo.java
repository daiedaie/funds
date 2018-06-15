package com.mrbt.mvc.vo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import com.mrbt.utils.FastJsonUtils;

public class FundHotSalesVo implements Serializable{
	/**
	 * 基金热销
	 */
	private static final long serialVersionUID = 7392377812120802570L;
	/**
	 *  基金类型，简称，代码，名称，最新净值，年度涨幅和一年的曲线值
	 *  
	 *  
	 close 注释 查询条数：基金超市中12条 排序条件：THS054.F004-THS054.F005的计算结果降序 数据来源 solr接口
	 * 请求参数 排序条件：order=2 页数：page=1 数据条数： rows=12 返回字段
	 * (THS035)：类型[F007_THS035]、基金管理人
	 * [F027_THS035]简称[F002_THS035]、代码[F001_THS035] (THS043)：净值[F003_THS043]
	 * 涨幅：计算一年的涨幅[(天F003_THS043-一年前的昨天F003_THS043)/一年前的昨天F003_THS043]
	 * 曲线：净值一个月的曲线[003_THS043](纵坐标为净值，直接取，横坐标为时间【5-22 6-22】
	 */
	//基金类型
	@Field(value="market_fund_type")
	private String hotsale_fund_type;
	//基金经理
	@Field(value="market_fund_manager")
	private String hotsale_fund_manager;
	//基金热销简称
	@Field(value="market_fund_shortname")
	private String hotsale_fund_abbreviation;
	//基金热销全称
	@Field(value="market_fund_name")
	private String hotsale_fund_fullname;
	//基金代码
	@Field(value="market_fund_code")
	private String hotsale_fund_code;
	//基金最新净值
	@Field(value="market_fund_latestnetvalue")
	private double hotsale_fund_latestnetvalue;
	//基金年收益
	@Field(value="market_fund_yeargain")
	private double hotsale_fund_yeargain;
	public String getHotsale_fund_type() {
		return hotsale_fund_type;
	}
	public void setHotsale_fund_type(String hotsale_fund_type) {
		this.hotsale_fund_type = hotsale_fund_type;
	}
	public String getHotsale_fund_manager() {
		return hotsale_fund_manager;
	}
	public void setHotsale_fund_manager(String hotsale_fund_manager) {
		this.hotsale_fund_manager = hotsale_fund_manager;
	}
	public String getHotsale_fund_abbreviation() {
		return hotsale_fund_abbreviation;
	}
	public void setHotsale_fund_abbreviation(String hotsale_fund_abbreviation) {
		this.hotsale_fund_abbreviation = hotsale_fund_abbreviation;
	}
	public String getHotsale_fund_code() {
		return hotsale_fund_code;
	}
	public void setHotsale_fund_code(String hotsale_fund_code) {
		this.hotsale_fund_code = hotsale_fund_code;
	}
	public double getHotsale_fund_latestnetvalue() {
		return hotsale_fund_latestnetvalue;
	}
	public void setHotsale_fund_latestnetvalue(double hotsale_fund_latestnetvalue) {
		this.hotsale_fund_latestnetvalue = hotsale_fund_latestnetvalue;
	}
	public double getHotsale_fund_yeargain() {
		return hotsale_fund_yeargain;
	}
	public void setHotsale_fund_yeargain(double hotsale_fund_yeargain) {
		this.hotsale_fund_yeargain = hotsale_fund_yeargain;
	}
	public String getHotsale_fund_fullname() {
		return hotsale_fund_fullname;
	}
	public void setHotsale_fund_fullname(String hotsale_fund_fullname) {
		this.hotsale_fund_fullname = hotsale_fund_fullname;
	}
	public String toString(){
		return FastJsonUtils.Obj2Json(this);
	}
}
