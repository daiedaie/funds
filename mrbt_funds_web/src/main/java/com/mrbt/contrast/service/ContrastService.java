package com.mrbt.contrast.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ContrastService {

	public JSONObject getFoudCompany();

	public JSONArray getFundsListFromIds(String ids);

	public JSONArray contrasFullTextSearch(String param);

}
