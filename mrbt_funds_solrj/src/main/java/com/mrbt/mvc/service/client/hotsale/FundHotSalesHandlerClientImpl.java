package com.mrbt.mvc.service.client.hotsale;

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

import com.mrbt.config.ClientHandlerType;
import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.mvc.vo.FundHotSalesVo;
import com.mrbt.mvc.vo.SolrResponseVo;
import com.mrbt.utils.FastJsonUtils;

@Component(value = "fundHotSalesHandlerClient")
public class FundHotSalesHandlerClientImpl extends SolrHandlerClient implements
		FundHotSalesHandlerClient {
	
	//排序条件：THS054.F004-THS054.F005的计算结果降序
	private static final Logger log = LogManager
			.getLogger(FundHotSalesHandlerClient.class);

	public void delete(String param, ClientHandlerType.DeteleType type)
			throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		// 根据名称删除
		if (type.getType().equalsIgnoreCase("name")) {
			client.deleteByQuery("market_fund_name:" + param);
		}
		// 根据代码简称删除
		else if (type.getType().equalsIgnoreCase("code")) {
			client.deleteByQuery("market_fund_code:" + param);
		}
		client.commit();
		client.optimize();
	}

	@Override
	public void add(FundHotSalesVo fundHotSalesVo) throws SolrServerException,
			IOException {
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		if (fundHotSalesVo == null)
			return;
		client.deleteByQuery("market_fund_code:"
				+ fundHotSalesVo.getHotsale_fund_code());
		client.addBean(fundHotSalesVo);
		client.commit();
	}

	@Override
	public void addBeans(List<FundHotSalesVo> fundHotSalesVos)
			throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		if (fundHotSalesVos == null || fundHotSalesVos.size() == 0)
			return;
		for (FundHotSalesVo fundHotSalesVo : fundHotSalesVos) {
			client.deleteByQuery("market_fund_code:"
					+ fundHotSalesVo.getHotsale_fund_code());
			client.commit();
		}
		client.addBeans(fundHotSalesVos);
		client.commit();
	}

	@Override
	public String select(int start, int rows){
		try{
			SolrQuery select = new SolrQuery("market_fund_code:*");
			select.setStart(start);
			select.setRows(rows);
			//热销降序
			select.addSort("market_fund_hotsale", ORDER.desc);
			select.set("ident", "true");
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			client.close();
			SolrResponseVo<FundHotSalesVo> solrResponseVo = new SolrResponseVo<FundHotSalesVo>();
			if (response != null) {
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundHotSalesVo> resultList = binder.getBeans(
							FundHotSalesVo.class, solrDocumentLists);
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("热销基金列表查询失败"+e);
			return "";
		}
	}

	@Override
	public void deleteByName(String fund_name) throws SolrServerException,
			IOException {
		delete(fund_name, ClientHandlerType.DeteleType.NAME);
	}

	@Override
	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException {
		delete(fund_code, ClientHandlerType.DeteleType.CODE);

	}

	@Override
	public String selectAll() {
		try{
			SolrQuery select = new SolrQuery("market_fund_code:*");
			select.setStart(0);
			select.setRows(1);
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			client.close();
			if (response != null && response.getResults() != null) {
				long queryCount = response.getResults().getNumFound();
				int numFound = new Long(queryCount).intValue();
				if (numFound > 0) {
					select.setRows(numFound);
					response = null;
					response = client.query(select);
					SolrResponseVo<FundHotSalesVo> solrResponseVo = new SolrResponseVo<FundHotSalesVo>();
					if (response != null) {
						log.info(response.getQTime() + ":");
						log.info(response.getQTime() + response.getStatus() + ":"
								+ response.getResults().getNumFound());
						solrResponseVo.setStatus(response.getStatus());
						SolrDocumentList solrDocumentLists = response.getResults();
						if (solrDocumentLists != null
								&& solrDocumentLists.size() > 0) {
							DocumentObjectBinder binder = new DocumentObjectBinder();
							List<FundHotSalesVo> resultList = binder.getBeans(
									FundHotSalesVo.class, solrDocumentLists);
							solrResponseVo.setIndexBean(resultList);
							solrResponseVo.setNumFound(solrDocumentLists
									.getNumFound());
						}
						return FastJsonUtils.Obj2Json(solrResponseVo);
					}
				}
				return "";
			}
			return "";
		}catch(Exception e){
			log.info("查询所有热销基金出现异常"+e);
			return "";
		}
	}
}
