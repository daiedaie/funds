package com.mrbt.mvc.service.client.markettable;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest.ACTION;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.mvc.vo.FundMarketTableVo;
import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.mvc.vo.SolrResponseVo;
import com.mrbt.utils.FastJsonUtils;

@Component(value = "fundMarketTableHandlerClient")
public class FundMarketTableHandlerClientImpl extends SolrHandlerClient
		implements FundMarketTableHandlerClient {
	private static final Logger log = LogManager
			.getLogger(FundMarketTableHandlerClient.class);

	// 基金代码
	public void delete(String fUNDS_CODE) throws SolrServerException,
			IOException {
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		client.deleteByQuery("market_fund_code:" + fUNDS_CODE);
		client.commit();

	}

	@Override
	public Boolean add(FundMarketTableVo fundMarketTableVo){
		try{
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			if (fundMarketTableVo == null)
				return false;
			client.deleteByQuery("market_fund_code:"
					+ fundMarketTableVo.getFunds_code());
			client.addBean(fundMarketTableVo);
			client.commit();
			return true;
		}catch(Exception e){
			log.info("基金超市索添加失败"+e);
			try {
				client.rollback();
			} catch (SolrServerException | IOException e1) {
				return false;
			}
		}finally{
			try {
				client.close();
			} catch (IOException e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public void addBeans(List<FundMarketTableVo> sundMarketTableVos)
			throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		if (sundMarketTableVos == null || sundMarketTableVos.size() == 0)
			return;
		for (FundMarketTableVo fundMarketTableVo : sundMarketTableVos) {
			client.deleteByQuery("market_fund_code:"
					+ fundMarketTableVo.getFunds_code());
			client.commit();
		}
		client.addBeans(sundMarketTableVos);
		client.commit();
	}

	public String select(String param){
		try{
			SolrQuery select = new SolrQuery("market_fund_code:" + param);
			select.setStart(0);
			select.setRows(10);
			select.addSort("market_fund_code", ORDER.asc);
			select.set("ident", "true");
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			SolrResponseVo<FundMarketTableVo> solrResponseVo = new SolrResponseVo<FundMarketTableVo>();
			if (response != null) {
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundMarketTableVo> resultList = binder.getBeans(
							FundMarketTableVo.class, solrDocumentLists);
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("索引查询失败"+e);
			return "";
		}
		
	}

	@Override
	public String update(Map<String, String> param, String foundCode)
			throws SolrServerException, IOException {
		if (param == null || param.size() == 0)
			return "";
		SolrQuery query = new SolrQuery("market_fund_code:" + foundCode);
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		QueryResponse response = client.query(query);
		if (response != null) {
			SolrDocumentList solrDocumentLists = response.getResults();
			if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
				Iterator<SolrDocument> docs = solrDocumentLists.iterator();
				while (docs.hasNext()) {
					SolrDocument sd = docs.next();
					if (sd != null) {
						SolrInputDocument solrDoc = new SolrInputDocument();
						for (Map.Entry<String, String> entry : param.entrySet()) {
							if (entry == null)
								continue;
							solrDoc.addField(
									entry.getKey(),
									entry.getValue() == null ? "" : entry
											.getValue(), 1.0f);
						}
						UpdateRequest updateRequest = new UpdateRequest();
						updateRequest.add(solrDoc);
						Object obj = updateRequest.process(client);
						System.out.println(obj);
					}
				}
			}
		}
		return null;
	}

	@Override
	public void fullimport() throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.setRequestHandler("/dataimport");
		query.setParam("command", AppInfo.ImportType.FULL.getType())
				.setParam("clean", false).setParam("commit", true)
				.setParam("optimize", true);
		// query.setParam("entity",null);
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		client.query(query);
		client.commit();
	}

	// 设置推荐参数
	@Override
	public String update(String fUNDS_CODE, String cREATE_TIME,
			String iS_RECOMM, String rECOMM_ORDER, String fUNDS_CODE_INNER,
			String rECOMM_REASON, String fUNDS_THEME)
			throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery("market_fund_code:" + fUNDS_CODE);
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		QueryResponse response = client.query(query);
		if (response != null) {
			SolrDocumentList solrDocumentLists = response.getResults();
			if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
				Iterator<SolrDocument> docs = solrDocumentLists.iterator();
				while (docs.hasNext()) {
					SolrDocument sd = docs.next();
					if (sd != null) {
						if (!sd.get("market_fund_code").equals(fUNDS_CODE)) {
							log.info("market_fund_code " + fUNDS_CODE
									+ " no exists");
							return AppInfo.FOUND_MARKET_UPDATE_MESSAGE_F;
						} else {
							DocumentObjectBinder binder = new DocumentObjectBinder();
							List<FundMarketVo> resultList = binder.getBeans(
									FundMarketVo.class, solrDocumentLists);
							if (CollectionUtils.isEmpty(resultList)) {
								break;
							}
							FundMarketVo updateFundMarketVo = resultList.get(0);
							if (updateFundMarketVo == null) {
								break;
							}
							client.deleteByQuery("market_fund_code:"
									+ updateFundMarketVo.getMarket_fund_code());
							client.commit();
							updateFundMarketVo
									.setMarket_fund_isrecommcreatetime(cREATE_TIME);
							updateFundMarketVo
									.setMarket_fund_isrecomm(iS_RECOMM);
							updateFundMarketVo
									.setMarket_fund_recommorder(Integer.parseInt(rECOMM_ORDER));
							updateFundMarketVo
									.setMarket_fund_codeinner(fUNDS_CODE_INNER);
							updateFundMarketVo
									.setMarket_fund_recommendreason(rECOMM_REASON);
							updateFundMarketVo
									.setMarket_fund_theme(fUNDS_THEME);
							SolrInputDocument solrDoc = binder
									.toSolrInputDocument(updateFundMarketVo);
							UpdateRequest updateRequest = new UpdateRequest();
							updateRequest.setAction(ACTION.COMMIT, true, true);
							updateRequest.add(solrDoc);
							Object obj = updateRequest.process(client);
							client.commit();
							client.optimize();
							log.info(obj.toString());
							return AppInfo.FOUND_MARKET_UPDATE_MESSAGE_T;
						}
					}
				}
			}
		}
		return AppInfo.FOUND_MARKET_UPDATE_MESSAGE_F;
	}
}
