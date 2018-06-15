package com.mvc.index;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.mapper.funds.FundMarketMapper;
import com.mrbt.mvc.model.funds.FundFuQuanNetValue;
import com.mrbt.mvc.model.funds.FundLatestNetValue;
import com.mrbt.utils.DateFormaterType;
import com.mrbt.utils.DateUtil;
import com.mrbt.utils.TimeFormaterType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml" })
public class MarketDataSelectTest {
	public static final Logger log = LogManager
			.getLogger(MarketDataSelectTest.class);
	@Autowired
	private FundMarketMapper fundMarketMapper;
	//测试数据库查询:最新的两条净值数据
	//@Test
	public void fundMatketMapperTest() {
		long start = System.currentTimeMillis();
		List<FundLatestNetValue> fundLatestNetValues = fundMarketMapper.selectNetValueLatest();
		long end = System.currentTimeMillis();
		log.info("查询耗时:(" + (end - start)/1000/60+")分钟");
		for(FundLatestNetValue latest : fundLatestNetValues){
			System.err.println(latest.toString());
		}
	}
	/*{"ctimeThs034":"2016-01-03 20:09:33","cumulativeNetValue":0,"fundCode":"002260","unitNetValue":1}
	{"ctimeThs034":"2015-12-30 22:19:36","cumulativeNetValue":0,"fundCode":"002260","unitNetValue":1}*/

	//@Test
	public void testFuQuanNetValue(){
		String fundcode = "000055";
		String date = "2015-11-26";
		FundFuQuanNetValue fv = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(fundcode, date);
		System.err.print(fv.toString());
	}
	//@Test
	public void testTime(){
		String time = "2015-11-26 22:09:12:000000";
		String date = DateUtil.StrToString(time,DateFormaterType.YYYY_MM_DD);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String last = null;
		try {
			last = DateUtil.previousYearLast(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.err.println(last);
	}
	//@Test
	public void testTimeShow(){
		String CTIME_THS043 = "2015-11-26 22:09:12:000000";
		Date curentStringToDate = DateUtil.TimeStampToDate(CTIME_THS043);
		String nextDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.DATE,AppInfo.PREVIOUSDATEONE,true);
		System.err.println("最新净值时间的昨天是:"+nextDay);
		String sevenDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.DATE,AppInfo.PREVIOUSDATESEVEN,true);
		System.err.println("最新净值时间的7天前是:"+sevenDay);
		String monthDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.MONTH,AppInfo.PREVIOUSDATEONE,true);
		System.err.println("最新净值时间的30天前是:"+monthDay);
		String threeMonthDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.MONTH,AppInfo.PREVIOUSDATETHREE,true);
		System.err.println("最新净值时间的3个月前是:"+threeMonthDay);
		String sixMonthDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.MONTH,AppInfo.PREVIOUSDATESIX,true);
		System.err.println("最新净值时间的6个月前是:"+sixMonthDay);
		String yearDay = DateUtil.previousYearLast(curentStringToDate);
		System.err.println("最新净值时间的一年前是:"+yearDay);
		String threeYearDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.YEAR,AppInfo.PREVIOUSDATETHREE,true);
		System.err.println("最新净值时间的三年前是:"+threeYearDay);
	}
	@Test
	public void testFuQuanNetValueLessThanSpecialTime(){
		String fundcode = "000055";
		String date = "2015-11-26";
		List<FundFuQuanNetValue> fundFuQuanNetValues = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(fundcode,date);
		for(FundFuQuanNetValue fq : fundFuQuanNetValues){
			System.err.println(fq.toString());
		}
	}
}