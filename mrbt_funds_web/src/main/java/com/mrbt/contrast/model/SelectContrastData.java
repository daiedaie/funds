package com.mrbt.contrast.model;

import java.util.Date;

public class SelectContrastData {

	private int applyRedeemState;
	private double avgNum;
	private String managersId;
	private String managersName;
	private Date stateWorkTime;
	private Date birthday;
	public int getApplyRedeemState() {
		return applyRedeemState;
	}
	public void setApplyRedeemState(int applyRedeemState) {
		this.applyRedeemState = applyRedeemState;
	}
	public double getAvgNum() {
		return avgNum;
	}
	public void setAvgNum(double avgNum) {
		this.avgNum = avgNum;
	}
	public String getManagersId() {
		return managersId;
	}
	public void setManagersId(String managersId) {
		this.managersId = managersId;
	}
	public String getManagersName() {
		return managersName;
	}
	public void setManagersName(String managersName) {
		this.managersName = managersName;
	}
	public Date getStateWorkTime() {
		return stateWorkTime;
	}
	public void setStateWorkTime(Date stateWorkTime) {
		this.stateWorkTime = stateWorkTime;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
