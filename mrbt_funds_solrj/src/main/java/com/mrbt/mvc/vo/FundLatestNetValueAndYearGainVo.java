package com.mrbt.mvc.vo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import com.mrbt.utils.FastJsonUtils;

public class FundLatestNetValueAndYearGainVo implements Serializable{
	/**
	 * 返回基金的最新净值和年收益率
	 */
	private static final long serialVersionUID = 1L;
	@Field(value="market_fund_latestnetvalue")
	private double netValue;
	@Field(value="market_fund_yeargain")
	private double yearGain;
	
	public double getNetValue() {
		return netValue;
	}

	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}

	public double getYearGain() {
		return yearGain;
	}

	public void setYearGain(double yearGain) {
		this.yearGain = yearGain;
	}

	public String toString(){
		return FastJsonUtils.Obj2Json(this);
	}

}
