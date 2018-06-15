package com.mrbt.mvc.service.service.detail;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.service.client.detail.FundDetailHandlerClient;
import com.mrbt.mvc.vo.FundDetailVo;

@Service
public class FundDetailHandlerServiceImpl implements FundDetailHandlerService{
	@Autowired
	private FundDetailHandlerClient fundDetailHandlerClient;
	public void detail(){
		
	}
	@Override
	public String getFundDetailByCode(String id) throws IOException, SolrServerException {
		return fundDetailHandlerClient.getFundDetailBycode(id);
	}
	@Override
	public void rollback() {
		fundDetailHandlerClient.rollback();
		
	}
	@Override
	public void finish() {
		fundDetailHandlerClient.finish();		
	}
	@Override
	public void addBeans(List<FundDetailVo> fundDeatilVos) throws SolrServerException, IOException {
		fundDetailHandlerClient.addBeans(fundDeatilVos);
	}
	@Override
	public void addBean(FundDetailVo fundDeatilVos) throws SolrServerException,
			IOException {
		fundDetailHandlerClient.addBean(fundDeatilVos);
	}
	@Override
	public String getFundDetailList(int start, int rows)
			throws SolrServerException, IOException {
		return fundDetailHandlerClient.getFundDetailList(start,rows);
	}
}
