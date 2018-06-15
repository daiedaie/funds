package com.mrbt.mvc.service.service.suggest;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.service.client.suggest.FundSuggestHandlerClient;
import com.mrbt.mvc.vo.FundSuggestion;

@Service
public class FundSuggestHandlerServiceImpl implements FundSuggestHandlerService{

	@Autowired
	@Qualifier("fundSuggestHandlerClient")
	private FundSuggestHandlerClient fundSuggestHandlerClient;
	@Override
	public String suggest(String param) throws IOException, SolrServerException {
		return fundSuggestHandlerClient.suggest(param);
	}
	@Override
	public void finish() {
		fundSuggestHandlerClient.finish();
	}
	
	@Override
	public void rollback() {
		fundSuggestHandlerClient.rollback();
	}
	@Override
	public void addBean(FundSuggestion fundSuggestion) throws IOException, SolrServerException {
		fundSuggestHandlerClient.addBean(fundSuggestion);
		
	}
	@Override
	public void addBeans(List<FundSuggestion> fundSuggestions) throws IOException, SolrServerException {
		fundSuggestHandlerClient.addBeans(fundSuggestions);
	}
}
