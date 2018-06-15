package com.mrbt.details.model;

public class RateInfo {
	/**
	 * 最小金额
	 */
	private int minPrice;
	/**
	 * 最大金额
	 */
	private int maxPrice;
	/**
	 * 费率
	 */
	private double rate;
	/**
	 * 输出标题
	 */
	private String title;
	/**
	 * 最小月数
	 */
	private double minDay;
	/**
	 * 最大月数
	 */
	private double maxDay;
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getMinDay() {
		return minDay;
	}
	public void setMinDay(double minDay) {
		this.minDay = minDay;
	}
	public double getMaxDay() {
		return maxDay;
	}
	public void setMaxDay(double maxDay) {
		this.maxDay = maxDay;
	}
}
