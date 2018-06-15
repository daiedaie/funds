package com.mrbt.details.model;


public class ManagersName {
	
	/**
	 * 基金经理ID
	 */
	private String id;
	/**
	 * 基金经理姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 学历
	 */
	private String education;
	/**
	 * 上任日期
	 */
	private String servingTime;
	/**
	 * 卸任日期
	 */
	private String outgoingDate;
	/**
	 * 任职天数
	 */
	private int servingDay;
	/**
	 * 任职回报
	 */
	private String inReturn;
	/**
	 * 简介
	 */
	private String desc;
	/**
	 * 基金代码
	 */
	private String fundsCode;
	/**
	 * 基金简称
	 */
	private String shortName;
	/**
	 * 基金类型
	 */
	private String fundsType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		if(sex != null){
			if(sex.replace(" ", "").equals("f")){
				return "女";
			}else{
				return "男";
			}
		}
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getServingTime() {
		return servingTime;
	}
	public void setServingTime(String servingTime) {
		this.servingTime = servingTime;
	}
	public String getOutgoingDate() {
		return outgoingDate;
	}
	public void setOutgoingDate(String outgoingDate) {
		this.outgoingDate = outgoingDate;
	}
	public int getServingDay() {
		return servingDay;
	}
	public void setServingDay(int servingDay) {
		this.servingDay = servingDay;
	}
	public String getInReturn() {
		return inReturn;
	}
	public void setInReturn(String inReturn) {
		this.inReturn = inReturn;
	}
	public String getFundsCode() {
		return fundsCode;
	}
	public void setFundsCode(String fundsCode) {
		this.fundsCode = fundsCode;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFundsType() {
		if(fundsType != null){
			String type = fundsType.replace(" ", "");
			if(type.equals("0")){
				return "股票型";
			}
			if(type.equals("1")){
				return "债券型";
			}
			if(type.equals("2")){
				return "混合型";
			}
			if(type.equals("3")){
				return "货币型";
			}
			if(type.equals("7")){
				return "保本型";
			}
			return "其他";
		}
		return fundsType;
	}
	public void setFundsType(String fundsType) {
		this.fundsType = fundsType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
