package com.mrbt.mvc.vo;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.solr.client.solrj.beans.Field;

import com.mrbt.utils.FastJsonUtils;

public class FundDetailVo implements Serializable {
	/**
	 * 基金详情
	 */
	private static final long serialVersionUID = 1L;
	static DecimalFormat dcmFmt = new DecimalFormat("0.0000");
	// 基金类型编码 0-股票型；1-债券型；2-混合型；3-货币型；6-基金型；7-保本型
	//@Field(value = "detail_fund_type")
	@Field(value = "market_fund_castsurely")
	private String type;
	// 基金类型名称
	@Field(value = "market_fund_type")
	private String type_name;
	// 基金简称
	@Field(value = "market_fund_detailShortName")
	private String shortName;
	// 基金状态 1-开放；2-暂停；3-未开放；4-终止
	@Field(value = "market_fund_state")
	private String state;
	// 基金代码
	@Field(value = "market_fund_code")
	private String code;
	// 基金规模
	@Field(value = "market_fund_scale")
	private double scale;
	// 成立日期
	@Field(value = "market_fund_upDate")
	private String upDate;
	// 单位净值
	@Field(value = "market_fund_latestnetvalue")
	private double netValue;
	// 净值时间
	@Field(value = "market_fund_navTime")
	private String navTime;
	// 累计净值
	@Field(value = "market_fund_totalTalue")
	private double totalTalue;
	// 购买费率
	@Field(value = "market_fund_ratez")
	private double ratez;
	// 购买费率
	@Field(value = "market_fund_rate")
	private double rate;
	// 分险等级
	@Field(value = "market_fund_risk")
	private String risk;
	// 基金全称
	@Field(value = "market_fund_name")
	private String fullName;
	// 基金管理人ID
	@Field(value = "market_fund_fundManagerId")
	private String fundManagerId;
	// 基金管理人,简称
	@Field(value = "market_fund_shortcompany")
	private String fundManager;
	// 基金托管人ID
	@Field(value = "market_fund_custodianId")
	private String custodianId;
	// 基金托管人
	@Field(value = "market_fund_custodian")
	private String custodian;
	// 发行日期
	@Field(value = "market_fund_issueDate")
	private String issueDate;
	// 业绩比较基准
	@Field(value = "market_fund_perComBen")
	private String perComBen;
	// 同花顺内部关联ID
	@Field(value = "market_fund_codeinner")
	private String f001_ths035;

	public static DecimalFormat getDcmFmt() {
		return dcmFmt;
	}

	public static void setDcmFmt(DecimalFormat dcmFmt) {
		FundDetailVo.dcmFmt = dcmFmt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getState() {
		// 1-开放；2-暂停；3-未开放；4-终止
		if (state.equals("1")) {
			return "开放";
		}
		if (state.equals("2")) {
			return "暂停";
		}
		if (state.equals("3")) {
			return "为开放";
		}
		if (state.equals("4")) {
			return "终止";
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getScale() {
		return Double.parseDouble(dcmFmt.format(scale / 100000000));
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public String getUpDate() {
		return upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public double getNetValue() {
		return netValue;
	}

	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}

	public String getNavTime() {
		return navTime;
	}

	public void setNavTime(String navTime) {
		this.navTime = navTime;
	}

	public double getTotalTalue() {
		return totalTalue;
	}

	public void setTotalTalue(double totalTalue) {
		this.totalTalue = totalTalue;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFundManagerId() {
		return fundManagerId;
	}

	public void setFundManagerId(String fundManagerId) {
		this.fundManagerId = fundManagerId;
	}

	public String getFundManager() {
		return fundManager;
	}

	public void setFundManager(String fundManager) {
		this.fundManager = fundManager;
	}

	public String getCustodianId() {
		return custodianId;
	}

	public void setCustodianId(String custodianId) {
		this.custodianId = custodianId;
	}

	public String getCustodian() {
		return custodian;
	}

	public void setCustodian(String custodian) {
		this.custodian = custodian;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getPerComBen() {
		return perComBen;
	}

	public void setPerComBen(String perComBen) {
		this.perComBen = perComBen;
	}

	public double getRatez() {
		return ratez;
	}

	public void setRatez(double ratez) {
		this.ratez = ratez;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getF001_ths035() {
		return f001_ths035;
	}

	public void setF001_ths035(String f001_ths035) {
		this.f001_ths035 = f001_ths035;
	}

	public String toString() {
		return FastJsonUtils.Obj2Json(this);
	}
}