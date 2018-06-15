package com.mrbt.mvc.service.client.netvalue;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

public interface FundNetValueHandlerClient {
	public void finish();

	public void rollback();

	public String selectNetValueAndYearGain(String fund_code)
			throws SolrServerException, IOException;

}
