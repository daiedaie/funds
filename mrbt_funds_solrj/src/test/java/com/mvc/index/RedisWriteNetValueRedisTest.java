package com.mvc.index;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.mrbt.cache.RedisDao;
import com.mrbt.config.AppInfo;
import com.mrbt.mvc.mapper.funds.FundNetValueMapper;
import com.mrbt.mvc.model.funds.AllFundLatestNetValueVo;
import com.mrbt.mvc.service.database.FundDBHandlerService;
import com.mrbt.mvc.service.redis.FundNetValueRedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class RedisWriteNetValueRedisTest {
	public static final Logger log = LogManager.getLogger(RedisWriteNetValueRedisTest.class);
	@Autowired
	private FundNetValueRedisService fundNetValueRedisService;
	@Test
	public void generateIndexTest(){
		//当月
		//double result = fundNetValueRedisService.saveAllFundNormalMonthNetValueToRedis();
		double result = fundNetValueRedisService.saveAllFundMonthNetValueToRedis();
		if(result > 0){
			log.info("基金月净值写入redis数量是"+result );
		}
		else{
			log.info("基金月净值写入redis失败" );
		}
	}	
	
	
	@Autowired
	private FundNetValueMapper fundNetValueMapper;
	//@Test
	public void testNetValueQuery(){
		List<AllFundLatestNetValueVo> netValue30s = fundNetValueMapper.selectAllFund30NetValue();
		System.out.println(netValue30s.size());
	}
	//@Test
	public void testAllFund30NetValueByDate(){
		String startDate = "2016-01-01";
		String endDate = "2016-01-30";
		List<AllFundLatestNetValueVo> netValue30s = fundNetValueMapper.selectAllFund30NetValueByDate(startDate, endDate);
		System.out.println(netValue30s.size());
	}
	//删除所有净值
	@Autowired
	private FundDBHandlerService fundDBHandlerService;
	@Autowired
	private 
	RedisDao redisDao;
	//@Test
	public void saveAllFundMonthNetValueToRedis() {
		double count = 0;
		//查询所有基金的月净值
		Map<String, List<String>> monthNetValueLists = fundDBHandlerService.selectAllMonthNetValueAll();
		if (monthNetValueLists == null || monthNetValueLists.size() == 0) {
			return;
		}
		boolean delete = false;
		for (Map.Entry<String,List<String>> entry : monthNetValueLists.entrySet()) {
			if(entry == null)continue;
			String fundcode = entry.getKey();
			String key = AppInfo.NETVALUEREDISKEYPREFIX+fundcode;
			delete = redisDao.delete(key);
			if(delete){
				count++;
			}
		}
		System.out.println("删除("+count+")个");
	}
	//@Autowired
	//private RedisTemplate redisTemplate;
	public void test(String key){
		if(redisDao.hasKey(key) && redisDao.getListSize(key) > 0){
			List<Object> results = redisDao.getList(key);
			results.add("9999/99/99&99.99");
			//boolean delete = redisDao.delete(key);
			boolean delete = redisDao.clear();
			if(delete){
				redisDao.setList(key,results);
				results = null;
				System.out.println("-------再次查询--------");
				if(redisDao.hasKey(key)){
					System.err.println("数量:"+redisDao.getListSize(key));
				}
				results = redisDao.getList(key);
				System.out.println("count:"+results.size());
				for(Object obj : results){
					System.out.println("行:"+obj.toString());
				}
			}
			else{
				System.err.println("null");
			}
		}
		else{
			System.err.println("添加30天");
		}
	}
	
	//@Test
	public void writeRedisTest(){
		String key = "netvalue1024122298";
		test(key);
	}
	public void append(String key){
		boolean result = redisDao.setList(key, "2020/22/20&12.220");
		System.err.println(result);
	}
	//清空Redis数据
	//@Test
	public void clear(){
		boolean clear = redisDao.clear();
		System.out.println("清空"+clear);
	}
	//删除192.168.1.88净值数据
	
	//@Test
	public void clear88(){
		Map<String, List<String>> monthNetValueLists = fundDBHandlerService.selectAllMonthNetValueAll();
		if (monthNetValueLists == null || monthNetValueLists.size() == 0) {
			return;
		}
		for (Map.Entry<String,List<String>> entry : monthNetValueLists.entrySet()) {
			String fundcode = entry.getKey();
			List<String> netvalues = entry.getValue();
			if (StringUtils.isEmpty(fundcode) || CollectionUtils.isEmpty(netvalues)) {
				continue;
			}
			redisDao.delete(AppInfo.NETVALUEREDISKEYPREFIX+fundcode);
		}
	}
	
	
	//@Test
	public void fundNetValueMapper(){
		List<AllFundLatestNetValueVo> monthNetValueLists = fundNetValueMapper.selectAllFundLatestNetValue();
		if (monthNetValueLists == null || monthNetValueLists.size() == 0) {
			return;
		}
		for (AllFundLatestNetValueVo vo : monthNetValueLists) {
			String fundcode = vo.getF002Ths001();
			redisDao.delete(AppInfo.NETVALUEREDISKEYPREFIX+fundcode);
		}
	}
	//====================================================================================================================
	//@Test
	public void test(){
		List<String> list = new ArrayList<String>();
		list.add("solrnetvalue000760");
		list.add("solrnetvalue519755");
		list.add("solrnetvalue519956");
		list.add("solrnetvalue519963");
		list.add("solrnetvalue150298");
		list.add("solrnetvalue150304");
		list.add("solrnetvalue150312");
		list.add("solrnetvalue150000");
		//============================
		list.add("solrnetvalue001088");
		list.add("solrnetvalue001092");
		list.add("solrnetvalue001105");
		list.add("solrnetvalue001110");
		list.add("solrnetvalue001117");
		list.add("solrnetvalue001125");
		list.add("solrnetvalue001126");
		list.add("solrnetvalue001135");
		
		for(String key : list){
			try{
				System.out.println("-------查询--------");
				if(redisDao.hasKey(key)){
					System.err.println("数量:"+redisDao.getListSize(key));
				}
				List<Object> results = redisDao.getList(key);
				System.out.println("count:"+results.size());
				for(Object obj : results){
					System.out.println("行:"+obj.toString());
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
		list = null;
	}
}