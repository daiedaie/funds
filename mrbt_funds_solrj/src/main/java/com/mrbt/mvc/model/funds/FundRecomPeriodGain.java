package com.mrbt.mvc.model.funds;

import java.io.Serializable;

public class FundRecomPeriodGain implements Serializable {
	private static final long serialVersionUID = 1L;
	//推荐期收益
	private double gain;
	public double getGain() {
		return gain;
	}
	public void setGain(double gain) {
		this.gain = gain;
	}
	
	

}
