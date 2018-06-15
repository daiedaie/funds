package com.mvc.index;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.service.service.markettable.FundMarketTableHandlerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class MarketTableIndexTest {
	public static final Logger log = LogManager.getLogger(MarketTableIndexTest.class);
	@Autowired
	private FundMarketTableHandlerService fundMarketTableHandlerService;
	
	
	//@Test
	public void testDeleteRecommend(){
		try {
			fundMarketTableHandlerService.delete("000000");
		} catch (SolrServerException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
	}
	//@Test
	public void testAddRecommend(){
		String fundcode = "000000";
		String theme = "测试";
		String order = "999";
		String inner = "KF0102";
		String isrecomm = "1";
		String reason = "no point";
		String createTime = "2016-07-08 15:30:00";
		try {
			fundMarketTableHandlerService.update(fundcode, createTime,isrecomm, 
					order, inner, reason, theme);
		} catch (SolrServerException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
	}
}