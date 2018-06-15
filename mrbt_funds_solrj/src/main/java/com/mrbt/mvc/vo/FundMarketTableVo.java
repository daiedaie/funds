package com.mrbt.mvc.vo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class FundMarketTableVo implements Serializable {
	/**
	 * 基金市场,对应数据库表funds_market
	 */
	private static final long serialVersionUID = 2877762234039710902L;
	@Field(value="market_fund_code")
	private String funds_code;
	
	@Field(value="market_fund_isrecommcreatetime")
	private String create_time;
	
	@Field(value="market_fund_isrecomm")
	private String is_recomm;
	
	@Field(value="market_fund_recommorder")
	private Integer recomm_order;
	
	@Field(value="market_fund_codeinner")
	private String funds_code_inner;
	
	@Field(value="market_fund_recommendreason")
	private String recomm_reason;
	
	@Field(value="market_fund_theme")
	private String funds_theme;

	public String getFunds_code() {
		return funds_code;
	}

	public String getRecomm_reason() {
		return recomm_reason;
	}

	public void setRecomm_reason(String recomm_reason) {
		this.recomm_reason = recomm_reason;
	}

	public String getFunds_theme() {
		return funds_theme;
	}

	public void setFuns_theme(String funds_theme) {
		this.funds_theme = funds_theme;
	}

	public void setFunds_code(String funds_code) {
		this.funds_code = funds_code;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getIs_recomm() {
		return is_recomm;
	}

	public void setIs_recomm(String is_recomm) {
		this.is_recomm = is_recomm;
	}

	public Integer getRecomm_order() {
		return recomm_order;
	}

	public void setRecomm_order(Integer recomm_order) {
		this.recomm_order = recomm_order;
	}

	public String getFunds_code_inner() {
		return funds_code_inner;
	}

	public void setFunds_code_inner(String funds_code_inner) {
		this.funds_code_inner = funds_code_inner;
	}
	
	

}
