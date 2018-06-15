package com.mrbt.mvc.service.service.market;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.service.client.market.FundMarketHandlerClient;
import com.mrbt.mvc.vo.FundMarketListVo;
import com.mrbt.mvc.vo.FundMarketVo;

@Service
public class FundMarketHandlerServiceImpl implements FundMarketHandlerService {
	@Autowired
	@Qualifier("fundMarketHandlerClient")
	private FundMarketHandlerClient fundMarketHandlerClient;

	@Override
	public String select() throws SolrServerException, IOException {
		return fundMarketHandlerClient.select();
	}

	@Override
	public void finish() {
		fundMarketHandlerClient.finish();
	}

	@Override
	public void rollback() {
		fundMarketHandlerClient.rollback();
	}

	@Override
	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException {
		fundMarketHandlerClient.deleteByCode(fund_code);

	}

	@Override
	public void deleteByName(String fund_name) throws SolrServerException,
			IOException {
		fundMarketHandlerClient.deleteByName(fund_name);

	}

	@Override
	public String selectAll() throws IOException, SolrServerException {
		return fundMarketHandlerClient.selectAll();
	}


	//基金超市列表
	@Override
	public String selectMarketList(int start, int rows, String castsurely,
			String type, String scale, String company, String theme,
			String order) throws SolrServerException, IOException {
		return fundMarketHandlerClient.selectMarketList(start, rows,
				castsurely, type, scale, company, theme, order);
	}

	@Override
	public String selectMarketSearchList(int start, int rows, String namecode,
			String order) throws IOException, SolrServerException {
		return fundMarketHandlerClient.selectMarketSearchList(start, rows,
				namecode, order);
	}

	@Override
	public void add(FundMarketVo fundMarketVo) throws SolrServerException,
			IOException {
		fundMarketHandlerClient.add(fundMarketVo);
		
	}

	@Override
	public void addBeans(List<FundMarketVo> fundMarketVos)
			throws SolrServerException, IOException {
		fundMarketHandlerClient.addBeans(fundMarketVos);		
	}

	@Override
	public String retrieve(String param,int start, int rows,String order) throws SolrServerException,
			IOException {
		return fundMarketHandlerClient.retrieve(param,start,rows,order);
	}

	@Override
	public String getMarketListByCode(String id) throws SolrServerException,
			IOException {
		return fundMarketHandlerClient.getMarketListByCode(id);
	}

	@Override
	public List<FundMarketListVo> selectMarketReturnList(int start, int rows,
			String castsurely, String type, String scale, String company,
			String theme, String order) throws SolrServerException, IOException {
		return fundMarketHandlerClient.selectMarketReturnList(start, rows, castsurely, type, scale, company, theme, order);
	}
	@Override
	public String selectMarketAllList() throws SolrServerException, IOException {
		return fundMarketHandlerClient.selectAll();
	}

	@Override
	public void addBean(FundMarketVo fundMarketVo)
			throws SolrServerException, IOException {
		fundMarketHandlerClient.add(fundMarketVo);;
	}
}
