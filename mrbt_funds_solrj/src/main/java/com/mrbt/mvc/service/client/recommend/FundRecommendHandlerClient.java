package com.mrbt.mvc.service.client.recommend;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.vo.FundRecommendVo;

public interface FundRecommendHandlerClient {
	public String select(int start, int rows) throws SolrServerException,
			IOException;

	public void finish();

	public void rollback();

	public void deleteByName(String fund_name) throws SolrServerException,
			IOException;

	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException;

	public String selectAll() throws SolrServerException, IOException;

	void add(Map<String, String> params) throws SolrServerException,
			IOException;

	void add(List<Map<String, String>> list) throws SolrServerException,
			IOException;

	void add(FundRecommendVo fundRecommendVo) throws SolrServerException,
			IOException;

	void addBeans(List<FundRecommendVo> fundRecommendVos)
			throws SolrServerException, IOException;

	/***
	 * 推荐基金列表查询
	 */
	public String select(int start, int rows, String type) throws IOException,
			SolrServerException;

}
