package com.mvc.index;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.mapper.funds.FundSuggestionMapper;
import com.mrbt.mvc.service.service.suggest.FundSuggestHandlerService;
import com.mrbt.mvc.vo.FundSuggestion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class GenarateSuggestTest {
	
	//测试查询
	@Autowired
	private FundSuggestionMapper fundSuggestionMapper;
	//@Test
	public void testGetFundSuggesions(){
		try {
			List<FundSuggestion> result = fundSuggestionMapper.getFundSuggesions();
			assertTrue(CollectionUtils.isNotEmpty(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//测试索引生成
	@Autowired
	private FundSuggestHandlerService fundSuggestHandlerService;
	@Test
	public void testGenerateFundSuggestsIndex(){
		List<FundSuggestion> fundSuggestions = fundSuggestionMapper.getFundSuggesions();
		try {
			fundSuggestHandlerService.addBeans(fundSuggestions);
		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	
	//测试手动添加
	//@Test
	public void testManalGenerateFundSuggestIndex(){
		FundSuggestion fundSuggestion = new FundSuggestion();
		fundSuggestion.setSuggest_code("12306");
		fundSuggestion.setSuggest_name("嘉实量化");
		fundSuggestion.setSuggest_fullname("嘉实");
		fundSuggestion.setSuggest_pinyin("jiashijijin");
		try {
			fundSuggestHandlerService.addBean(fundSuggestion);
		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}
	}

}
