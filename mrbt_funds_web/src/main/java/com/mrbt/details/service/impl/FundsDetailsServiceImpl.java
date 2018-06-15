package com.mrbt.details.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.mrbt.cache.RedisDao;
import com.mrbt.details.dao.DetailsMapper;
import com.mrbt.details.model.FundPositions;
import com.mrbt.details.model.IndusAllCom;
import com.mrbt.details.model.IndustrAlloca;
import com.mrbt.details.model.ManagersName;
import com.mrbt.details.model.NetValue;
import com.mrbt.details.model.OperatingExpenses;
import com.mrbt.details.model.RateInfo;
import com.mrbt.details.model.ReportingPeriod;
import com.mrbt.details.service.FundsDetailsService;
import com.mrbt.units.DataFormatUnits;
import com.mrbt.units.DateUnits;
import com.mrbt.units.SolrUnits;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("fundsDetailsService")
public class FundsDetailsServiceImpl implements FundsDetailsService {

	private Logger logger = Logger.getLogger(FundsDetailsServiceImpl.class);

	@Resource
	private DetailsMapper detailsMapper;
	
	@Autowired
	private RedisDao redisDao;
	
	//设置reids保存时间
	private final int SECONDSNUM = 300;

	@Override
	public JSONObject getBaseInfo(String id) {
		JSONObject json = new JSONObject();

		//查询redis
		String redis_key = "details_base_info_" + id;
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
		
		if(redis_value == null){
			logger.info("从solr接口中获取数据");
			
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("id", id);

			json = SolrUnits.getFundsDetails(paramsMap);
			logger.info(json);

			if (json == null || json.getInt("numFound") == 0) {
				return null;
			}
			
			json = json.getJSONArray("indexBean").getJSONObject(0);
			
			//数据的查询结果写入到redis中
			redisDao.set(redis_key, json);
			redisDao.expire(redis_key, SECONDSNUM, TimeUnit.SECONDS);
		}else{
			json = JSONObject.fromObject(redis_value);
		}
		return json;
	}

	@Override
	public String findFundManagersName(String f001_ths035) {
		List<ManagersName> managName = detailsMapper.findFundManagersName(f001_ths035);
		if (managName != null) {
			StringBuffer sb = new StringBuffer();
			int size = managName.size();

			for (int i = 0; i < size; i++) {
				ManagersName managersName = managName.get(i);
				if (i == size - 1) {
					sb.append(managersName.getName());
				} else {
					sb.append(managersName.getName() + "、");
				}

			}
			return sb.toString();
		}
		return null;
	}

	@Override
	public JSONObject getNetValueChart(String id, int interval, int type) {
		JSONObject json = new JSONObject();
		
		//查询redis
		String redis_key = "details_net_value_chart_" + id + "_" + interval + "_" + type;
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
		
		if(redis_value == null){
			logger.info("从数据库中获取数据");
			
			NetValue param = new NetValue();
			param.setFundCode(id);
			
			switch (interval) {
			case 1:
				param.setStateDate(DateUnits.getMonthlyInterval()[0]);
				param.setEndDate(DateUnits.getMonthlyInterval()[1]);
				break;
			case 2:
				param.setStateDate(DateUnits.getHalfYearInterval()[0]);
				param.setEndDate(DateUnits.getHalfYearInterval()[1]);
				break;
			case 3:
				param.setStateDate(DateUnits.getOneYearInterval()[0]);
				param.setEndDate(DateUnits.getOneYearInterval()[1]);
				break;
			case 4:
				param.setStateDate(DateUnits.getTreeYearInterval()[0]);
				param.setEndDate(DateUnits.getTreeYearInterval()[1]);
				break;
			default:
				break;
			}
		
			List<NetValue> resList = detailsMapper.getNetValueList(param);
			if(resList != null){
				JSONArray jsonax = new JSONArray();
				JSONArray jsonay = new JSONArray();
				
				for (int i = 0; i < resList.size(); i++) {
					NetValue net = resList.get(i);
					if(type == 1){
						jsonay.add(net.getNetValue());
					}else{
						if(net.getTotalTalue() > 0){
							jsonay.add(net.getTotalTalue());
						}else{
							jsonay.add(net.getNetValue());
						}
					}
					jsonax.add(DateUnits.getStrDate(net.getNetDate()));
				}
				json.put("x", jsonax);
				json.put("y", jsonay);
				json.put("spanning", resList.size()/5); //设置x轴刻度的步长
			}else{
				return null;
			}
			
			//数据的查询结果写入到redis中
			redisDao.set(redis_key, json);
			redisDao.expire(redis_key, SECONDSNUM, TimeUnit.SECONDS);
		}else{
			json = JSONObject.fromObject(redis_value);
		}
		return json;
	}

	@Override
	public JSONObject getIndustrAlloca(String id, String type) {
		JSONObject json = new JSONObject();// data中的json对象
		
		//查询redis
		String redis_key = "details_industr_alloca_" + id + "_" + type;
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
		
		if(redis_value == null){
			
			logger.info("从数据库中查询数据");
			
			//查询基金的所有年份
			List<String> yearList = detailsMapper.getIndustAllocaYear(id);
			
			int x = yearList.size();
			if(x > 0){
				String[] getSqlDate = DateUnits.getSqlDate(yearList.get(0), 0);
				
				//找出指定时间内的数据
				List<IndustrAlloca> res = detailsMapper.getIndustrAlloca(getSqlDate[0], getSqlDate[1], id);
				
				//没有查询到数据查询前一个季度的数据
				if(res.size() == 0){
					getSqlDate = DateUnits.getSqlDate(yearList.get(0), 1);
					res = detailsMapper.getIndustrAlloca(getSqlDate[0], getSqlDate[1], id);
				}
				
				if (res != null && res.size() > 0) {
		
					JSONObject jsonChart = new JSONObject();// 柱状图json
					JSONArray jsona_id = new JSONArray();// 柱状图x轴
					JSONArray proportion = new JSONArray();// 柱状图y轴
		
					int z = res.size();
					
					// 拼接柱状图json数据
					for (int j = 0; j < z; j++) {
						IndustrAlloca alloca = res.get(j);
		
						if (j == 0) {
							jsonChart.put("shortName", alloca.getShortName());
							jsonChart.put("endDate", DateUnits.getFormatDate(alloca.getEndDate()));
							jsonChart.put("maxProportion", alloca.getProportion());
						}
						
						jsona_id.add(alloca.getIndustryId());// x累加
						proportion.add(alloca.getProportion());// y累加
						alloca.setMarketValues(DataFormatUnits.thousandBits(alloca.getMarketValue()));
					}
		
					jsonChart.put("industryId", jsona_id);// 添加x
					jsonChart.put("proportion", proportion);// 添加y
					json.put("chart", jsonChart);
					json.put("year", yearList);
					json.put("table", JSON.toJSON(res));//表格数据
				}else{
					return null;
				}
			}else{
				return null;
			}
			//数据的查询结果写入到redis中
			redisDao.set(redis_key, json);
			redisDao.expire(redis_key, SECONDSNUM, TimeUnit.SECONDS);
		}else{
			json = JSONObject.fromObject(redis_value);
		}
		return json;
	}
	
	@Override
	public JSONObject getFundPositions(String id, String year) {
		JSONObject json = new JSONObject();// data中的json对象
		
		//查询redis
		String redis_key = "details_fund_positions_" + id + "_" + year;
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
		
		if(redis_value == null){
			
			logger.info("从数据库中查询数据");
			
			//获取持仓数据年份列表
			List<String> yearList = new ArrayList<String>();
			yearList.add("2016");
			yearList.add("2015");
			yearList.add("2014");
			
			json.put("year", yearList);
			
			//获取持仓图表
			JSONObject chart = new JSONObject();
			
			JSONArray chartx = new JSONArray();
			JSONArray charty = new JSONArray();
			for (int i = 0; i < 10; i++) {
				chartx.add("名称" + i);
				charty.add(30 + i);
			}
			chart.put("x", chartx);
			chart.put("y", charty);
			
			json.put("chart", chart);
			
			//获取列表页面
			List<FundPositions> listPosit = new ArrayList<FundPositions>();
			
			for (int i = 0; i < 10; i++) {
				FundPositions posit = new FundPositions();
				posit.setFundCode("001070");
				posit.setShortName("建信信息产业股票");
				posit.setNewPrice(26.15 - i);
				posit.setRiseFall(2.95 + 0.1);
				posit.setDesc("变更详情");
				posit.setNetValue(8.97 - 1);
				posit.setPositNumber(500 + 1);
				posit.setPositPrice(600 + 1);
				listPosit.add(posit);
			}
			
			json.put("table", listPosit);
			//数据的查询结果写入到redis中
			redisDao.set(redis_key, json);
//			redisDao.expire(redis_key, DateUnits.getTomorrowDate());
			redisDao.expire(redis_key, SECONDSNUM, TimeUnit.SECONDS);
		}else{
			json = JSONObject.fromObject(redis_value);
		}
		return json;
	}
	
	@Override
	public JSONObject getIndustryAllocaCompare(String id) {
		JSONObject json = new JSONObject();// data中的json对象
		
		//查询redis
		String redis_key = "details_industry_alloca_compare_" + id;
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
		
		if(redis_value == null){
			
			logger.info("从数据库中查询数据");
			//查询行业配置比较
			List<IndusAllCom> list = new ArrayList<IndusAllCom>();
			
			for (int i = 0; i < 10; i++) {
				IndusAllCom indus = new IndusAllCom();
				indus.setIndusCode("A" + i);
				indus.setIndusName("采矿业" + i);
				indus.setIndusConf("6.23");
				indus.setIndusAge("5.26");
				indus.setIndusSper("22.36");
				list.add(indus);
			}
			
			json.put("table", list);
			//数据的查询结果写入到redis中
			redisDao.set(redis_key, json);
//			redisDao.expire(redis_key, DateUnits.getTomorrowDate());
			redisDao.expire(redis_key, SECONDSNUM, TimeUnit.SECONDS);
		}else{
			json = JSONObject.fromObject(redis_value);
		}
		return json;
	}

	@Override
	public JSONArray getFundsManagersInfo(String funds_code) {
		JSONArray jsona = new JSONArray();
		
		//查询redis
		String redis_key = "details_funds_managers_info_" + funds_code;
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
				
		if(redis_value == null){
			//获取基金经理基本信息
			List<ManagersName> resList = detailsMapper.getManagerBaseInfo(funds_code);
			if(resList.size() > 0){
				//循环基金经理基本信息，获取基金经理经历信息
				for (int i = 0; i < resList.size(); i++) {
					ManagersName m1 = resList.get(i);
					JSONObject json = JSONObject.fromObject(m1);
					
					List<ManagersName> resList2 = detailsMapper.getManagerExperience(m1.getId());
					
					JSONArray jsona2 = new JSONArray();
					
					for (int j = 0; j < resList2.size(); j++) {
						ManagersName m2 = resList2.get(j);
						if(m2.getOutgoingDate() == null){
							m2.setOutgoingDate("至今");
						}
						m2.setServingDay((DateUnits.getDateSubtraction(m2.getServingTime(), m2.getOutgoingDate())));
						
						//计算任职回报，最新的(净值-起始时间的净值)/起始时间的净值*100
						double netValue1 = detailsMapper.getNewNetValue(m2.getFundsCode());
						double netValue2 = detailsMapper.getOldNetValue(m2.getFundsCode(), m2.getServingTime());
						
						m2.setInReturn(DataFormatUnits.thousandBits((netValue1 - netValue2)/netValue2 * 100));

						JSONObject json2 = JSONObject.fromObject(m2);
						jsona2.add(json2);
					}
					json.put("exper", jsona2);
					jsona.add(json);
				}
			}else{
				return null;
			}
			//数据的查询结果写入到redis中
			redisDao.set(redis_key, jsona);
//			redisDao.expire(redis_key, DateUnits.getTomorrowDate());
			redisDao.expire(redis_key, SECONDSNUM, TimeUnit.SECONDS);
		}else{
			jsona = JSONArray.fromObject(redis_value);
		}
		return jsona;
	}

	@Override
	public JSONArray getFundEvaluation(String id) {
		//天相评价，查询分险等级
		
		//特色数据 ，接口数据
		return null;
	}

	@Override
	public JSONObject getReportingPeriod(String id) {
		JSONObject json = new JSONObject();
		
		//查询redis
		String redis_key = "details_reporting_period_" + id;
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
				
		if(redis_value == null){
			//份额规模数据来源，THS054
			List<ReportingPeriod> resList = detailsMapper.getReportingPeriod(id);
			if(resList.size() > 0){
				JSONObject chart = new JSONObject();
				
				JSONArray jsonay1 = new JSONArray();
				JSONArray jsonay2 = new JSONArray();
				JSONArray jsonay3 = new JSONArray();
				JSONArray jsonay4 = new JSONArray();
				JSONArray jsonax = new JSONArray();
				
				for (int i = resList.size() - 1 ; i >= 0; i--) {
					ReportingPeriod report = resList.get(i);
					
					jsonax.add(report.getReportDate());//时间
					jsonay1.add(Double.parseDouble(DataFormatUnits.thousandBits(report.getPurchase())));//期间申购
					jsonay2.add(Double.parseDouble(DataFormatUnits.thousandBits(report.getRedeem())));//期间赎回
					jsonay3.add(Double.parseDouble(DataFormatUnits.thousandBits(report.getThefinal())));//期末申购
					
					//查询指定日期或指定日期之前的净值
					double netValue = detailsMapper.netValueFromDataCode(report.getThsKey(), report.getReportDate());
					report.setNetAssetSize(report.getThefinal() * netValue);
					jsonay4.add(Double.parseDouble(DataFormatUnits.thousandBits(report.getNetAssetSize())));
					
					
					//总份额变动率=(期间申购-期间赎回)/(期末中份额-(期间申购-期间赎回))
					report.setRateChange((report.getPurchase() - report.getRedeem()) / (report.getThefinal()-(report.getPurchase() - report.getRedeem())));
				}
					
				chart.put("y1", jsonay1);
				chart.put("y2", jsonay2);
				chart.put("y3", jsonay3);
				chart.put("y4", jsonay4);
				chart.put("x", jsonax);
					
				json.put("chart", chart);
				json.put("table", resList);
			}else{
				return null;
			}
		
			//数据的查询结果写入到redis中
			redisDao.set(redis_key, json);
//			redisDao.expire(redis_key, DateUnits.getTomorrowDate());
			redisDao.expire(redis_key, SECONDSNUM, TimeUnit.SECONDS);
		}else{
			json = JSONObject.fromObject(redis_value);
		}
		return json;
	}

	@Override
	public JSONObject getRateInfo(String id) {
		JSONObject json = new JSONObject();
		
		//查询redis
		String redis_key = "details_rate_info_" + id;
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
					
		if(redis_value == null){
			//查询申购费率
			List<RateInfo> resList = detailsMapper.getRateInfo(id);
			if(resList.size() > 0){
				JSONArray jsona = new JSONArray();
				for (int i = 0; i < resList.size(); i++) {
					RateInfo rate = resList.get(i);
					if(rate.getMaxPrice() < rate.getMinPrice() || rate.getMaxPrice() == 0){
						rate.setTitle("大于" + rate.getMinPrice()  + "万元");
					}else{
						rate.setTitle(rate.getMinPrice() + "万元~" + rate.getMaxPrice() + "万元" );
					}
					
					jsona.add(rate);
				}
				
				json.put("apply", jsona);
			}
			
			//查询赎回费率
			List<RateInfo> resList2 = detailsMapper.getRateInfo2(id);
			if(resList2.size() > 0){
				JSONArray jsona = new JSONArray();
				for (int i = 0; i < resList2.size(); i++) {
					RateInfo rate = resList2.get(i);
					if(rate.getMinDay() == 0 && rate.getMaxDay() < 1){
						rate.setTitle("小于" + DataFormatUnits.mathRint(rate.getMaxDay() * 30) + "天");
					}else if(rate.getMinDay() > 1 && rate.getMaxDay() < 1){
						rate.setTitle("大于" + DataFormatUnits.mathRint(rate.getMinDay()) + "月");
					}else if(rate.getMinDay() <1){
						rate.setTitle("大于" + DataFormatUnits.mathRint(rate.getMinDay() * 30) + "天小于" + DataFormatUnits.mathRint(rate.getMaxDay()) + "月");
					}else{
						rate.setTitle("大于" + DataFormatUnits.mathRint(rate.getMinDay()) + "月小于" + DataFormatUnits.mathRint(rate.getMaxDay()) + "月");
					}
					
					jsona.add(rate);
				}
				
				json.put("redeem", jsona);
			}
			
			//运作费用
			OperatingExpenses operExp = detailsMapper.getOperatingExpenses(id);
			json.put("oper_exp", operExp);	
			
			//数据的查询结果写入到redis中
			redisDao.set(redis_key, json);
//			redisDao.expire(redis_key, DateUnits.getTomorrowDate());
			redisDao.expire(redis_key, SECONDSNUM, TimeUnit.SECONDS);
		}else{
			json = JSONObject.fromObject(redis_value);
		}
		
		return json;
	}
}
