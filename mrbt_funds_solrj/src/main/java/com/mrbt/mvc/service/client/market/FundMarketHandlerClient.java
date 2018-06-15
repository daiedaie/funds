package com.mrbt.mvc.service.client.market;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.vo.FundMarketListVo;
import com.mrbt.mvc.vo.FundMarketVo;

public interface FundMarketHandlerClient {
	public void finish();

	public void rollback();

	public String select() throws SolrServerException, IOException;

	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException;

	public void deleteByName(String fund_name) throws SolrServerException,
			IOException;

	public String selectAll() throws IOException, SolrServerException;

	void add(FundMarketVo fundMarketVo) throws SolrServerException, IOException;

	<K, V> boolean update(String fundcode, Map<K, V> param);

	void addBeans(List<FundMarketVo> fundMarketVos) throws SolrServerException,
			IOException;

	public String selectMarketList(int start, int rows, String castsurely,
			String type, String scale, String company, String theme,
			String order) ;
	

	public String selectMarketSearchList(int start, int rows, String namecode,
			String order) throws IOException, SolrServerException;

	
	public String retrieve(String param, int start, int rows, String order) throws IOException,
	SolrServerException;

	public String getMarketListByCode(String id) throws IOException,
	SolrServerException;

	List<FundMarketListVo> selectMarketReturnList(int start, int rows,
			String castsurely, String type, String scale, String company,
			String theme, String order) throws SolrServerException, IOException;

	public boolean deleteAllIndex() throws SolrServerException, IOException;


}
