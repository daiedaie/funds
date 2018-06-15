package com.mrbt.mvc.service.client.hotsale;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.vo.FundHotSalesVo;

public interface FundHotSalesHandlerClient {
	public void finish();

	public void rollback();

	public String select(int start, int rows) throws SolrServerException,
			IOException;

	public void deleteByName(String fund_name) throws SolrServerException,
			IOException;

	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException;

	public String selectAll() throws IOException, SolrServerException;

	void addBeans(List<FundHotSalesVo> fundHotSalesVos)
			throws SolrServerException, IOException;

	void add(FundHotSalesVo fundHotSalesVo) throws SolrServerException,
			IOException;

}
