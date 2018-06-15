package com.mrbt.mvc.service.client.suggest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;
import org.apache.solr.client.solrj.response.Suggestion;
import org.springframework.stereotype.Component;

import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.mvc.vo.FundSuggestion;
import com.mrbt.utils.FastJsonUtils;
import com.mrbt.utils.StringCustomerUtils;

@Component(value = "fundSuggestHandlerClient")
public class FundSuggestHandlerClientImpl extends SolrHandlerClient implements
		FundSuggestHandlerClient {
	private Logger log = LogManager.getLogger(FundSuggestHandlerClientImpl.class);
	@Override
	public String suggest(String param) {
		try{
			log.info("开始suggest查询数据");
			List<String> wordkey = new ArrayList<String>();
			SolrQuery query = new SolrQuery();
			if(StringCustomerUtils.isCN(param)){
				//中文
				param = param.trim();
				query.set("suggest.dictionary", "fundName");
			}
			else if(StringCustomerUtils.isAlpha(param)){
				param = param.trim();
				query.set("suggest.dictionary", "fundPinYin");
				//字母
			}
			else if(StringCustomerUtils.isNumber(param)){
				param = param.trim();
				query.set("suggest.dictionary", "fundCode");
				//数字			
			}
			else{
				//空
				return FastJsonUtils.list2Json(wordkey);
			}
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			query.set("qt", "/suggest");
			query.set("suggest", true);
			query.set("suggest.build", true);
			query.set("wt", "json");
			query.set("suggest.count", "5");
			query.set("q",param);
			query.set("ident", "true");
			QueryResponse response = null;
			response = client.query(query);
			if (response != null) {
				client.close();
				SuggesterResponse suggestResponse = response.getSuggesterResponse();
				if(suggestResponse == null){
					return FastJsonUtils.list2Json(wordkey);
				}
				Map<String, List<Suggestion>> suggests = suggestResponse.getSuggestions();
				for(Map.Entry<String, List<Suggestion>> entry : suggests.entrySet()){
					List<Suggestion> contents = entry.getValue();
					for(Suggestion suggestion : contents){
						wordkey.add(suggestion.getTerm());
					}
				}
			}
			return FastJsonUtils.list2Json(wordkey);
		}catch(Exception e){
			log.info("全文检索查询出现异常");
			return "";
		}
		
	}
	
	@Override
	public void addBean(FundSuggestion fundSuggestion) throws IOException, SolrServerException{
		if (fundSuggestion == null)
			return;
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		client.deleteByQuery("suggest_code:"+ fundSuggestion.getSuggest_code());
		client.addBean(fundSuggestion);
		client.commit();
	}
	@Override
	public void addBeans(List<FundSuggestion> fundSuggestions) throws SolrServerException, IOException{
		if (fundSuggestions == null || fundSuggestions.size() == 0)
			return;
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		for (FundSuggestion fundSuggestion : fundSuggestions) {
			client.deleteByQuery("suggest_code:"+ fundSuggestion.getSuggest_code());
			client.commit();
		}
		client.addBeans(fundSuggestions);
		client.commit();
	}
}
