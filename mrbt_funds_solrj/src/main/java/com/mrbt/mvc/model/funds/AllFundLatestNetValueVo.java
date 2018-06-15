package com.mrbt.mvc.model.funds;

import java.math.BigDecimal;


/**
 * 用于查询THS035表/THS001表/THS043表的全部基金的最新净值
 * 包括基金代码,时间,净值
 * 
 *
 */
public class AllFundLatestNetValueVo {
	private String f002Ths001;
	private String ctimeThs043;
	//不能为字符串类型,否则可能出现首位为0省略的情况
	private BigDecimal f003Ths043;
	public String getF002Ths001() {
		return f002Ths001;
	}
	public void setF002Ths001(String f002Ths001) {
		this.f002Ths001 = f002Ths001;
	}
	public String getCtimeThs043() {
		return ctimeThs043;
	}
	public void setCtimeThs043(String ctimeThs043) {
		this.ctimeThs043 = ctimeThs043;
	}
	public BigDecimal getF003Ths043() {
		return f003Ths043;
	}
	public void setF003Ths043(BigDecimal f003Ths043) {
		this.f003Ths043 = f003Ths043;
	}
	
}
