package com.mrbt.details.model;

public class FundPositions {
	
	/**
	 * 基金代码
	 */
	private String fundCode;
	/**
	 * 基金名称
	 */
	private String shortName;
	/**
	 * 最新价
	 */
	private double newPrice;
	/**
	 * 涨跌幅
	 */
	private double riseFall;
	/**
	 * 相关资讯
	 */
	private String desc;
	/**
	 * 占净值比
	 */
	private double netValue;
	/**
	 * 持股数
	 */
	private double positNumber;
	/**
	 * 持仓市值
	 */
	private double positPrice;
	
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
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public double getRiseFall() {
		return riseFall;
	}
	public void setRiseFall(double riseFall) {
		this.riseFall = riseFall;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getNetValue() {
		return netValue;
	}
	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}
	public double getPositNumber() {
		return positNumber;
	}
	public void setPositNumber(double positNumber) {
		this.positNumber = positNumber;
	}
	public double getPositPrice() {
		return positPrice;
	}
	public void setPositPrice(double positPrice) {
		this.positPrice = positPrice;
	}
}
