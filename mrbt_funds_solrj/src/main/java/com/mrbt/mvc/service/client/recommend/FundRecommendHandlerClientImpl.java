package com.mrbt.mvc.service.client.recommend;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import com.mrbt.config.AppInfo;
import com.mrbt.config.ClientHandlerType;
import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.mvc.vo.FundRecommendVo;
import com.mrbt.mvc.vo.SolrResponseVo;
import com.mrbt.utils.FastJsonUtils;

@Component(value = "fundRecommendHandlerClient")
public class FundRecommendHandlerClientImpl extends SolrHandlerClient implements
		FundRecommendHandlerClient {
	private static final Logger log = LogManager
			.getLogger(FundRecommendHandlerClientImpl.class);
	public void delete(String param,ClientHandlerType.DeteleType type) throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else{
			client.close();
			getSolrClientInstance();
		}
		//根据名称删除
		if(type.getType().equalsIgnoreCase("name")){
			client.deleteByQuery("market_fund_name:"+param);
		}
		//根据代码简称删除
		else if(type.getType().equalsIgnoreCase("code")){
			client.deleteByQuery("market_fund_code:"+param);
		}
		client.commit();
		client.optimize();
	}
	@Override
	public void add(FundRecommendVo fundRecommendVo) throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else{
			client.close(); 
			getSolrClientInstance();
		}
		if( fundRecommendVo == null) return;
		client.deleteByQuery("market_fund_code:" +fundRecommendVo.getRecommend_fund_code());
		client.addBean( fundRecommendVo);
		client.commit();		
	}
	@Override
	public void addBeans(List<FundRecommendVo>  fundRecommendVos) throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else{
			client.close(); 
			getSolrClientInstance();
		}
		if(fundRecommendVos == null ||  fundRecommendVos.size() == 0) return;
		for(FundRecommendVo fundRecommendVo : fundRecommendVos){
			client.deleteByQuery("market_fund_code:" +fundRecommendVo.getRecommend_fund_code());
			client.commit();
		}
		client.addBeans(fundRecommendVos);
		client.commit();				
	}

	public String select(int start, int rows) throws SolrServerException,
			IOException {
		log.info("foundsrecoms select");
		SolrQuery select = new SolrQuery("market_fund_isrecomm:"+AppInfo.FUND_ISRECOMMEND);
		select.setStart(start);
		select.setRows(rows);
		select.addSort("market_fund_recommorder", ORDER.asc);
		select.set("ident","true");
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		QueryResponse response = client.query(select);
		SolrResponseVo<FundRecommendVo> solrResponseBean = new SolrResponseVo<FundRecommendVo>();
		if (response != null) {
			System.out.println(response.getQTime() + ":");
			System.out.println(response.getQTime() + response.getStatus() + ":"
					+ response.getResults().getNumFound());
			solrResponseBean.setStatus(response.getStatus());
			SolrDocumentList solrDocumentLists = response.getResults();
			if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
				DocumentObjectBinder binder = new DocumentObjectBinder();
				List<FundRecommendVo> resultList = binder.getBeans(
						FundRecommendVo.class, solrDocumentLists);
				solrResponseBean.setIndexBean(resultList);
				solrResponseBean.setNumFound(solrDocumentLists.getNumFound());
			}
			return FastJsonUtils.Obj2Json(solrResponseBean);
		}
		return "";
	}


	@Override
	public void deleteByName(String fund_name) throws SolrServerException, IOException {
		delete(fund_name,ClientHandlerType.DeteleType.NAME);
	}

	@Override
	public void deleteByCode(String fund_code) throws SolrServerException, IOException {
		delete(fund_code,ClientHandlerType.DeteleType.CODE);
	}

	@Override
	public String selectAll() throws IOException, SolrServerException {
		//代表推荐
		SolrQuery select = new SolrQuery("market_fund_isrecomm:"+AppInfo.FUND_ISRECOMMEND);
		select.setStart(0);
		select.setRows(Integer.MAX_VALUE);
		select.addSort("market_fund_recommorder", ORDER.asc);
		
		if (client == null)
			getSolrClientInstance();
		else{
			client.close();
			getSolrClientInstance();
		}
		select.set("ident","true");
		QueryResponse response = client.query(select);
		if(response != null && response.getResults() != null){
			long queryCount = response.getResults().getNumFound();
			int numFound = new Long(queryCount).intValue();
			if(numFound > 0){
				select.setRows(numFound);
				SolrResponseVo<FundRecommendVo> solrResponseVo = new SolrResponseVo<FundRecommendVo>();
				response = null;
				response = client.query(select);
				if(response != null){
					log.info(response.getQTime()+":");
					log.info(response.getQTime()+response.getStatus()+":"+response.getResults().getNumFound());
					solrResponseVo.setStatus(response.getStatus());
					SolrDocumentList solrDocumentLists = response.getResults();
					if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
						DocumentObjectBinder binder = new DocumentObjectBinder();
						List<FundRecommendVo> resultList = binder.getBeans(FundRecommendVo.class, solrDocumentLists);
						solrResponseVo.setIndexBean(resultList);
						solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
					}
					return FastJsonUtils.Obj2Json(solrResponseVo);
				}
			}
			return "";
		}
		return "";
	}
	@Override
	public void add(Map<String,String> params) throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else{
			client.close();
			getSolrClientInstance();
		}
		SolrInputDocument doc = new SolrInputDocument();
		for(Map.Entry<String,String> entry : params.entrySet()){
			if(entry == null) continue;
			client.deleteByQuery("market_fund_code:" + params.get("market_fund_code"));
			client.commit();
			doc.addField(entry.getKey(),entry.getValue(),1.0f);
		}
		client.add(doc);
		client.commit();
	}
	@Override
	public void add(List<Map<String,String>> list) throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else{
			client.close();
			getSolrClientInstance();
		}
		if(list == null || list.size() == 0) return;
		for(Map<String,String> params : list){
			SolrInputDocument doc = new SolrInputDocument();
			for(Map.Entry<String,String> entry : params.entrySet()){
				if(entry == null) continue;
				client.deleteByQuery("market_fund_code:" + params.get("market_fund_code"));
				client.commit();
				doc.addField(entry.getKey(),entry.getValue(),1.0f);
			}
			client.add(doc);
			client.commit();
		}
	}
	@Override
	public String select(int start, int rows, String type) throws IOException, SolrServerException {
		try{
			type = getType(type);
			SolrQuery select = new SolrQuery();
			log.info("优选基金查询类型是:"+type);
			System.err.println("优选基金查询类型是:"+type);
			StringBuffer stringBuffer = new StringBuffer("market_fund_isrecomm:"+AppInfo.FUND_ISRECOMMEND);
			if (isNotAsterisk(type)) {
				if(type.equals("-1")){
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.STOCK_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.HYBIRD_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.BOND_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.INDEX_TYPE+"\")");
				}
				else{
					stringBuffer.append(" AND market_fund_type:" + "\"" + type + "\"");
				}
			}
			select.setQuery(stringBuffer.toString());
			System.err.println(stringBuffer.toString());
			select.setStart(start);
			select.setRows(rows);
			select.addSort("market_fund_recommorder", ORDER.asc);
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			SolrResponseVo<FundRecommendVo> solrResponseBean = new SolrResponseVo<FundRecommendVo>();
			if (response != null) {
				client.close();
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"+ response.getResults().getNumFound());
				solrResponseBean.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					//System.out.println(solrDocumentLists.size());
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundRecommendVo> resultList = binder.getBeans(FundRecommendVo.class, solrDocumentLists);
					solrResponseBean.setIndexBean(resultList);
					solrResponseBean.setNumFound(solrDocumentLists.getNumFound());
				}
				//System.err.println("打印优选基金");
				//System.err.println(FastJsonUtils.Obj2Json(solrResponseBean));
				return FastJsonUtils.Obj2Json(solrResponseBean);
			}
			return "";
		}catch(Exception e){
			log.info("优选基金查询出现异常"+e);
			return "";
		}
		
	}
	private String getType(String type) {
		if (type.equals(AppInfo.STOCK_TYPE_NUM)) {
			type = AppInfo.STOCK_TYPE;
		} else if (type.equals(AppInfo.BOND_TYPE_NUM)) {
			type = AppInfo.BOND_TYPE;
		} else if (type.equals(AppInfo.HYBIRD_TYPE_NUM)) {
			type = AppInfo.HYBIRD_TYPE;
		} else if (type.equals(AppInfo.CURRENCY_TYPE_NUM)) {
			type = AppInfo.CURRENCY_TYPE;
		} else if (type.equals(AppInfo.INDEX_TYPE_NUM)) {
			type = AppInfo.INDEX_TYPE;
		} else if (type.equals(AppInfo.QDII_TYPE_NUM)) {
			type = AppInfo.QDII_TYPE;
		} else if (type.equals(AppInfo.FUND_TYPE_NUM)) {
			type = AppInfo.FUND_TYPE;
		} else if (type.equals(AppInfo.CAPITALPRERVATION_TYPE_NUM)) {
			type = AppInfo.CAPITALPRERVATION_TYPE;
		} else if (type.equals(AppInfo.INNOVATION_TYPE_NUM)) {
			type = AppInfo.INNOVATION_TYPE;
		} else if(type.equals(AppInfo.OTHERS_TYPE)){
			type = AppInfo.OTHERS_TYPE;
		}
		else {
			type = AppInfo.ALL_TYPE;
		}
		return type;
	}
	private boolean isNotAsterisk(String asterisk) {
		if (asterisk == null || asterisk.equals(AppInfo.ALL_TYPE) || asterisk.length() == 0
				|| asterisk.equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}
}
