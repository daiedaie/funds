package com.mrbt.oa.mvc.vo.funds;

import com.mrbt.oa.mvc.vo.BaseVo;

import java.math.BigDecimal;
import java.util.Date;

public class FundsRisk extends BaseVo {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column FUNDS_RISK.FUNDS_CODE
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	private String fundsCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column FUNDS_RISK.RISK_LEVEL
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	private String riskLevel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column FUNDS_RISK.CREATE_TIME
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	private Date createTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column FUNDS_RISK.FUNDS_CODE
	 * @return  the value of FUNDS_RISK.FUNDS_CODE
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	public String getFundsCode() {
		return fundsCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column FUNDS_RISK.FUNDS_CODE
	 * @param fundsCode  the value for FUNDS_RISK.FUNDS_CODE
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	public void setFundsCode(String fundsCode) {
		this.fundsCode = fundsCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column FUNDS_RISK.RISK_LEVEL
	 * @return  the value of FUNDS_RISK.RISK_LEVEL
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	public String getRiskLevel() {
		return riskLevel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column FUNDS_RISK.RISK_LEVEL
	 * @param riskLevel  the value for FUNDS_RISK.RISK_LEVEL
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column FUNDS_RISK.CREATE_TIME
	 * @return  the value of FUNDS_RISK.CREATE_TIME
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column FUNDS_RISK.CREATE_TIME
	 * @param createTime  the value for FUNDS_RISK.CREATE_TIME
	 * @mbggenerated  Fri Jul 01 14:18:29 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "FundsRisk [fundsCode=" + fundsCode + ", riskLevel=" + riskLevel
				 + ", createTime=" + createTime + "]";
	}
}