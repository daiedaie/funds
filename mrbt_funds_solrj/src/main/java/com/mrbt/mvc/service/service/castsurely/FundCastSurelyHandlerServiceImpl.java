package com.mrbt.mvc.service.service.castsurely;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.service.client.castsurely.FundCastSurelyHandlerClient;

@Service
public class FundCastSurelyHandlerServiceImpl implements FundCastSurelyHandlerService{
	@Autowired
	@Qualifier("fundCastSurelyHandlerClient")
	private FundCastSurelyHandlerClient fundCastSurelyHandlerClient;
	@Override
	public String select(int start,int rows) throws SolrServerException, IOException {
		return fundCastSurelyHandlerClient.select(start,rows);
	}
	@Override
	public void finish() {
		fundCastSurelyHandlerClient.finish();
	}
	@Override
	public void rollback() {
		fundCastSurelyHandlerClient.rollback();
	}
//	@Override
//	public void add(String DT_TYPE, String DT_FUND_MANAGER, String DT_FUND_JC,
//			String DT_FUND_CODE, String DT_FUND_LEASTJZ, String DT_FUND_YEARZF)
//			throws SolrServerException, IOException {
//		fundCastSurelyHandlerClient.add(DT_TYPE, DT_FUND_MANAGER, DT_FUND_JC, DT_FUND_CODE, DT_FUND_LEASTJZ, DT_FUND_YEARZF);
//	}
//	@Override
//	public void update(String dt_fund_type, String dt_fund_manager,
//			String dt_fund_abbreviation, String dt_fund_code,
//			String dt_fund_latestnetvalue, String dt_fund_yeargain) throws SolrServerException, IOException {
//		fundCastSurelyHandlerClient.update(dt_fund_type, dt_fund_manager, dt_fund_abbreviation, dt_fund_code, dt_fund_latestnetvalue, dt_fund_yeargain);
//	}
//	@Override
//	public void deleteByName(String fund_name) throws SolrServerException, IOException {
//		fundCastSurelyHandlerClient.deleteByName(fund_name);
//	}
//	@Override
//	public void deleteByCode(String fund_name) throws SolrServerException, IOException {
//		fundCastSurelyHandlerClient.deleteByCode(fund_name);
//	}
	@Override
	public String selectAll() throws IOException, SolrServerException {
		return fundCastSurelyHandlerClient.selectAll();
	}
//	@Override
//	public void add(List<Map<String, String>> list) throws SolrServerException,
//			IOException {
//		fundCastSurelyHandlerClient.add(list);
//		
//	}
//	@Override
//	public void add(Map<String, String> params) throws SolrServerException,
//			IOException {
//		fundCastSurelyHandlerClient.add(params);
//		
//	}
}
