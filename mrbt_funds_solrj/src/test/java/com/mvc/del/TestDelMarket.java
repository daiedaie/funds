package com.mvc.del;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.service.service.market.FundMarketHandlerService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class TestDelMarket {
	public static final Logger log = LogManager
			.getLogger(TestDelMarket.class);
	@Autowired
	private FundMarketHandlerService fundMarketHandlerService;
	@Test
	public void TestAdd(){
		try {
			fundMarketHandlerService.deleteByCode("005299");
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
			log.info(e);
		}		
	}
}