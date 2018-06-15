package com.mrbt.details.model;

public class IndustrAlloca {
	
	//基金代码
	private String fundCode;
	//简称
	private String shortName;
	//同花顺内部编码
	private String ifindCode;
	//截止日期
	private String endDate;
	//投资行业ID
	private String industryId;
	//投资行业明合成
	private String industryName;
	//占净值比例
	private double proportion;
	//市值
	private double marketValue;
	//千位符的市值
	private String marketValues;
	
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getIfindCode() {
		return ifindCode;
	}
	public void setIfindCode(String ifindCode) {
		this.ifindCode = ifindCode;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public double getProportion() {
		return proportion;
	}
	public void setProportion(double proportion) {
		this.proportion = proportion;
	}
	public double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public String getMarketValues() {
		return marketValues;
	}
	public void setMarketValues(String marketValues) {
		this.marketValues = marketValues;
	}
}
