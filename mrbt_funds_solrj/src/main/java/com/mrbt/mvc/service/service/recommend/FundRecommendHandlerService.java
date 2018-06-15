package com.mrbt.mvc.service.service.recommend;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.service.baseservice.BaseService;

public interface FundRecommendHandlerService extends BaseService {
	public String select(int start, int rows) throws SolrServerException,
			IOException;

	public void add(Map<String, String> fundrecommend)
			throws SolrServerException, IOException;

	/*public void add(String recommend_fund_type,
			String recommend_fund_abbreviation, String recommend_fund_code,
			String recommend_fund_buyrate,
			String recommend_fund_latestnetvalue,
			String recommend_fund_nearlymonthgain,
			String recommend_fund_nearlythreemonthgain,
			String recommend_fund_recommendgain,
			String recommend_fund_recommendreason) throws SolrServerException,
			IOException;

	public void update(String recommend_fund_type,
			String recommend_fund_abbreviation, String recommend_fund_code,
			String recommend_fund_buyrate,
			String recommend_fund_latestnetvalue,
			String recommend_fund_nearlymonthgain,
			String recommend_fund_nearlythreemonthgain,
			String recommend_fund_recommendgain,
			String recommend_fund_recommendreason) throws SolrServerException,
			IOException;*/

	public void deleteByName(String fund_name) throws SolrServerException,
			IOException;

	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException;

	public String selectAll() throws SolrServerException, IOException;

	public String select(int start, int rows, String type) throws SolrServerException, IOException;

}
