package com.mrbt.mvc.service.client.castsurely;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

public interface FundCastSurelyHandlerClient {
	public void finish();

	public void rollback();

	public String select(int start, int rows) throws SolrServerException,
			IOException;

	public String selectAll() throws IOException, SolrServerException;

}
