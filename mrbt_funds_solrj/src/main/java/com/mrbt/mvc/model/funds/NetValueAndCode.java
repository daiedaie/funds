package com.mrbt.mvc.model.funds;

import java.io.Serializable;
import java.math.BigDecimal;

public class NetValueAndCode implements Serializable{
	private static final long serialVersionUID = 1L;
	private String fundCode;
	private BigDecimal netValue;
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public BigDecimal getNetValue() {
		return netValue;
	}
	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}
}
