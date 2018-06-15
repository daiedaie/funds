package com.mrbt.mvc.service.service.markettable;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.service.baseservice.BaseService;

public interface FundMarketTableHandlerService extends BaseService {
	public String delete(String foundcode) throws SolrServerException,
			IOException;

	public String select(String foundcode) throws SolrServerException,
			IOException;

	String update(Map<String, String> foundmarket, String foundcode)
			throws SolrServerException, IOException;

	public void finish();

	public void rollback();

	public void fullimport() throws SolrServerException, IOException;

	public String update(String fUNDS_CODE, String cREATE_TIME,
			String iS_RECOMM, String rECOMM_ORDER, String fUNDS_CODE_INNER,
			String rECOMM_REASON, String fUNDS_THEME)
			throws SolrServerException, IOException;

	public boolean add(String fUNDS_CODE, String cREATE_TIME, String iS_RECOMM,
			String rECOMM_ORDER, String fUNDS_CODE_INNER, String rECOMM_REASON,
			String fUNDS_THEME);

	public boolean deleteAllIndex() throws SolrServerException, IOException;
}
