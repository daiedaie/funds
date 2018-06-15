package com.mrbt.mvc.service.client.detail;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.mvc.vo.FundDetailVo;
import com.mrbt.mvc.vo.SolrResponseVo;
import com.mrbt.utils.FastJsonUtils;
import com.mrbt.utils.StringCustomerUtils;

@Component(value = "fundDetailHandlerClient")
public class FundDetailHandlerClientImpl extends SolrHandlerClient implements FundDetailHandlerClient {
	private Logger log = LogManager.getLogger(FundDetailHandlerClientImpl.class);
	public void detail(){
		
	}

	//根据基金代码查询详情页面信息
	@Override
	public String getFundDetailBycode(String id) {
		try{
			if(StringCustomerUtils.isEmpty(id)){
				return null;
			}
			SolrQuery select = new SolrQuery("market_fund_code:"+id);
			select.setStart(0);
			select.setRows(1);
			select.set("ident","true");
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			SolrResponseVo<FundDetailVo> solrResponseVo = new SolrResponseVo<FundDetailVo>();
			client.close();
			if (response != null) {
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundDetailVo> resultList = binder.getBeans(FundDetailVo.class, solrDocumentLists);
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("查询基金详情出现异常"+e);
			return "";
		}
	}

	@Override
	public void addBeans(List<FundDetailVo> fundDeatilVos) {
		try{
			if(CollectionUtils.isEmpty(fundDeatilVos)){
				return;
			}
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			for (FundDetailVo fundDetailVo : fundDeatilVos) {
				String code = fundDetailVo.getCode();
				if(StringCustomerUtils.isEmpty(code)){
					continue;
				}
				client.deleteByQuery("market_fund_code:"+code);
				client.commit();
			}
			client.addBeans(fundDeatilVos);
			client.commit();
		}catch(Exception e){
			try {
				client.rollback();
			} catch (SolrServerException | IOException e1) {
				log.info("添加基金详情索引出现异常，回滚失败"+e1);
			}
			finally{
				try {
					client.close();
				} catch (IOException e1) {
					log.info("添加基金详情索引出现异常，客户端连接关闭失败"+e1);
				}
			}
		}
	}

	@Override
	public void addBean(FundDetailVo fundDeatilVos) throws SolrServerException,
			IOException {
		if(fundDeatilVos == null){
			return;
		}
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		
		String code = fundDeatilVos.getCode();
		if(!StringCustomerUtils.isEmpty(code)){
			client.deleteByQuery("market_fund_code:"+code);
			client.commit();
		}
		client.addBean(fundDeatilVos);
		client.commit();
	}

	@Override
	public String getFundDetailList(int start,int rows) throws SolrServerException, IOException {
		try{
			SolrQuery select = new SolrQuery("market_fund_code:"+AppInfo.ALL_TYPE);
			select.setStart(start);
			select.setRows(rows);
			select.set("ident","true");
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			SolrResponseVo<FundDetailVo> solrResponseVo = new SolrResponseVo<FundDetailVo>();
			client.close();
			if (response != null) {
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundDetailVo> resultList = binder.getBeans(
							FundDetailVo.class, solrDocumentLists);
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("查询所有基金详情出现异常"+e);
			return "";
		}
	}
}
