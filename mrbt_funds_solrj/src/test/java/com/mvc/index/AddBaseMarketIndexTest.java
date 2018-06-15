package com.mvc.index;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.service.client.market.FundMarketHandlerClient;
import com.mrbt.mvc.service.service.markettable.FundMarketTableHandlerService;
import com.mrbt.mvc.vo.FundMarketVo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class AddBaseMarketIndexTest {
	
	
	//通过后台添加基金的索引测试
	@Autowired
	private FundMarketTableHandlerService fundMarketTableHandlerService;
	//@Test
	public void test(){
		//boolean result = fundMarketTableHandlerService.add("000333","2016-07-12 09:38:33","0", "999", "KF3937","好卖","互联网");
		long start = System.currentTimeMillis();
		boolean result = fundMarketTableHandlerService.add("000055","2016-07-32 09:38:33","0", "999", "KF3937","2016-07-25","基金代码测试添加任务地");
		long end = System.currentTimeMillis();
		System.err.println(result+"("+(end-start)+")");
		//10秒左右 计算涨幅等等
	}
	
	@Resource
	FundMarketHandlerClient fundMarketHandlerClient;
	//手动添加测试数据
	@Test
	public void manal(){
		// 4
		FundMarketVo fundMarketVo = new FundMarketVo();
		
		fundMarketVo.setMarket_fund_type("创新型");
		fundMarketVo.setMarket_fund_shortname("光大保德信行业轮动混合");
		fundMarketVo.setMarket_fund_name("光大保德信行业轮动混合基金");
		fundMarketVo.setMarket_fund_code("888888");
		fundMarketVo.setMarket_fund_scale(100000000);
		fundMarketVo.setMarket_fund_buyrate(1.68);
		fundMarketVo.setMarket_fund_latestnetvalue(1.26);
		fundMarketVo.setMarket_fund_daygain(-1.22);
		fundMarketVo.setMarket_fund_weekgain(1.222);
		fundMarketVo.setMarket_fund_nearlymonthgain(3.782);
		
		fundMarketVo.setMarket_fund_nearlythreemonthgain(3.782);
		fundMarketVo.setMarket_fund_nearlysixmonthgain(7.434);
		fundMarketVo.setMarket_fund_yeargain(76.232);
		fundMarketVo.setMarket_fund_yeargain(3.222);
		fundMarketVo.setMarket_fund_castsurely("5");
		fundMarketVo.setMarket_fund_company("工商银行日本分行");
		fundMarketVo.setMarket_fund_theme("互联网金融");
		fundMarketVo.setMarket_fund_hotsale(232.232899);
		fundMarketVo.setMarket_fund_manager("理财通");
		fundMarketVo.setMarket_fund_recommendreason("赚钱");
		
		fundMarketVo.setMarket_fund_recommendgain(161.2222);
		fundMarketVo.setMarket_fund_isrecommcreatetime("2015-08-09 19:00:00");
		fundMarketVo.setMarket_fund_isrecomm("1");
		fundMarketVo.setMarket_fund_recommorder(1024);
		fundMarketVo.setMarket_fund_codeinner("KF007008");
		fundMarketVo.setMarket_fund_pinyin("guangdabaode");
		fundMarketVo.setMarket_fund_shortcompany("光大保德信行业轮动混合");
		fundMarketVo.setMarket_fund_upDate("1989-01-01 16:00:02");
		
		//详情
		fundMarketVo.setMarket_fund_detailShortName("基金详情名称");
		fundMarketVo.setMarket_fund_state("2");
		fundMarketVo.setMarket_fund_navTime("2016-07-26 10:00:00");
		fundMarketVo.setMarket_fund_totalTalue(2.22);
		fundMarketVo.setMarket_fund_rate(-10.02);
		fundMarketVo.setMarket_fund_ratez(1.27);
		fundMarketVo.setMarket_fund_risk("中迪风险");
		fundMarketVo.setMarket_fund_fundManagerId("236278372");
		fundMarketVo.setMarket_fund_custodianId("232332");
		fundMarketVo.setMarket_fund_custodian("custodian");
		fundMarketVo.setMarket_fund_issueDate("2016-08-20 09:00:00");
		fundMarketVo.setMarket_fund_perComBen("26326");
		try {
			System.err.println("手动添加索引测试");
			fundMarketHandlerClient.add(fundMarketVo);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
}
