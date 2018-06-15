package com.mvc.index;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.service.service.market.FundMarketGenerateIndexService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml" })
public class TestMarketIndexGenerate {
	public static final Logger log = LogManager
			.getLogger(TestMarketIndexGenerate.class);
	@Autowired
	private FundMarketGenerateIndexService fundMarketGenerateIndexService;
	
	@Test
	public void test(){
		long start = System.currentTimeMillis();
		boolean result = fundMarketGenerateIndexService.generateFundMarketIndex();
		if(result){
			System.out.println("索引生成成功");
		}
		else{
			System.out.println("索引生成失败");
		}
		long end = System.currentTimeMillis();
		System.out.println("查询数据生成时间是:"+(end-start));
	}
}