package com.mrbt.recom.model;

/**
 * 调出调入设计
 * 
 * @author LZD
 *
 */
public class TheTakingOf {

	// 基金代码
	private String fundCode;
	// 简称
	private String shortName;
	// 因由(调入、调出)
	private String reason;

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
