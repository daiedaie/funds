package com.mrbt.mvc.service.service.netvalue;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

public interface FundNetValueHandlerService {

	void finish();

	void rollback();

	String getFundNetValueAndYearGain(String fund_code) throws SolrServerException, IOException;


}
