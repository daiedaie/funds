package com.mrbt.mvc.vo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import com.mrbt.utils.FastJsonUtils;

public class FundMarketVo implements Serializable {
	/**
	 * 基金的基础索引
	 */
	private static final long serialVersionUID = 1L;
	/****
	/**
	 * 是否定投的标志;基金类型;基金规模;基金公司;基金主题作为筛选条件。基金名称、拼音简称、代码，基金规模，申购费率 单位净值
	 */
	/**
	 * 基金超市 基金类型，基金名称，基金代码，基金最新净值，基金日涨幅，基金近一月，基金近三月，基金今年来，基金近三年来，
	 */
	// 基金类型
	@Field(value = "market_fund_type")
	private String market_fund_type;
	@Field(value = "market_fund_shortname")
	private String market_fund_shortname;
	// 基金全称名称
	@Field(value = "market_fund_name")
	private String market_fund_name;
	// 基金代码
	@Field(value = "market_fund_code")
	private String market_fund_code;
	// 基金规模
	@Field(value = "market_fund_scale")
	private double market_fund_scale;
	// 申购费率
	@Field(value = "market_fund_buyrate")
	private double market_fund_buyrate;
	// 最新净值
	@Field(value = "market_fund_latestnetvalue")
	private double market_fund_latestnetvalue;
	// 日涨幅
	@Field(value = "market_fund_daygain")
	private double market_fund_daygain;
	// 周涨幅
	@Field(value = "market_fund_weekgain")
	private double market_fund_weekgain;
	// 近一个月
	@Field(value = "market_fund_nearlymonthgain")
	private double market_fund_nearlymonthgain;
	// 近三个月
	@Field(value = "market_fund_nearlythreemonthgain")
	private double market_fund_nearlythreemonthgain;
	// 六个月涨幅
	@Field(value = "market_fund_nearlysixmonthgain")
	private double market_fund_nearlysixmonthgain;
	// 今年来
	@Field(value = "market_fund_yeargain")
	private double market_fund_yeargain;
	// 近三年
	@Field(value = "market_fund_threeyeargain")
	private double market_fund_threeyeargain;
	// 是否定投
	@Field(value = "market_fund_castsurely")
	private String market_fund_castsurely;
	// 基金公司
	@Field(value = "market_fund_company")
	private String market_fund_company;
	// 基金主题
	@Field(value = "market_fund_theme")
	private String market_fund_theme;

	// 基金热销标志,默认为0
	@Field(value = "market_fund_hotsale")
	private double market_fund_hotsale;

	// 基金经理
	@Field(value = "market_fund_manager")
	private String market_fund_manager;

	// 基金推荐原因
	@Field(value = "market_fund_recommendreason")
	private String market_fund_recommendreason;

	// 推荐期收益
	@Field(value = "market_fund_recommendgain")
	private double market_fund_recommendgain;

	// 开始推荐的时间
	@Field(value = "market_fund_isrecommcreatetime")
	private String market_fund_isrecommcreatetime;

	// 是否推荐的标志
	@Field(value = "market_fund_isrecomm")
	private String market_fund_isrecomm;

	// 推荐序号
	@Field(value = "market_fund_recommorder")
	private Integer market_fund_recommorder;

	// 推荐codeinner
	@Field(value = "market_fund_codeinner")
	private String market_fund_codeinner;
	// 基金拼音
	@Field(value = "market_fund_pinyin")
	private String market_fund_pinyin;

	// 基金公司简称
	@Field(value = "market_fund_shortcompany")
	private String market_fund_shortcompany;
	
	//基金成立时间
	@Field(value = "market_fund_upDate")
	private String market_fund_upDate;
	
	
	//基金详情简称
	@Field(value = "market_fund_detailShortName")
	private String market_fund_detailShortName;
	// 基金状态 1-开放；2-暂停；3-未开放；4-终止
	@Field(value = "market_fund_state")
	private String market_fund_state;
	// 净值时间
	@Field(value = "market_fund_navTime")
	private String market_fund_navTime;
	// 累计净值
	@Field(value = "market_fund_totalTalue")
	private double market_fund_totalTalue;
	// 购买费率
	@Field(value = "market_fund_ratez")
	private double market_fund_ratez;
	// 购买费率
	@Field(value = "market_fund_rate")
	private double market_fund_rate;
	// 分险等级
	@Field(value = "market_fund_risk")
	private String market_fund_risk;
	// 基金管理人ID
	@Field(value = "market_fund_fundManagerId")
	private String market_fund_fundManagerId;
	// 基金托管人ID
	@Field(value = "market_fund_custodianId")
	private String market_fund_custodianId;
	// 基金托管人
	@Field(value = "market_fund_custodian")
	private String market_fund_custodian;
	// 发行日期
	@Field(value = "market_fund_issueDate")
	private String market_fund_issueDate;
	// 业绩比较基准
	@Field(value = "market_fund_perComBen")
	private String market_fund_perComBen;

	public String getMarket_fund_pinyin() {
		return market_fund_pinyin;
	}

	public void setMarket_fund_pinyin(String market_fund_pinyin) {
		this.market_fund_pinyin = market_fund_pinyin;
	}

	public String getMarket_fund_type() {
		return market_fund_type;
	}

	public void setMarket_fund_type(String market_fund_type) {
		this.market_fund_type = market_fund_type;
	}

	public String getMarket_fund_shortname() {
		return market_fund_shortname;
	}

	public void setMarket_fund_shortname(String market_fund_shortname) {
		this.market_fund_shortname = market_fund_shortname;
	}

	public String getMarket_fund_name() {
		return market_fund_name;
	}

	public void setMarket_fund_name(String market_fund_name) {
		this.market_fund_name = market_fund_name;
	}

	public String getMarket_fund_code() {
		return market_fund_code;
	}

	public void setMarket_fund_code(String market_fund_code) {
		this.market_fund_code = market_fund_code;
	}

	public double getMarket_fund_scale() {
		return market_fund_scale;
	}

	public void setMarket_fund_scale(double market_fund_scale) {
		this.market_fund_scale = market_fund_scale;
	}

	public double getMarket_fund_buyrate() {
		return market_fund_buyrate;
	}

	public void setMarket_fund_buyrate(double market_fund_buyrate) {
		this.market_fund_buyrate = market_fund_buyrate;
	}

	public double getMarket_fund_latestnetvalue() {
		return market_fund_latestnetvalue;
	}

	public void setMarket_fund_latestnetvalue(double market_fund_latestnetvalue) {
		this.market_fund_latestnetvalue = market_fund_latestnetvalue;
	}

	public double getMarket_fund_daygain() {
		return market_fund_daygain;
	}

	public void setMarket_fund_daygain(double market_fund_daygain) {
		this.market_fund_daygain = market_fund_daygain;
	}

	public double getMarket_fund_nearlymonthgain() {
		return market_fund_nearlymonthgain;
	}

	public void setMarket_fund_nearlymonthgain(
			double market_fund_nearlymonthgain) {
		this.market_fund_nearlymonthgain = market_fund_nearlymonthgain;
	}

	public double getMarket_fund_nearlythreemonthgain() {
		return market_fund_nearlythreemonthgain;
	}

	public void setMarket_fund_nearlythreemonthgain(
			double market_fund_nearlythreemonthgain) {
		this.market_fund_nearlythreemonthgain = market_fund_nearlythreemonthgain;
	}

	public double getMarket_fund_yeargain() {
		return market_fund_yeargain;
	}

	public void setMarket_fund_yeargain(double market_fund_yeargain) {
		this.market_fund_yeargain = market_fund_yeargain;
	}

	public double getMarket_fund_threeyeargain() {
		return market_fund_threeyeargain;
	}

	public void setMarket_fund_threeyeargain(double market_fund_threeyeargain) {
		this.market_fund_threeyeargain = market_fund_threeyeargain;
	}

	public String getMarket_fund_castsurely() {
		return market_fund_castsurely;
	}

	public void setMarket_fund_castsurely(String market_fund_castsurely) {
		this.market_fund_castsurely = market_fund_castsurely;
	}

	public String getMarket_fund_company() {
		return market_fund_company;
	}

	public void setMarket_fund_company(String market_fund_company) {
		this.market_fund_company = market_fund_company;
	}

	public String getMarket_fund_theme() {
		return market_fund_theme;
	}

	public void setMarket_fund_theme(String market_fund_theme) {
		this.market_fund_theme = market_fund_theme;
	}

	public double getMarket_fund_hotsale() {
		return market_fund_hotsale;
	}

	public void setMarket_fund_hotsale(double market_fund_hotsale) {
		this.market_fund_hotsale = market_fund_hotsale;
	}

	public String getMarket_fund_manager() {
		return market_fund_manager;
	}

	public void setMarket_fund_manager(String market_fund_manager) {
		this.market_fund_manager = market_fund_manager;
	}

	public String getMarket_fund_recommendreason() {
		return market_fund_recommendreason;
	}

	public void setMarket_fund_recommendreason(
			String market_fund_recommendreason) {
		this.market_fund_recommendreason = market_fund_recommendreason;
	}

	public double getMarket_fund_recommendgain() {
		return market_fund_recommendgain;
	}

	public void setMarket_fund_recommendgain(double market_fund_recommendgain) {
		this.market_fund_recommendgain = market_fund_recommendgain;
	}

	public String getMarket_fund_isrecommcreatetime() {
		return market_fund_isrecommcreatetime;
	}

	public void setMarket_fund_isrecommcreatetime(
			String market_fund_isrecommcreatetime) {
		this.market_fund_isrecommcreatetime = market_fund_isrecommcreatetime;
	}

	public String getMarket_fund_isrecomm() {
		return market_fund_isrecomm;
	}

	public void setMarket_fund_isrecomm(String market_fund_isrecomm) {
		this.market_fund_isrecomm = market_fund_isrecomm;
	}

	public Integer getMarket_fund_recommorder() {
		return market_fund_recommorder;
	}

	public void setMarket_fund_recommorder(Integer market_fund_recommorder) {
		this.market_fund_recommorder = market_fund_recommorder;
	}

	public String getMarket_fund_codeinner() {
		return market_fund_codeinner;
	}

	public void setMarket_fund_codeinner(String market_fund_codeinner) {
		this.market_fund_codeinner = market_fund_codeinner;
	}

	public double getMarket_fund_weekgain() {
		return market_fund_weekgain;
	}

	public void setMarket_fund_weekgain(double market_fund_weekgain) {
		this.market_fund_weekgain = market_fund_weekgain;
	}

	public double getMarket_fund_nearlysixmonthgain() {
		return market_fund_nearlysixmonthgain;
	}

	public void setMarket_fund_nearlysixmonthgain(
			double market_fund_nearlysixmonthgain) {
		this.market_fund_nearlysixmonthgain = market_fund_nearlysixmonthgain;
	}

	public String getMarket_fund_shortcompany() {
		return market_fund_shortcompany;
	}

	public void setMarket_fund_shortcompany(String market_fund_shortcompany) {
		this.market_fund_shortcompany = market_fund_shortcompany;
	}
	
	public String getMarket_fund_upDate() {
		return market_fund_upDate;
	}

	public void setMarket_fund_upDate(String market_fund_upDate) {
		this.market_fund_upDate = market_fund_upDate;
	}

	public String getMarket_fund_detailShortName() {
		return market_fund_detailShortName;
	}

	public void setMarket_fund_detailShortName(String market_fund_detailShortName) {
		this.market_fund_detailShortName = market_fund_detailShortName;
	}


	public String getMarket_fund_state() {
		return market_fund_state;
	}

	public void setMarket_fund_state(String market_fund_state) {
		this.market_fund_state = market_fund_state;
	}

	public String getMarket_fund_navTime() {
		return market_fund_navTime;
	}

	public void setMarket_fund_navTime(String market_fund_navTime) {
		this.market_fund_navTime = market_fund_navTime;
	}

	public double getMarket_fund_totalTalue() {
		return market_fund_totalTalue;
	}

	public void setMarket_fund_totalTalue(double market_fund_totalTalue) {
		this.market_fund_totalTalue = market_fund_totalTalue;
	}

	public double getMarket_fund_ratez() {
		return market_fund_ratez;
	}

	public void setMarket_fund_ratez(double market_fund_ratez) {
		this.market_fund_ratez = market_fund_ratez;
	}

	public double getMarket_fund_rate() {
		return market_fund_rate;
	}

	public void setMarket_fund_rate(double market_fund_rate) {
		this.market_fund_rate = market_fund_rate;
	}

	public String getMarket_fund_risk() {
		return market_fund_risk;
	}

	public void setMarket_fund_risk(String market_fund_risk) {
		this.market_fund_risk = market_fund_risk;
	}

	public String getMarket_fund_fundManagerId() {
		return market_fund_fundManagerId;
	}

	public void setMarket_fund_fundManagerId(String market_fund_fundManagerId) {
		this.market_fund_fundManagerId = market_fund_fundManagerId;
	}

	public String getMarket_fund_custodianId() {
		return market_fund_custodianId;
	}

	public void setMarket_fund_custodianId(String market_fund_custodianId) {
		this.market_fund_custodianId = market_fund_custodianId;
	}

	public String getMarket_fund_custodian() {
		return market_fund_custodian;
	}

	public void setMarket_fund_custodian(String market_fund_custodian) {
		this.market_fund_custodian = market_fund_custodian;
	}

	public String getMarket_fund_issueDate() {
		return market_fund_issueDate;
	}

	public void setMarket_fund_issueDate(String market_fund_issueDate) {
		this.market_fund_issueDate = market_fund_issueDate;
	}

	public String getMarket_fund_perComBen() {
		return market_fund_perComBen;
	}

	public void setMarket_fund_perComBen(String market_fund_perComBen) {
		this.market_fund_perComBen = market_fund_perComBen;
	}

	public String toString(){
		return FastJsonUtils.Obj2Json(this);
	}

}
