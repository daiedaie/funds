package com.mrbt.mvc.service.service.recommend;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.service.client.recommend.FundRecommendHandlerClient;

@Service
public class FundRecommendHandlerServiceImpl implements
		FundRecommendHandlerService {
	@Autowired
	@Qualifier("fundRecommendHandlerClient")
	private FundRecommendHandlerClient fundRecommendHandlerClient;

	@Override
	public String select(int start, int rows) throws SolrServerException,
			IOException {
		return fundRecommendHandlerClient.select(start, rows);
	}

	@Override
	public void finish() {
		fundRecommendHandlerClient.finish();
	}

	@Override
	public void rollback() {
		fundRecommendHandlerClient.rollback();
	}

	@Override
	public void add(Map<String, String> fundrecommend)
			throws SolrServerException, IOException {
		fundRecommendHandlerClient.add(fundrecommend);
	}


	@Override
	public void deleteByName(String fund_name) throws SolrServerException, IOException {
		fundRecommendHandlerClient.deleteByName(fund_name);
	}
	@Override
	public void deleteByCode(String fund_code) throws SolrServerException, IOException{
		fundRecommendHandlerClient.deleteByCode(fund_code);
	}

	@Override
	public String selectAll() throws SolrServerException, IOException {
		return fundRecommendHandlerClient.selectAll();
	}
	@Override
	public String select(int start, int rows, String type)
			throws SolrServerException, IOException {
		return fundRecommendHandlerClient.select(start, rows,type);
	}
}
