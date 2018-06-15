package com.mrbt.contrast.dao;

import java.util.List;

import com.mrbt.contrast.model.FundCompany;
import com.mrbt.contrast.model.SelectContrastData;

public interface ContrastMapper {

	List<FundCompany> getFundCompany();

	String getFundRisk(String fundCode);

	SelectContrastData getApplyRedeemState(String fundCode, int i);

	SelectContrastData getCumulativeDividend(String fundCode);

	List<SelectContrastData> getManagersInfo(String fundCode);

	Integer getAdminCount(String managersId);

	List<String> getIndustryDistribution(String fundCode);
	
}
