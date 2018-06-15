package com.mrbt.markt.entity;

import org.apache.commons.lang.StringUtils;

/**
 *@author yiban sun
 *@date 2016年7月5日 上午11:10:26
 *@version 1.0
 *@description 超市页查询参数实体类
 *@since
 **/
public class MarketQueryParamEntity {
	private String castsurely; //是否定投 
	private String company;    //基金公司
	private String order;	   //排序
	private String scale;	   //基金规模
	private String theme;	   //基金主题
	private String type;	   //基金类型
	private String start;      //分页开始
	private String rows;	   //分页条数
	private String pUrlName;   //solr使用url
	private String orderType;  //asc desc
	private String param;      //全文搜索时使用的参数
	
	public String getCastsurely() {
		return castsurely;
	}
	public void setCastsurely(String castsurely) {
		this.castsurely = castsurely;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStart() {
		if(StringUtils.isBlank(start)) {
			start = "0";//默认从0开始
		}
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getRows() {
		if(StringUtils.isBlank(rows)){
			rows = "10";//默认查询十条
		}
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getpUrlName() {
		return pUrlName;
	}
	public void setpUrlName(String pUrlName) {
		this.pUrlName = pUrlName;
	}
	public String getOrderType() {
		if(StringUtils.isBlank(orderType)) {
			orderType = "asc";
		}
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
}
