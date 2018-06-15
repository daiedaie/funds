package com.mrbt.mvc.service.client.castsurely;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import com.mrbt.config.AppInfo;
import com.mrbt.config.ClientHandlerType;
import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.mvc.service.client.market.FundMarketHandlerClient;
import com.mrbt.mvc.vo.FundCastSurelyVo;
import com.mrbt.mvc.vo.SolrResponseVo;
import com.mrbt.utils.FastJsonUtils;

@Component(value = "fundCastSurelyHandlerClient")
public class FundCastSurelyHandlerClientImpl extends SolrHandlerClient implements
		FundCastSurelyHandlerClient {
	private static final Logger log = LogManager.getLogger(FundMarketHandlerClient.class);
	
	//定投查询
	@Override
	public String select(int start,int rows) {
		try{
			SolrQuery select = new SolrQuery("market_fund_castsurely:"+AppInfo.FUND_ISCASTSURELY);
			select.setStart(start);
			select.setRows(rows);
			//按照代码升序
			select.addSort("market_fund_code", ORDER.asc);
			select.set("ident","true");
			if (client == null)
				getSolrClientInstance();
			else{
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			SolrResponseVo<FundCastSurelyVo> solrResponseVo = new SolrResponseVo<FundCastSurelyVo>();
			if(response != null){
				client.close();
				log.info(response.getQTime()+":");
				log.info(response.getQTime()+response.getStatus()+":"+response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundCastSurelyVo> resultList = binder.getBeans(FundCastSurelyVo.class, solrDocumentLists);
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("基金定投列表查询出现异常"+e);
			return "";
		}
	}
	
	
	public void delete(String param,ClientHandlerType.DeteleType type){
		try{
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
		}catch(Exception e){
			log.info("定投删除索引出现异常");
			try {
				client.rollback();
			} catch (SolrServerException | IOException e1) {
				log.info("定投删除索引出现异常-数据回滚出现异常");
			}
		}finally{
			try {
				client.close();
			} catch (IOException e) {
				log.info("定投删除索引出现异常-solr client关闭出现异常");
			}
		}
		
	}

	@Override
	public String selectAll() throws IOException, SolrServerException {
		try{
			SolrQuery select = new SolrQuery("market_fund_code:"+AppInfo.ALL_TYPE);
			select.setStart(0);
			select.setRows(Integer.MAX_VALUE);
			select.set("ident","true");
			if (client == null)
				getSolrClientInstance();
			else{
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			client.close();
			if(response != null && response.getResults() != null){
				long queryCount = response.getResults().getNumFound();
				int numFound = new Long(queryCount).intValue();
				if(numFound > 0){
					select.setRows(numFound);
					SolrResponseVo<FundCastSurelyVo> solrResponseVo = new SolrResponseVo<FundCastSurelyVo>();
					response = null;
					response = client.query(select);
					if(response != null){
						client.close();
						log.info(response.getQTime()+":");
						log.info(response.getQTime()+response.getStatus()+":"+response.getResults().getNumFound());
						solrResponseVo.setStatus(response.getStatus());
						SolrDocumentList solrDocumentLists = response.getResults();
						if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
							DocumentObjectBinder binder = new DocumentObjectBinder();
							List<FundCastSurelyVo> resultList = binder.getBeans(FundCastSurelyVo.class, solrDocumentLists);
							solrResponseVo.setIndexBean(resultList);
							solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
						}
						return FastJsonUtils.Obj2Json(solrResponseVo);
					}
				}
				return "";
			}
			return "";
		}catch(Exception e){
			log.info("查询所有定投索引出现异常"+e);
			return "";
		}
	}
}
