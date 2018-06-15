package com.mrbt.details.model;

public class ReportingPeriod {
	
	private String thsKey;
	/**
	 * 基金代码
	 */
	private String fundCode;
	/**
	 * 报告时间
	 */
	private String reportDate;
	/**
	 * 期间申购
	 */
	private double purchase;
	/**
	 * 期间赎回
	 */
	private double redeem;
	/**
	 * 期末申购
	 */
	private double thefinal;
	/**
	 * 变动率
	 */
	private double rateChange;
	/**
	 * 净值规模变动
	 */
	private double netAssetSize;
	
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public double getPurchase() {
		return purchase;
	}
	public void setPurchase(double purchase) {
		this.purchase = purchase;
	}
	public double getRedeem() {
		return redeem;
	}
	public void setRedeem(double redeem) {
		this.redeem = redeem;
	}
	public double getThefinal() {
		return thefinal;
	}
	public void setThefinal(double thefinal) {
		this.thefinal = thefinal;
	}
	public double getRateChange() {
		return rateChange;
	}
	public void setRateChange(double rateChange) {
		this.rateChange = rateChange;
	}
	public String getThsKey() {
		return thsKey;
	}
	public void setThsKey(String thsKey) {
		this.thsKey = thsKey;
	}
	public double getNetAssetSize() {
		return netAssetSize;
	}
	public void setNetAssetSize(double netAssetSize) {
		this.netAssetSize = netAssetSize;
	}
}
