package com.mrbt.details.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface FundsDetailsService {

	public JSONObject getBaseInfo(String id);

	public String findFundManagersName(String f001_ths035);

	public JSONObject getIndustrAlloca(String id, String year);

	public JSONObject getNetValueChart(String id, int interval, int type);

	public JSONArray getFundsManagersInfo(String id);

	public JSONObject getFundPositions(String id, String year);

	public JSONObject getIndustryAllocaCompare(String id);

	public JSONArray getFundEvaluation(String id);

	public JSONObject getReportingPeriod(String id);

	public JSONObject getRateInfo(String id);

}
