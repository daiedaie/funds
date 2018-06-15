package com.mrbt.mvc.service.service.castsurely;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.service.baseservice.BaseService;

public interface FundCastSurelyHandlerService extends BaseService {
	public void finish();

	public void rollback();

	public String select(int start, int rows) throws SolrServerException,
			IOException;
	public String selectAll() throws IOException, SolrServerException;

}
