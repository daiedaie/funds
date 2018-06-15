package com.mrbt.mvc.service.client.suggest;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.vo.FundSuggestion;



public interface FundSuggestHandlerClient{

	String suggest(String param) throws IOException, SolrServerException;

	void finish();

	void rollback();

	void addBean(FundSuggestion fundSuggestion) throws IOException, SolrServerException;

	void addBeans(List<FundSuggestion> fundSuggestions) throws IOException, SolrServerException;

}
