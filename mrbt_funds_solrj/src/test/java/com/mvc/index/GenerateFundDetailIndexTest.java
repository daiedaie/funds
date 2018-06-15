/*package com.mvc.index;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.mapper.funds.FundDetailMapper;
import com.mrbt.mvc.service.service.detail.FundDetailGenerateIndexService;
import com.mrbt.mvc.service.service.detail.FundDetailHandlerService;
import com.mrbt.mvc.vo.FundDetailVo;
import com.mrbt.utils.StringCustomerUtils;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class GenerateFundDetailIndexTest {
	public static final Logger log = LogManager.getLogger(GenerateFundDetailIndexTest.class);
	@Autowired
	private FundDetailGenerateIndexService fundDetailGenerateIndexService;
	//@Test
	public void generateFundDetailIndexByCodeTest(){
		long start = System.currentTimeMillis();
		boolean result = fundDetailGenerateIndexService.generateFundDetailIndex();
		long end = System.currentTimeMillis();
		log.info("生成索引耗时:"+(end-start)+" "+result);
	}	
	//@Test
	public void generateFundDetailIndexAllTest(){
		long start = System.currentTimeMillis();
		String code ="000366";
		boolean result = fundDetailGenerateIndexService.generateFundDetailIndexByCode(code);
		long end = System.currentTimeMillis();
		log.info("生成索引耗时:"+(end-start)+" "+result);
	}
	
	@Autowired
	private FundDetailMapper fundDetailMapper;
	
	//测试数据库查询基金详情的SQL语句
	//@Test
	public void testQueryFromDataBaseByFundCode(){
		String fundcode = "502013";
	    FundDetailVo fundDetailVo = fundDetailMapper.getDetailsFromId(fundcode);
		System.out.println(fundDetailVo.toString());
	}
	
	@Autowired
	private FundDetailHandlerService fundDetailHandlerService;
	//@Test
	public void testFundDetailByIdQuery(){
		String fundcode = "123456789";
		String fundDetailVoIndex = null;
		try {
			fundDetailVoIndex = fundDetailHandlerService.getFundDetailByCode(fundcode);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		System.out.println(fundDetailVoIndex);
	}
	
	//打印索引测试
	@Test
	public void testPrintIndexs(){
		try {
			String fundDetailVos = fundDetailHandlerService.getFundDetailList(0,30);
			if(StringCustomerUtils.isNotEmpty(fundDetailVos)){
					System.err.println(fundDetailVos.toString());
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//手动添加索引对象
	//@Test
	public void manalWriteFundeDetailIndex(){
		FundDetailVo fundDetailVo = new FundDetailVo();
		fundDetailVo.setCode("123456789");
		fundDetailVo.setCustodian("中国建设银行股份公司");
		fundDetailVo.setCustodianId("1024122298");
		fundDetailVo.setF001_ths035("KFC007");
		fundDetailVo.setFullName("这个属性是全名");
		fundDetailVo.setFundManager("嘉实基金");
		fundDetailVo.setFundManagerId("912457694");
		fundDetailVo.setIssueDate("2016-7-11 10:30:10");
		fundDetailVo.setNavTime("2016-7-21 01:00:19");
		fundDetailVo.setNetValue(0.6733);
		fundDetailVo.setPerComBen("本基金为股票型指数基金");
		fundDetailVo.setRate(2.3456);
		fundDetailVo.setRatez(-0.3364);
		fundDetailVo.setRisk("高风险");
		fundDetailVo.setScale(1380590228);
		fundDetailVo.setShortName("一路带一路");
		fundDetailVo.setState("开放");
		fundDetailVo.setTotalTalue(2.4562);
		fundDetailVo.setType("0");
		fundDetailVo.setType_name("股票型");
		fundDetailVo.setUpDate("2010-01-01 12:00:20");
		try {
			fundDetailHandlerService.addBean(fundDetailVo);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}*/