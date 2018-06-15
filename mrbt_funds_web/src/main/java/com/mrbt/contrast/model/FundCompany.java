package com.mrbt.contrast.model;

public class FundCompany {
	/**
	 * 基金ID
	 */
	private String fundCode;
	/**
	 * 基金名称
	 */
	private String fundName;
	/**
	 * 公司ID
	 */
	private String companyId;
	/**
	 * 公司名称
	 */
	private String companyName;
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
