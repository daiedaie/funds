package com.mrbt.mvc.service.client.suggest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;

import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.utils.FastJsonUtils;

//@Component(value = "fundSuggestSpellCheckHandlerClient")
public class FundSuggestSpellCheckHandlerClientImpl extends SolrHandlerClient //implements FundSuggestHandlerClient 
{
	public static void main(String[] args) throws IOException, SolrServerException {
		FundSuggestSpellCheckHandlerClientImpl spell = new FundSuggestSpellCheckHandlerClientImpl();
		String result = spell.suggest("国泰全球绝对收益型基");
		System.err.println(result);
	}
	//private Logger log = LogManager.getLogger(FundSuggestSpellCheckHandlerClientImpl.class);
	public String suggest(String param) throws IOException, SolrServerException {
		List<String> wordkey = new ArrayList<String>();
		SolrQuery query = new SolrQuery();
//		if(StringCustomerUtils.isCN(param)){
//			//中文
//			param = param.trim();
//			query.set("suggest.dictionary", "fundName");
//			//query.set("q",param);
//		}
//		else if(StringCustomerUtils.isAlpha(param)){
//			param = param.trim();
//			query.set("suggest.dictionary", "fundPinYin");
//			//query.set("q",param);
//			//字母
//		}
//		else if(StringCustomerUtils.isNumber(param)){
//			param = param.trim();
//			query.set("suggest.dictionary", "fundCode");
//			//query.set("q",param);
//			//数字			
//		}
//		else{
//			//空
//			return FastJsonUtils.list2Json(wordkey);
//		}
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		query.set("qt", "/spell");
		//query.set("suggest", true);
		//query.set("suggest.build", true);
		query.set("wt", "json");
		//query.set("suggest.count", "5");
		query.set("q",param);
		QueryResponse response = null;
		response = client.query(query);
		if (response != null) {
			SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
			//空指针的可能:加判断
			List<Collation> suggests = spellCheckResponse.getCollatedResults();
			System.out.println(suggests.size());
//			for(Map.Entry<String, List<Suggestion>> entry : suggests.entrySet()){
//				List<Suggestion> contents = entry.getValue();
//				for(Suggestion suggestion : contents){
//					wordkey.add(suggestion.getTerm());
//				}
//			}
		}
		return FastJsonUtils.list2Json(wordkey);
	}
//	@Override
//	public void add(List<Map<String, String>> list) throws SolrServerException,
//			IOException {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void add(Map<String, String> params) throws SolrServerException,
//			IOException {
//		// TODO Auto-generated method stub
//		
//	}	
}
