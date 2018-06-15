package com.mrbt.contrast.model;

public class ContrastFoundsInfo {
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 基金简称
	 */
	private String shortName;
	/**
	 * 基金代码
	 */
	private String fundCode;
	/**
	 * 基金类型
	 */
	private String type;
	/**
	 * 基金净值
	 */
	private String netValue;
	/**
	 * 风险等级
	 */
	private String risk;
	/**
	 * 申购状态
	 */
	private String applyState;
	/**
	 * 赎回状态
	 */
	private String redeemState;
	/**
	 * 规模
	 */
	private String scale;
	/**
	 * 管理人
	 */
	private String adminName;
	/**
	 * 申购费率
	 */
	private String applyRates;
	/**
	 * 累计分红
	 */
	private double cumulativeDividend;
	/**
	 * 成立日期
	 */
	private String upDate;
	/**
	 * 近一周业绩增长率
	 */
	private String weekFeat;
	/**
	 * 近一月业绩增长率
	 */
	private String monthFeat;
	/**
	 * 近三月业绩增长率
	 */
	private String seasonFeat;
	/**
	 *近六月业绩增长率 
	 */
	private String halfYearFeat;
	/**
	 * 近一年业绩增长率
	 */
	private String oneYearFeat;
	/**
	 * 近一年沪深300指数
	 */
	private String oneYearCSI300;
	/**
	 * 近一年上证指数
	 */
	private String oneYearSSE;
	/**
	 * 近三年业绩增长率
	 */
	private String threeYearFeat;
	/**
	 * 成立以来业绩增长率
	 */
	private String fullFeat;
	/**
	 * 七日年化收益率
	 */
	private String  sevenRateReturn;
	/**
	 * 万分收益率
	 */
	private String extremeYield;
	/**
	 * 近三月最大回撤
	 */
	private String marchWithdrawal;
	/**
	 * 近半年最大回撤
	 */
	private String halfYearWithdrawal;
	/**
	 * 近一年最大回撤
	 */
	private String oneYearWithdrawal;
	/**
	 *  sharpe值
	 */
	private String sharpeValue;
	/**
	 * 收益标准差
	 */
	private String incomeDeviation;
	/**
	 * 基金经理
	 */
	private String fundManagers;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 从业时间
	 */
	private String workingTime;
	/**
	 * 旗下基金数量
	 */
	private String adminFundsNum;
	/**
	 * 同类基金期间收益排名
	 */
	private String rankingRange;
	/**
	 *重仓债券1 
	 */
	private String theLargestBond1;
	/**
	 *重仓债券2 
	 */
	private String theLargestBond2;
	/**
	 *重仓债券3
	 */
	private String theLargestBond3;
	/**
	 *重仓债券4
	 */
	private String theLargestBond4;	
	/**
	 *重仓债券5
	 */
	private String theLargestBond5;
	/**
	 * 重仓行业1
	 */
	private String theLargestIndustry1;
	/**
	 * 重仓行业2
	 */
	private String theLargestIndustry2;
	/**
	 * 重仓行业3
	 */
	private String theLargestIndustry3;
	/**
	 * 重仓行业4
	 */
	private String theLargestIndustry4;
	/**
	 * 重仓行业5
	 */
	private String theLargestIndustry5;
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNetValue() {
		return netValue;
	}
	public void setNetValue(String netValue) {
		this.netValue = netValue;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public String getApplyState() {
		return applyState;
	}
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	public String getRedeemState() {
		return redeemState;
	}
	public void setRedeemState(String redeemState) {
		this.redeemState = redeemState;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getApplyRates() {
		return applyRates;
	}
	public void setApplyRates(String applyRates) {
		this.applyRates = applyRates;
	}
	public String getUpDate() {
		return upDate;
	}
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
	public String getWeekFeat() {
		return weekFeat;
	}
	public void setWeekFeat(String weekFeat) {
		this.weekFeat = weekFeat;
	}
	public String getMonthFeat() {
		return monthFeat;
	}
	public void setMonthFeat(String monthFeat) {
		this.monthFeat = monthFeat;
	}
	public String getSeasonFeat() {
		return seasonFeat;
	}
	public void setSeasonFeat(String seasonFeat) {
		this.seasonFeat = seasonFeat;
	}
	public String getHalfYearFeat() {
		return halfYearFeat;
	}
	public void setHalfYearFeat(String halfYearFeat) {
		this.halfYearFeat = halfYearFeat;
	}
	public String getOneYearFeat() {
		return oneYearFeat;
	}
	public void setOneYearFeat(String oneYearFeat) {
		this.oneYearFeat = oneYearFeat;
	}
	public String getOneYearCSI300() {
		return oneYearCSI300;
	}
	public void setOneYearCSI300(String oneYearCSI300) {
		this.oneYearCSI300 = oneYearCSI300;
	}
	public String getOneYearSSE() {
		return oneYearSSE;
	}
	public void setOneYearSSE(String ongYearSSE) {
		this.oneYearSSE = ongYearSSE;
	}
	public String getThreeYearFeat() {
		return threeYearFeat;
	}
	public void setThreeYearFeat(String threeYearFeat) {
		this.threeYearFeat = threeYearFeat;
	}
	public String getFullFeat() {
		return fullFeat;
	}
	public void setFullFeat(String fullFeat) {
		this.fullFeat = fullFeat;
	}
	public String getSevenRateReturn() {
		return sevenRateReturn;
	}
	public void setSevenRateReturn(String sevenRateReturn) {
		this.sevenRateReturn = sevenRateReturn;
	}
	public String getExtremeYield() {
		return extremeYield;
	}
	public void setExtremeYield(String extremeYield) {
		this.extremeYield = extremeYield;
	}
	public String getMarchWithdrawal() {
		return marchWithdrawal;
	}
	public void setMarchWithdrawal(String marchWithdrawal) {
		this.marchWithdrawal = marchWithdrawal;
	}
	public String getHalfYearWithdrawal() {
		return halfYearWithdrawal;
	}
	public void setHalfYearWithdrawal(String halfYearWithdrawal) {
		this.halfYearWithdrawal = halfYearWithdrawal;
	}
	public String getOneYearWithdrawal() {
		return oneYearWithdrawal;
	}
	public void setOneYearWithdrawal(String oneYearWithdrawal) {
		this.oneYearWithdrawal = oneYearWithdrawal;
	}
	public String getSharpeValue() {
		return sharpeValue;
	}
	public void setSharpeValue(String sharpeValue) {
		this.sharpeValue = sharpeValue;
	}
	public String getIncomeDeviation() {
		return incomeDeviation;
	}
	public void setIncomeDeviation(String incomeDeviation) {
		this.incomeDeviation = incomeDeviation;
	}
	public String getFundManagers() {
		return fundManagers;
	}
	public void setFundManagers(String fundManagers) {
		this.fundManagers = fundManagers;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}
	public String getAdminFundsNum() {
		return adminFundsNum;
	}
	public void setAdminFundsNum(String adminFundsNum) {
		this.adminFundsNum = adminFundsNum;
	}
	public String getRankingRange() {
		return rankingRange;
	}
	public void setRankingRange(String rankingRange) {
		this.rankingRange = rankingRange;
	}
	public String getTheLargestBond1() {
		return theLargestBond1;
	}
	public void setTheLargestBond1(String theLargestBond1) {
		this.theLargestBond1 = theLargestBond1;
	}
	public String getTheLargestBond2() {
		return theLargestBond2;
	}
	public void setTheLargestBond2(String theLargestBond2) {
		this.theLargestBond2 = theLargestBond2;
	}
	public String getTheLargestBond3() {
		return theLargestBond3;
	}
	public void setTheLargestBond3(String theLargestBond3) {
		this.theLargestBond3 = theLargestBond3;
	}
	public String getTheLargestBond4() {
		return theLargestBond4;
	}
	public void setTheLargestBond4(String theLargestBond4) {
		this.theLargestBond4 = theLargestBond4;
	}
	public String getTheLargestBond5() {
		return theLargestBond5;
	}
	public void setTheLargestBond5(String theLargestBond5) {
		this.theLargestBond5 = theLargestBond5;
	}
	public String getTheLargestIndustry1() {
		return theLargestIndustry1;
	}
	public void setTheLargestIndustry1(String theLargestIndustry1) {
		this.theLargestIndustry1 = theLargestIndustry1;
	}
	public String getTheLargestIndustry2() {
		return theLargestIndustry2;
	}
	public void setTheLargestIndustry2(String theLargestIndustry2) {
		this.theLargestIndustry2 = theLargestIndustry2;
	}
	public String getTheLargestIndustry3() {
		return theLargestIndustry3;
	}
	public void setTheLargestIndustry3(String theLargestIndustry3) {
		this.theLargestIndustry3 = theLargestIndustry3;
	}
	public String getTheLargestIndustry4() {
		return theLargestIndustry4;
	}
	public void setTheLargestIndustry4(String theLargestIndustry4) {
		this.theLargestIndustry4 = theLargestIndustry4;
	}
	public String getTheLargestIndustry5() {
		return theLargestIndustry5;
	}
	public void setTheLargestIndustry5(String theLargestIndustry5) {
		this.theLargestIndustry5 = theLargestIndustry5;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public double getCumulativeDividend() {
		return cumulativeDividend;
	}
	public void setCumulativeDividend(double cumulativeDividend) {
		this.cumulativeDividend = cumulativeDividend;
	}
}

