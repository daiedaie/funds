package com.mrbt.mvc.service.client.markettable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.vo.FundMarketTableVo;

public interface FundMarketTableHandlerClient {
	public void delete(String param) throws SolrServerException, IOException;

	public String select(String param) throws SolrServerException, IOException;

	public String update(Map<String, String> param, String foundCode)
			throws SolrServerException, IOException;

	public void finish();

	public void rollback();

	public void fullimport() throws SolrServerException, IOException;

	void addBeans(List<FundMarketTableVo> sundMarketTableVos)
			throws SolrServerException, IOException;

	Boolean add(FundMarketTableVo fundMarketTableVo) throws SolrServerException,
			IOException;

	public String update(String fUNDS_CODE, String cREATE_TIME,
			String iS_RECOMM, String rECOMM_ORDER, String fUNDS_CODE_INNER,
			String rECOMM_REASON, String fUNDS_THEME)
			throws SolrServerException, IOException;

}
