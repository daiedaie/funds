package com.mrbt.details.model;

import java.text.DecimalFormat;

/**
 * get方法中有计算，不要自动生成set、get
 * @author Administrator
 *
 */
public class Details {
		
		static DecimalFormat dcmFmt = new DecimalFormat("0.00");
	
		//基金类型编码  0-股票型；1-债券型；2-混合型；3-货币型；6-基金型；7-保本型 
		private String type;
		//基金类型名称
		private String type_name;
		//基金简称
		private String shortName;
		//基金状态 1-开放；2-暂停；3-未开放；4-终止
		private String state;
		//基金代码
		private String code;
		//基金规模
		private String scale;
		//成立日期
		private String upDate;
		//单位净值
		private String netValue;
		//净值时间
		private String navTime;
		//累计净值
		private String totalTalue;
		//购买费率
		private double ratez;
		//购买费率
		private double rate;
		//分险等级
		private String risk;
		//基金全称
		private String fullName;
		//基金管理人ID
		private String fundManagerId;
		//基金管理人,简称
		private String fundManager;
		//基金托管人ID
		private String custodianId;
		//基金托管人
		private String custodian;
		//发行日期
		private String issueDate;
		//业绩比较基准
		private String perComBen;
		//同花顺内部关联ID
		private String f001_ths035;
		
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
		public String getScale() {
			return scale;
		}
		public void setScale(String scale) {
			this.scale = scale;
		}
		public String getUpDate() {
			return upDate;
		}
		public void setUpDate(String upDate) {
			this.upDate = upDate;
		}
		public String getNetValue() {
			return netValue;
		}
		public void setNetValue(String netValue) {
			this.netValue = netValue;
		}
		public String getNavTime() {
			return navTime;
		}
		public void setNavTime(String navTime) {
			this.navTime = navTime;
		}
		public String getTotalTalue() {
			return totalTalue;
		}
		public void setTotalTalue(String totalTalue) {
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
}