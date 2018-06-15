package com.mrbt.mvc.service.service.netvalue;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.service.client.netvalue.FundNetValueHandlerClient;

@Service
public class FundNetValueHandlerServiceImpl implements FundNetValueHandlerService{
	@Autowired
	@Qualifier("fundNetValueHandlerClient")
	private FundNetValueHandlerClient fundNetValueHandlerClient;
	@Override
	public void finish() {
		fundNetValueHandlerClient.finish();
	}
	
	@Override
	public void rollback() {
		fundNetValueHandlerClient.rollback();
	}

	@Override
	public String getFundNetValueAndYearGain(String fund_code) throws SolrServerException, IOException {
		return fundNetValueHandlerClient.selectNetValueAndYearGain(fund_code);
	}
}
