package com.mrbt.mvc.service.service.hotsale;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.service.baseservice.BaseService;

public interface FundHotSalesHandlerService extends BaseService {
	public void finish();

	public void rollback();

	public String select(int start, int rows) throws SolrServerException,
			IOException;

	public void deleteByName(String fund_name) throws SolrServerException,
			IOException;

	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException;

	public String selectAll() throws IOException, SolrServerException;
}
