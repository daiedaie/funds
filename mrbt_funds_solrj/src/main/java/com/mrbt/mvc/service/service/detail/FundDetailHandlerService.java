package com.mrbt.mvc.service.service.detail;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.mrbt.mvc.vo.FundDetailVo;

public interface FundDetailHandlerService {

	String getFundDetailByCode(String id) throws IOException, SolrServerException;

	void rollback();

	void finish();

	void addBeans(List<FundDetailVo> fundDeatilVos) throws SolrServerException, IOException;

	void addBean(FundDetailVo fundDeatilVos) throws SolrServerException, IOException;

	String getFundDetailList(int start, int rows) throws SolrServerException, IOException;

}
