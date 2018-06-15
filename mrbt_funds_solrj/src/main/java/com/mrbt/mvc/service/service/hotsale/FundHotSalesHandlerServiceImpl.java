package com.mrbt.mvc.service.service.hotsale;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.service.client.hotsale.FundHotSalesHandlerClient;

@Service
public class FundHotSalesHandlerServiceImpl implements
		FundHotSalesHandlerService {
	@Autowired
	@Qualifier("fundHotSalesHandlerClient")
	private FundHotSalesHandlerClient fundHotSalesHandlerClient;

	@Override
	public String select(int start, int rows) throws SolrServerException,
			IOException {
		return fundHotSalesHandlerClient.select(start, rows);
	}

	@Override
	public void finish() {
		fundHotSalesHandlerClient.finish();
	}

	@Override
	public void rollback() {
		fundHotSalesHandlerClient.rollback();
	}

	@Override
	public void deleteByName(String fund_name) throws SolrServerException,
			IOException {
		fundHotSalesHandlerClient.deleteByName(fund_name);

	}

	@Override
	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException {
		fundHotSalesHandlerClient.deleteByCode(fund_code);

	}

	@Override
	public String selectAll() throws IOException, SolrServerException {
		return fundHotSalesHandlerClient.selectAll();
	}

}
