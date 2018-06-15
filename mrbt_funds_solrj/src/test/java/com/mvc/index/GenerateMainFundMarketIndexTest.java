package com.mvc.index;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.mvc.mapper.funds.FundMarketMapper;
import com.mrbt.mvc.model.funds.FundRecomPeriodGain;
import com.mrbt.mvc.model.funds.NetValueAndCode;
import com.mrbt.mvc.service.client.market.FundMarketHandlerClient;
import com.mrbt.mvc.service.service.market.FundMarketGenerateIndexService;
import com.mrbt.mvc.service.service.market.FundMarketHandlerService;
import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.utils.DateFormaterType;
import com.mrbt.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml" })
public class GenerateMainFundMarketIndexTest {
	public static final Logger log = LogManager
			.getLogger(GenerateMainFundMarketIndexTest.class);
	@Autowired
	private FundMarketGenerateIndexService fundMarketGenerateIndexService;
	@Autowired
	private FundMarketHandlerService fundMarketHandlerService;
	@Autowired
	private FundMarketMapper fundMarketMapper;

	@Test
	public void generateIndexTest() {
		long start = System.currentTimeMillis();
		boolean result = fundMarketGenerateIndexService
				.generateFundMarketIndex();
		if (!result) {
			log.info("基金超市索引查询并写入失败");
		} else {
			log.info("基金超市索引查询并写入成功");
		}
		//单线程执行查询并生成索引的时间是
		long end = System.currentTimeMillis();
		log.info("生成索引耗时:(" + (end - start)/1000/60+")分钟");
	}

	// 测试推荐基金推荐期收益值
	// @Test
//	public void testFundRecommendPeroidGains() {
//		long start = System.currentTimeMillis();
//		Date date = DateUtil.StringToDate("2016-01-06",
//				DateFormaterType.YYYY_MM_DD);
//		Map<String, String> map = fundMarketGenerateIndexService
//				.generateFundRecomPeriodGain(date);
//		long end = System.currentTimeMillis();
//		log.info("查询计算耗时:" + (end - start) + "(" + map.size() + ")");
//		// 查询计算耗时:4149 无数据
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			if (entry == null)
//				continue;
//			log.info(entry.getKey() + "========" + entry.getValue());
//		}
//	}


	// 测试基金类型的返回值
	public void TestFundTypeReturnValue() {
		FundMarketVo netValueAndCodes = fundMarketMapper.selectFundMarketList()
				.get(0);
		log.info(netValueAndCodes.getMarket_fund_type());
	}


	// @Test
	@SuppressWarnings("unused")
	public void testAll() {
		Map<String, String> netValue = new HashMap<String, String>();
		String fundcode = "000055";
		String innercode = "KF3997";
		// 昨天
		List<FundRecomPeriodGain> yesNetValues = fundMarketMapper
				.getFundRecomPeriodGain(innercode, "2015-12-29");

		String yesNetValue = yesNetValues.get(0).getGain() + "";

		List<NetValueAndCode> lists = fundMarketMapper
				.selectNetValueByDateAndCode(fundcode, "2015-12-30");

		List<FundRecomPeriodGain> createTimeNetValues = fundMarketMapper
				.getFundRecomPeriodGain(innercode, "2015-11-26");

		String createTimeNetValue = createTimeNetValues.get(0).getGain() + "";

		List<NetValueAndCode> lists2 = fundMarketMapper
				.selectNetValueByDateAndCode(fundcode, "2015-11-27");

		BigDecimal result = (new BigDecimal(createTimeNetValue)).subtract(
				new BigDecimal(yesNetValue)).divide(
				new BigDecimal(yesNetValue), 4, BigDecimal.ROUND_HALF_UP);
		netValue.put(fundcode, result + "");
		for (Map.Entry<String, String> entry : netValue.entrySet()) {
			if (entry == null)
				continue;
			log.info(entry.getKey() + "========" + entry.getValue());
		}
	}
	@Autowired
	private FundMarketHandlerClient fundMarketHandlerClient;
	//@Test
	public void testMarketIndexUpdate(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("market_fund_theme","基金主题");
		boolean result = fundMarketHandlerClient.update("000906",map);
		System.out.println(result);
	}
	//@Test
	public void testLatestNetValue(){
		List<FundMarketVo> lists = fundMarketMapper.selectFundMarketList();
		for(FundMarketVo vo : lists){
			if(vo.getMarket_fund_code().equals("000240")){
				System.out.println(vo.toString());
				break;
			}
			continue;
		}
	}
	//@Test
	public void testDateToString(){
		String creatTime ="2016-07-04 15:20:59";
		Date creatDate = DateUtil.TimeStampToDate(creatTime);
		String show = DateUtil.DateToString(creatDate, DateFormaterType.YYYY_MM_DD);
		System.err.println(show);
	}
}