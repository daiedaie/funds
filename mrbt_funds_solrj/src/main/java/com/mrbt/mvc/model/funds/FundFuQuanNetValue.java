package com.mrbt.mvc.model.funds;

import java.io.Serializable;

import com.mrbt.utils.FastJsonUtils;


public class FundFuQuanNetValue implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fundCode;
	private double fuQuanNetValue;
	private String ctimeThs043;
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public double getFuQuanNetValue() {
		return fuQuanNetValue;
	}
	public void setFuQuanNetValue(double fuQuanNetValue) {
		this.fuQuanNetValue = fuQuanNetValue;
	}
	public String getCtimeThs043() {
		return ctimeThs043;
	}
	public void setCtimeThs043(String ctimeThs043) {
		this.ctimeThs043 = ctimeThs043;
	}
	public String toString(){
		return FastJsonUtils.Obj2Json(this);
	}
}
