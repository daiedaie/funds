package com.mrbt.details.model;

import java.util.Date;

public class NetValue {
	
	/**
	 * 截止日期
	 */
	private Date netDate;
	/**
	 * 净值
	 */
	private double netValue;
	
	/**
	 * 累计净值
	 */
	private double totalTalue;
	/**
	 * 基金代码
	 */
	private String fundCode;
	/**
	 * 查询开始日期
	 * @return
	 */
	private String stateDate;
	/**
	 * 查询结束日期
	 * @return
	 */
	private String endDate;
	public Date getNetDate() {
		return netDate;
	}
	public void setNetDate(Date netDate) {
		this.netDate = netDate;
	}
	public double getNetValue() {
		return netValue;
	}
	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}
	public double getTotalTalue() {
		return totalTalue;
	}
	public void setTotalTalue(double totalTalue) {
		this.totalTalue = totalTalue;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getStateDate() {
		return stateDate;
	}
	public void setStateDate(String stateDate) {
		this.stateDate = stateDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
