package com.mrbt.mvc.service.client.netvalue;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.mvc.vo.FundLatestNetValueAndYearGainVo;
import com.mrbt.mvc.vo.SolrResponseVo;
import com.mrbt.utils.FastJsonUtils;

@Component(value = "fundNetValueHandlerClient")
public class FundNetValueHandlerClientImpl extends SolrHandlerClient implements
		FundNetValueHandlerClient {
	// private static final Logger log =
	// LogManager.getLogger(FundNetValueHandlerClientImpl.class);
	/***
	 * 返回年收益和净值的数据
	 */
	@Override
	public String selectNetValueAndYearGain(String fund_code)
			throws SolrServerException, IOException {
		SolrQuery select = new SolrQuery("market_fund_code:" + fund_code);
		select.setStart(0);
		select.setRows(1);
		select.set("ident", "true");
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		QueryResponse response = client.query(select);
		SolrResponseVo<FundLatestNetValueAndYearGainVo> solrResponseVo = new SolrResponseVo<FundLatestNetValueAndYearGainVo>();
		if (response != null) {
			solrResponseVo.setStatus(response.getStatus());
			SolrDocumentList solrDocumentLists = response.getResults();
			if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
				DocumentObjectBinder binder = new DocumentObjectBinder();
				List<FundLatestNetValueAndYearGainVo> resultList = binder
						.getBeans(FundLatestNetValueAndYearGainVo.class,
								solrDocumentLists);
				solrResponseVo.setIndexBean(resultList);
				solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
			}
			return FastJsonUtils.Obj2Json(solrResponseVo);
		}
		return "";
	}
}
