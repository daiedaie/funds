package com.mvc.database;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.mapper.funds.FundMarketMapper;
import com.mrbt.mvc.mapper.funds.FundNetValueMapper;
import com.mrbt.mvc.model.funds.AllFundLatestNetValueVo;
import com.mrbt.mvc.model.funds.NetValueAndCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml" })
public class TestNetValue {
	public static final Logger log = LogManager.getLogger(TestNetValue.class);
	@Autowired
	private FundNetValueMapper fundNetValueMapper;
	
	@Autowired
	private FundMarketMapper fundMarketMapper;
	//

	//@Test
	public void test() {
		AllFundLatestNetValueVo netvalue = fundNetValueMapper
				.selectAllFundLatestNetValue().get(0);
		log.info(netvalue.getCtimeThs043());
		log.info(netvalue.getF002Ths001());
		log.info(netvalue.getF003Ths043());
		/*
		 * 打印显示 
		 * 2016-06-30 13:43:04,218 [main] INFO [com.mvc.database.TestNetValue] - 2016-01-05 01:03:33 
		 * 2016-06-30 13:43:04,218 [main] INFO [com.mvc.database.TestNetValue] - 000055
		 * 2016-06-30 13:43:04,218 [main] INFO [com.mvc.database.TestNetValue] - 0.2572
		 */
	}
	
	@Test
	public void test2() {
		String fundcode = "000055";
		String date = "2015-11-26";
		List<NetValueAndCode> lists = fundMarketMapper.selectNetValueByDateAndCode(fundcode, date);
		System.out.println(lists.get(0).getFundCode()+"====="+lists.get(0).getNetValue());
		/*
		 * 打印显示 
		 *000055=====0.2601 
		 */
	}
}