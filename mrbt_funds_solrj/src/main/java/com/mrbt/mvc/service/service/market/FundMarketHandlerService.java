package com.mrbt.mvc.service.service.market;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.service.baseservice.BaseService;
import com.mrbt.mvc.vo.FundMarketListVo;
import com.mrbt.mvc.vo.FundMarketVo;

public interface FundMarketHandlerService extends BaseService {
	public String select() throws SolrServerException, IOException;

	public void finish();

	public void rollback();

	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException;

	public void deleteByName(String fund_name) throws SolrServerException,
			IOException;

	public String selectAll() throws IOException, SolrServerException;

	public String selectMarketList(int start, int rows, String castsurely,
			String type, String scale, String company, String theme,
			String order) throws SolrServerException, IOException;

	public String selectMarketSearchList(int start, int rows, String namecode,
			String order) throws IOException, SolrServerException;

	public void add(FundMarketVo fundMarketVo) throws SolrServerException,
			IOException;

	public void addBeans(List<FundMarketVo> fundMarketVos)
			throws SolrServerException, IOException;
	
	public String retrieve(String param, int start, int rows, String order) throws SolrServerException, IOException;

	public String getMarketListByCode(String id) throws SolrServerException, IOException;

	public List<FundMarketListVo> selectMarketReturnList(int start, int rows,
			String castsurely, String type, String scale, String company,
			String theme, String order) throws SolrServerException, IOException;

	public String selectMarketAllList() throws SolrServerException, IOException;

	//基础索引添加数据的接口程序
	public void addBean(FundMarketVo fundMarketVo) throws SolrServerException, IOException;
}
