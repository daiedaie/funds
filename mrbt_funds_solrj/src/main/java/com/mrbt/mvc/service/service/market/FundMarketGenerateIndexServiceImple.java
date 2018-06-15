package com.mrbt.mvc.service.service.market;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.mapper.funds.FundDetailMapper;
import com.mrbt.mvc.mapper.funds.FundMarketMapper;
import com.mrbt.mvc.mapper.funds.FundMarketTableMapper;
import com.mrbt.mvc.model.funds.FundFuQuanNetValue;
import com.mrbt.mvc.model.funds.FundLatestNetValue;
import com.mrbt.mvc.vo.FundDetailVo;
import com.mrbt.mvc.vo.FundMarketTableVo;
import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.utils.DateFormaterType;
import com.mrbt.utils.DateUtil;
import com.mrbt.utils.StringCustomerUtils;
import com.mrbt.utils.TimeFormaterType;

@Service
public class FundMarketGenerateIndexServiceImple implements	FundMarketGenerateIndexService {
	private Logger log = LogManager.getLogger(FundMarketGenerateIndexService.class);
	@Autowired
	private FundMarketMapper fundMarketMapper;
	@Autowired
	private FundMarketHandlerService fundMarketHandlerService;
	@Autowired
	private FundMarketTableMapper fundMarketTableMapper;

	static DecimalFormat dcmFmt = new DecimalFormat("0.0000");
	/**
	 * 基金超市索引生成的方法
	 * 
	 * @return
	 */
	@Autowired
	private FundDetailMapper fundDetailMapper;
	@Override
	public boolean generateFundMarketIndex() {
		log.info("开始执行基金超市索引生成方法");
		boolean gainIsEmpty = true;
		List<FundMarketVo> fundMarketVos = fundMarketMapper.selectFundMarketList();
		if (CollectionUtils.isNotEmpty(fundMarketVos)) {
			Map<String, Map<String, Object>> netVaMap = calculateGain();
			Map<String, Object> daygain = netVaMap.get("daygain");
			Map<String, Object> weekgain = netVaMap.get("weekgain");
			Map<String, Object> monthgain = netVaMap.get("monthgain");
			Map<String, Object> threemonthgain = netVaMap.get("threemonthgain");
			Map<String, Object> sixmonthgain = netVaMap.get("sixmonthgain");
			Map<String, Object> yeargain = netVaMap.get("yeargain");
			Map<String, Object> threeyeargain = netVaMap.get("threeyeargain");
			Map<String, Object> latestgain = netVaMap.get("latestNetValues");
			Map<String, Object> fundRecomPeriodGainMap = netVaMap.get("fundRecomPeriodGainMap");
			if (fundRecomPeriodGainMap == null|| fundRecomPeriodGainMap.size() == 0) {
				gainIsEmpty = false;
			}
			List<FundMarketVo> fundMarketDetailsEmpty = new ArrayList<FundMarketVo>();
			for (FundMarketVo fundMarketVo : fundMarketVos) {
				String fundcode = fundMarketVo.getMarket_fund_code();
				fundMarketVo.setMarket_fund_latestnetvalue(parse(latestgain.get(fundcode)));
				fundMarketVo.setMarket_fund_daygain(parse(daygain.get(fundcode)));
				fundMarketVo.setMarket_fund_weekgain(parse(weekgain.get(fundcode)));
				fundMarketVo.setMarket_fund_nearlymonthgain(parse(monthgain.get(fundcode)));
				fundMarketVo.setMarket_fund_nearlythreemonthgain(parse(threemonthgain.get(fundcode)));
				fundMarketVo.setMarket_fund_nearlysixmonthgain(parse(sixmonthgain.get(fundcode)));
				fundMarketVo.setMarket_fund_yeargain(parse(yeargain.get(fundcode)));
				fundMarketVo.setMarket_fund_threeyeargain(parse(threeyeargain.get(fundcode)));
				//详情
				FundDetailVo fundDetailVo = fundDetailMapper.getDetailsFromId(fundcode);
				if(fundDetailVo == null){
					fundMarketDetailsEmpty.add(fundMarketVo);
					continue;
					/*fundMarketVo.setMarket_fund_detailShortName("");
					fundMarketVo.setMarket_fund_state("");
					fundMarketVo.setMarket_fund_navTime("");
					fundMarketVo.setMarket_fund_totalTalue(0);
					fundMarketVo.setMarket_fund_ratez(0);
					fundMarketVo.setMarket_fund_rate(0);
					fundMarketVo.setMarket_fund_risk("");
					fundMarketVo.setMarket_fund_fundManagerId("");
					fundMarketVo.setMarket_fund_custodianId("");
					fundMarketVo.setMarket_fund_custodian("");
					fundMarketVo.setMarket_fund_issueDate("");
					fundMarketVo.setMarket_fund_perComBen("");*/
				}
				else{
					fundMarketVo.setMarket_fund_detailShortName(parseIsEmpty(fundDetailVo.getShortName()));
					fundMarketVo.setMarket_fund_state(parseIsEmpty(fundDetailVo.getState()));
					fundMarketVo.setMarket_fund_navTime(parseIsEmpty(fundDetailVo.getNavTime()));
					fundMarketVo.setMarket_fund_totalTalue(fundDetailVo.getTotalTalue());
					fundMarketVo.setMarket_fund_ratez(fundDetailVo.getRatez());
					fundMarketVo.setMarket_fund_rate(fundDetailVo.getRate());
					fundMarketVo.setMarket_fund_risk(parseIsEmpty(fundDetailVo.getRisk()));
					fundMarketVo.setMarket_fund_fundManagerId(parseIsEmpty(fundDetailVo.getFundManagerId()));
					fundMarketVo.setMarket_fund_custodianId(parseIsEmpty(fundDetailVo.getCustodianId()));
					fundMarketVo.setMarket_fund_custodian(parseIsEmpty(fundDetailVo.getCustodian()));
					fundMarketVo.setMarket_fund_issueDate(parseIsEmpty(fundDetailVo.getIssueDate()));
					fundMarketVo.setMarket_fund_perComBen(parseIsEmpty(fundDetailVo.getPerComBen()));
				}
				// 计算推荐期收益
				if (gainIsEmpty) {
					if(!fundRecomPeriodGainMap.containsKey(fundcode)){
						continue;
					}
					double gain = (double)fundRecomPeriodGainMap.get(fundcode);
					if (gain < 0) {
						fundMarketVo.setMarket_fund_recommendgain(AppInfo.DEFAULTDOUBLEVALUEZERO);
					} else {
						fundMarketVo.setMarket_fund_recommendgain(gain);
					}
				} else {
					fundMarketVo.setMarket_fund_recommendgain(AppInfo.DEFAULTDOUBLEVALUEZERO);
				}
			}
			try {
				log.info("执行基金索引写入方法-" + DateUtil.getCurrentTimes(new Date()));
				if(CollectionUtils.isNotEmpty(fundMarketDetailsEmpty)){
					fundMarketVos.removeAll(fundMarketDetailsEmpty);
				}
				fundMarketHandlerService.addBeans(fundMarketVos);
				log.info("基金索引写入完成-" + DateUtil.getCurrentTimes(new Date()));
				return true;
			} catch (SolrServerException | IOException e) {
				e.printStackTrace();
				log.error("基金超市写入索引失败" + e);
				return false;
			} finally {
				log.info("执行基金超市索引生成方法--完成");
			}
		}
		return false;
	}

	/**
	 * 计算涨幅 最新的净值, 日涨幅、周涨幅、月涨幅、三个月涨幅、六个月涨幅、一年涨幅、三年涨幅
	 */
	@Override
	public Map<String, Map<String, Object>> calculateGain() {
		log.info("计算---最新净值和涨幅");
		Map<String, Map<String, Object>> netValue = new HashMap<String, Map<String, Object>>();
		Map<String, Object> latestNetValues = new HashMap<String, Object>();
		Map<String, Object> daygain = new HashMap<String, Object>();
		Map<String, Object> weekgain = new HashMap<String, Object>();
		Map<String, Object> monthgain = new HashMap<String, Object>();
		Map<String, Object> threemonthgain = new HashMap<String, Object>();
		Map<String, Object> sixmonthgain = new HashMap<String, Object>();
		Map<String, Object> yeargain = new HashMap<String, Object>();
		Map<String, Object> threeyeargain = new HashMap<String, Object>();
		Map<String, Object> fundRecomPeriodGainMap = new HashMap<String, Object>();

		/***
		 * 计算最新的净值
		 */
		List<FundLatestNetValue> fundLatestNetValues = fundMarketMapper
				.selectNetValueLatest();
		for (FundLatestNetValue fundLatestNetValue : fundLatestNetValues) {
			latestNetValues.put(fundLatestNetValue.getFundCode(),
					fundLatestNetValue.getUnitNetValue());
		}
		netValue.put("latestNetValues", latestNetValues);
		/***
		 * 计算日涨幅、周涨幅、月涨幅、三个月涨幅、六个月涨幅、一年涨幅、三年涨幅
		 */
		List<FundFuQuanNetValue> fundFuQuanNetValues = fundMarketMapper.selectNetValueFuQuan();
		//int count = 0;
		if (CollectionUtils.isNotEmpty(fundFuQuanNetValues)) {
			for (FundFuQuanNetValue fundFuQuanNetValue : fundFuQuanNetValues) {
				String CTIME_THS043 = fundFuQuanNetValue.getCtimeThs043();
				String FUND_CODE = fundFuQuanNetValue.getFundCode();
				double FUQUAN_NETVALUE = fundFuQuanNetValue.getFuQuanNetValue();
				if (StringCustomerUtils.isEmpty(CTIME_THS043)
						|| StringCustomerUtils.isEmpty(FUND_CODE)) {
					continue;
				}
				// 时间戳转换为Date类型
				Date curentStringToDate = DateUtil
						.TimeStampToDate(CTIME_THS043);
				// 最新记录的下一个时间
				String nextDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.DATE, AppInfo.PREVIOUSDATEONE, false);
				System.err.println("最新净值时间的昨天是:" + nextDay);
				FundFuQuanNetValue currentNext = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE, nextDay);
				double netValueNext = 0;
				if (currentNext != null) {
					netValueNext = currentNext.getFuQuanNetValue();
					daygain.put(FUND_CODE, gainCalculate(FUQUAN_NETVALUE,netValueNext));
				} else {
					List<FundFuQuanNetValue> currentNexts = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, nextDay);
					if (CollectionUtils.isEmpty(currentNexts)) {
						daygain.put(FUND_CODE, 9999.0);
					}
					else{
						netValueNext = currentNexts.get(0).getFuQuanNetValue();
						daygain.put(FUND_CODE, gainCalculate(FUQUAN_NETVALUE,netValueNext));
					}
				}

				// 计算周涨幅
				String sevenDay = DateUtil.previousDate(curentStringToDate,
						TimeFormaterType.DATE, AppInfo.PREVIOUSDATESEVEN, false);
				System.err.println("最新净值时间的7天前是:" + sevenDay);
				FundFuQuanNetValue currentNextSeven = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE, sevenDay);
				double netValueNextSeven = 0;
				if (currentNextSeven != null) {
					netValueNextSeven = currentNextSeven.getFuQuanNetValue();
					weekgain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE,netValueNextSeven));
				} else {
					List<FundFuQuanNetValue> currentNextSevens = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, sevenDay);
					if (CollectionUtils.isEmpty(currentNextSevens)) {
						weekgain.put(FUND_CODE,9999.0);
					}
					else{
						netValueNextSeven = currentNextSevens.get(0).getFuQuanNetValue();
						weekgain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE,netValueNextSeven));
					}
				}

				// 计算月涨幅
				String monthDay = DateUtil.previousDate(curentStringToDate,
						TimeFormaterType.MONTH, AppInfo.PREVIOUSDATEONE, false);
				System.err.println("最新净值时间的30天前是:" + monthDay);
				FundFuQuanNetValue currentNextMonth = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE, monthDay);
				double netValueNextMonth = 0;
				if (currentNextMonth != null) {
					netValueNextMonth = currentNextMonth.getFuQuanNetValue();
					monthgain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE,netValueNextMonth));
				} else {
					List<FundFuQuanNetValue> currentNextMonths = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, monthDay);
					if (CollectionUtils.isEmpty(currentNextMonths)) {
						monthgain.put(FUND_CODE,9999.0);
					}
					else{
						netValueNextMonth = currentNextMonths.get(0).getFuQuanNetValue();
						monthgain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE,netValueNextMonth));
					}
				}
				// 计算三月涨幅
				String threeMonthDay = DateUtil.previousDate(curentStringToDate, TimeFormaterType.MONTH,AppInfo.PREVIOUSDATETHREE, false);
				System.err.println("最新净值时间的3个月前是:" + threeMonthDay);
				FundFuQuanNetValue currentNextThreeMonth = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE,threeMonthDay);
				double netValueNextThreeMonth = 0;
				if (currentNextThreeMonth != null) {
					netValueNextThreeMonth = currentNextThreeMonth.getFuQuanNetValue();
					threemonthgain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE, netValueNextThreeMonth));
				} else {
					List<FundFuQuanNetValue> currentNextThreeMonths = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, threeMonthDay);
					if (CollectionUtils.isEmpty(currentNextThreeMonths)) {
						threemonthgain.put(FUND_CODE,9999.0);
					}
					else{
						netValueNextThreeMonth = currentNextThreeMonths.get(0).getFuQuanNetValue();
						threemonthgain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE, netValueNextThreeMonth));
					}
				}

				// 计算六个月涨幅
				String sixMonthDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.MONTH, AppInfo.PREVIOUSDATESIX,false);
				System.err.println("最新净值时间的6个月前是:" + sixMonthDay);
				
				FundFuQuanNetValue currentNextSixMonth = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE,sixMonthDay);
				double netValueNextSixMonth = 0;
				if (currentNextSixMonth != null) {
					netValueNextSixMonth = currentNextSixMonth.getFuQuanNetValue();
					sixmonthgain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE, netValueNextSixMonth));
				} else {
					List<FundFuQuanNetValue> currentNextSixMonths = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, sixMonthDay);
					if (CollectionUtils.isEmpty(currentNextSixMonths)) {
						sixmonthgain.put(FUND_CODE,9999.0);
					}
					else{
						netValueNextThreeMonth = currentNextSixMonths.get(0).getFuQuanNetValue();
						sixmonthgain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE, netValueNextSixMonth));
					}
				}

				// 计算年涨幅
				// 查询上一年的最后一天:yyyy-12-31
				String yearDay = DateUtil.previousYearLast(curentStringToDate);
				System.err.println("最新净值时间的一年前是:" + yearDay);
				FundFuQuanNetValue currentNextYear = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE, yearDay);
				double netValueNextYear = 0;
				if (currentNextYear != null) {
					netValueNextYear = currentNextYear.getFuQuanNetValue();
					yeargain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE,netValueNextYear));
				} else {
					List<FundFuQuanNetValue> currentNextYears = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, yearDay);
					if (CollectionUtils.isEmpty(currentNextYears)) {
						yeargain.put(FUND_CODE,9999.0);
					}
					else{
						netValueNextYear = currentNextYears.get(0).getFuQuanNetValue();
						yeargain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE,netValueNextYear));
					}
					
				}
				// 计算三年涨幅
				String threeYearDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.YEAR, AppInfo.PREVIOUSDATETHREE, false);
				System.err.println("最新净值时间的三年前是:" + threeYearDay);
				FundFuQuanNetValue currentNextThreeYear = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE,threeYearDay);
				double netValueNextThreeYear = 0;
				if (currentNextThreeYear != null) {
					netValueNextThreeYear = currentNextThreeYear.getFuQuanNetValue();
					threeyeargain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE,netValueNextThreeYear));
				} else {
					List<FundFuQuanNetValue> currentNextThreeYears = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, threeYearDay);
					if (CollectionUtils.isEmpty(currentNextThreeYears)) {
						threeyeargain.put(FUND_CODE,9999.0);
					}
					else{
						netValueNextThreeYear = currentNextThreeYears.get(0).getFuQuanNetValue();
						threeyeargain.put(FUND_CODE,gainCalculate(FUQUAN_NETVALUE,netValueNextThreeYear));
					}
				}
			}
			netValue.put("daygain",daygain);
			netValue.put("weekgain",weekgain);
			netValue.put("monthgain",monthgain);
			netValue.put("threemonthgain",threemonthgain);
			netValue.put("sixmonthgain",sixmonthgain);
			netValue.put("yeargain",yeargain);
			netValue.put("threeyeargain",threeyeargain);
			netValue.put("fundRecomPeriodGainMap",fundRecomPeriodGainMap);
			/***
			 * 计算推荐期涨幅
			 */
			// 获取推荐的基金代码
			List<FundMarketTableVo> fundMarketTableVos = fundMarketTableMapper.selectFundMarketTableList();
			if (CollectionUtils.isNotEmpty(fundMarketTableVos)) {
				String fundcode = null;
				String innercode = null;
				String creatTime = null;
				for (FundMarketTableVo fundMarketTableVo : fundMarketTableVos) {
					fundcode = fundMarketTableVo.getFunds_code();
					innercode = fundMarketTableVo.getFunds_code_inner();
					creatTime = fundMarketTableVo.getCreate_time();
					if (StringCustomerUtils.isEmpty(fundcode)
							|| StringCustomerUtils.isEmpty(innercode)
							|| StringCustomerUtils.isEmpty(creatTime)) {
						continue;
					}
					double recommendGain = 0;
					Date creatDate = DateUtil.TimeStampToDate(creatTime);
					FundFuQuanNetValue fundFuQuanNetValue = fundMarketMapper.selectNetValueFuQuanByCodeLatest(fundcode);
					if (fundFuQuanNetValue == null) {
						continue;
					} else {
						double currentNetValue = fundFuQuanNetValue.getFuQuanNetValue();
						String createTime = DateUtil.DateToString(creatDate,DateFormaterType.YYYY_MM_DD);
						FundFuQuanNetValue createRecommentDateNetValue = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(fundcode,createTime);
						if (createRecommentDateNetValue == null) {
							List<FundFuQuanNetValue> nexts = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(fundcode, createTime);
							if (CollectionUtils.isEmpty(nexts)) {
								continue;
							}
							recommendGain = nexts.get(0).getFuQuanNetValue();
							fundRecomPeriodGainMap.put(fundcode,gainCalculate(currentNetValue,recommendGain));
						} else {
							recommendGain = createRecommentDateNetValue.getFuQuanNetValue();
							fundRecomPeriodGainMap.put(fundcode,gainCalculate(currentNetValue,recommendGain));
						}
					}
				}
				netValue.put("fundRecomPeriodGainMap", fundRecomPeriodGainMap);
			}
		}
		log.info("计算---日涨幅、周涨幅、月涨幅、三个月涨幅、六个月涨幅、一年涨幅、三年涨幅---完成");
		return netValue;
	}

	


	private double parse(Object param) {
		if (param == null) {
			return AppInfo.DEFAULTDOUBLEVALUEZERO;
		}
		return (double) param;
	}
	//保留4位小数
	private double gainCalculate(double FUQUAN_NETVALUE,double netValueNextSixMonth){
		/*if(netValueNextSixMonth == 0){
			return new BigDecimal("0.0000").doubleValue();
		}*/
		//相减 再除  保留4位  再乘以 100
		/*BigDecimal result = (new BigDecimal(FUQUAN_NETVALUE)).subtract(new BigDecimal(netValueNextSixMonth)).
				divide(new BigDecimal(netValueNextSixMonth),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		
		return result.doubleValue();*/
		double results = 0;
		try{
			System.err.println(FUQUAN_NETVALUE+"===================="+netValueNextSixMonth);
			results = Double.parseDouble(dcmFmt.format((FUQUAN_NETVALUE-netValueNextSixMonth)/netValueNextSixMonth*100));
		}catch(Exception e){
			//e.printStackTrace();
			results = 9999.0;
		}
		return results;
	}
	private String parseIsEmpty(String content){
		if(content == null){
			System.err.println("content IS NULL");
			return "";
		}
		//System.err.println(content);
		return content;
	}
}
