package com.mrbt.mvc.model.funds;

import java.io.Serializable;

import com.mrbt.utils.FastJsonUtils;

/**
 * 用于查询基金的最新两条净值数据
 * @author Administrator
 *
 */
public class FundLatestNetValue implements Serializable{
	private static final long serialVersionUID = 1L;
	//基金代码
	private String fundCode;
	//单位净值
	private double unitNetValue;
	//累计净值
	private double cumulativeNetValue;
	//创建时间
	private String ctimeThs034;
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public double getUnitNetValue() {
		return unitNetValue;
	}
	public void setUnitNetValue(double unitNetValue) {
		this.unitNetValue = unitNetValue;
	}
	public double getCumulativeNetValue() {
		return cumulativeNetValue;
	}
	public void setCumulativeNetValue(double cumulativeNetValue) {
		this.cumulativeNetValue = cumulativeNetValue;
	}
	public String getCtimeThs034() {
		return ctimeThs034;
	}
	public void setCtimeThs034(String ctimeThs034) {
		this.ctimeThs034 = ctimeThs034;
	}
	public String toString(){
		return FastJsonUtils.Obj2Json(this);
	}
	

}
