package com.mrbt.mvc.vo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import com.mrbt.utils.FastJsonUtils;

public class FundCastSurelyVo implements Serializable{
	/**
	 * 基金定投
	 * 基金类型，基金简称，基金代码，基金名称，基金最新净值，基金年度涨幅，基金年度曲线
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定投类型
	@Field(value="market_fund_type")
	private String dt_fund_type;
	//定投基金管理人
	@Field(value="market_fund_manager")
	private String dt_fund_manager;
	//定投基金简称
	@Field(value="market_fund_shortname")
	private String dt_fund_abbreviation;
	//基金定投全称
	@Field(value="market_fund_name")
	private String dt_fund_fullname;
	//定投基金代码
	@Field(value="market_fund_code")
	private String dt_fund_code;
	//定投基金最新净值
	@Field(value="market_fund_latestnetvalue")
	private double dt_fund_latestnetvalue;
	//定投基金年度涨幅
	@Field(value="market_fund_yeargain")
	private double dt_fund_yeargain;
	//年度涨幅,净值曲线
	
	public String getDt_fund_manager() {
		return dt_fund_manager;
	}
	public String getDt_fund_type() {
		return dt_fund_type;
	}
	public void setDt_fund_type(String dt_fund_type) {
		this.dt_fund_type = dt_fund_type;
	}
	public void setDt_fund_manager(String dt_fund_manager) {
		this.dt_fund_manager = dt_fund_manager;
	}
	public String getDt_fund_abbreviation() {
		return dt_fund_abbreviation;
	}
	public void setDt_fund_abbreviation(String dt_fund_abbreviation) {
		this.dt_fund_abbreviation = dt_fund_abbreviation;
	}
	public String getDt_fund_code() {
		return dt_fund_code;
	}
	public void setDt_fund_code(String dt_fund_code) {
		this.dt_fund_code = dt_fund_code;
	}
	public double getDt_fund_latestnetvalue() {
		return dt_fund_latestnetvalue;
	}
	public void setDt_fund_latestnetvalue(double dt_fund_latestnetvalue) {
		this.dt_fund_latestnetvalue = dt_fund_latestnetvalue;
	}
	public double getDt_fund_yeargain() {
		return dt_fund_yeargain;
	}
	public void setDt_fund_yeargain(double dt_fund_yeargain) {
		this.dt_fund_yeargain = dt_fund_yeargain;
	}
	
	public String getDt_fund_fullname() {
		return dt_fund_fullname;
	}
	public void setDt_fund_fullname(String dt_fund_fullname) {
		this.dt_fund_fullname = dt_fund_fullname;
	}
	public String toString(){
		return FastJsonUtils.Obj2Json(this);
	}
}
