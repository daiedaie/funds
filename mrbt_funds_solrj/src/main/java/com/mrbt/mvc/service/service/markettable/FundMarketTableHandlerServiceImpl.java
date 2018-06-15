package com.mrbt.mvc.service.service.markettable;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mrbt.cache.RedisDao;
import com.mrbt.config.AppInfo;
import com.mrbt.mvc.mapper.funds.FundDetailMapper;
import com.mrbt.mvc.mapper.funds.FundMarketMapper;
import com.mrbt.mvc.model.funds.FundFuQuanNetValue;
import com.mrbt.mvc.service.client.market.FundMarketHandlerClient;
import com.mrbt.mvc.service.client.markettable.FundMarketTableHandlerClient;
import com.mrbt.mvc.service.database.FundDBHandlerService;
import com.mrbt.mvc.vo.FundDetailVo;
import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.utils.DateFormaterType;
import com.mrbt.utils.DateUtil;
import com.mrbt.utils.TimeFormaterType;

@Service
public class FundMarketTableHandlerServiceImpl implements
		FundMarketTableHandlerService {
	static DecimalFormat dcmFmt = new DecimalFormat("0.0000");
	@Autowired
	private RedisDao redisDao;
	@Autowired
	@Qualifier("fundMarketTableHandlerClient")
	private FundMarketTableHandlerClient fundMarketTableHandlerClient;

	@Override
	public String delete(String foundcode) throws SolrServerException,
			IOException {
		fundMarketTableHandlerClient.delete(foundcode);
		return foundcode;
	}

	@Override
	public String select(String foundcode) throws SolrServerException,
			IOException {
		return fundMarketTableHandlerClient.select(foundcode);
	}

	@Override
	public String update(Map<String, String> foundmarket, String foundcode)
			throws SolrServerException, IOException {
		return fundMarketTableHandlerClient.update(foundmarket, foundcode);
	}

	@Override
	public void finish() {
		fundMarketTableHandlerClient.finish();
	}

	@Override
	public void rollback() {
		fundMarketTableHandlerClient.rollback();
	}

	@Override
	public void fullimport() throws SolrServerException, IOException {
		fundMarketTableHandlerClient.fullimport();
	}

	@Override
	public String update(String fUNDS_CODE, String cREATE_TIME,
			String iS_RECOMM, String rECOMM_ORDER, String fUNDS_CODE_INNER,
			String rECOMM_REASON, String fUNDS_THEME)
			throws SolrServerException, IOException {
		return fundMarketTableHandlerClient.update(fUNDS_CODE, cREATE_TIME,
				iS_RECOMM, rECOMM_ORDER, fUNDS_CODE_INNER, rECOMM_REASON,
				fUNDS_THEME);
	}

	/**
	 * 添加基金索引的方法
	 */
	@Autowired
	private FundMarketMapper fundMarketMapper;
	@Autowired
	private FundMarketHandlerClient fundMarketHandlerClient;
	@Autowired
	private FundDBHandlerService fundDBHandlerService;
	@Autowired
	private FundDetailMapper fundDetailMapper;
	private ExecutorService executorService = null;
	public boolean add(final String FUND_CODE, final String cREATE_TIME, final String iS_RECOMM,
			final String rECOMM_ORDER, final String fUNDS_CODE_INNER, final String rECOMM_REASON,
			final String fUNDS_THEME) {
		Future<Boolean> result = null;
		try{
			executorService = Executors.newCachedThreadPool();
			//Future<?> future = null;
			result = executorService.submit(new Callable<Boolean>(){
				@Override
				public Boolean call() throws Exception {
					try{
						FundMarketVo fundMarketVo = fundMarketMapper.selectMarketVoByFundCode(FUND_CODE);
						if(fundMarketVo == null){
							log.info("throw new RuntimeException(根据基金代码查询不到数据)");
							//throw new RuntimeException("根据基金代码查询不到数据");
							return false;
						}
						fundMarketVo.setMarket_fund_isrecommcreatetime(cREATE_TIME);
						fundMarketVo.setMarket_fund_isrecomm(iS_RECOMM);
						fundMarketVo.setMarket_fund_recommorder(Integer.parseInt(rECOMM_ORDER));
						fundMarketVo.setMarket_fund_codeinner(fUNDS_CODE_INNER);
						fundMarketVo.setMarket_fund_recommendreason(rECOMM_REASON);
						fundMarketVo.setMarket_fund_theme(fUNDS_THEME);
						//详情
						FundDetailVo fundDetailVo = fundDetailMapper.getDetailsFromId(fundMarketVo.getMarket_fund_code());
						if(fundDetailVo == null){
							return false;
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
						/***
						 * 计算涨幅
						 */
						//查询单个基金的最新的复权净值
						FundFuQuanNetValue fundFuQuanNetValue = fundMarketMapper.selectNetValueFuQuanByFundCode(FUND_CODE);
						double fuQuanNetValue = 0;
						if(fundFuQuanNetValue == null){
							fundMarketVo.setMarket_fund_daygain(0);
							fundMarketVo.setMarket_fund_weekgain(0);
							fundMarketVo.setMarket_fund_nearlymonthgain(0);
							fundMarketVo.setMarket_fund_nearlythreemonthgain(0);
							fundMarketVo.setMarket_fund_nearlysixmonthgain(0);
							fundMarketVo.setMarket_fund_yeargain(0);
							fundMarketVo.setMarket_fund_threeyeargain(0);
						}
						else{
							fuQuanNetValue = fundFuQuanNetValue.getFuQuanNetValue();
							//最新复权净值对应的时间
							Date curentStringToDate = DateUtil.TimeStampToDate(fundFuQuanNetValue.getCtimeThs043());
							// 最新记录的下一个时间
							String nextDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.DATE, AppInfo.PREVIOUSDATEONE, false);
							System.err.println("最新净值时间的昨天是:" + nextDay);
							FundFuQuanNetValue currentNext = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE, nextDay);
							double netValueNext = 0;
							if (currentNext != null) {
								netValueNext = currentNext.getFuQuanNetValue();
								fundMarketVo.setMarket_fund_daygain(gainCalculate(fuQuanNetValue , netValueNext));
							} else {
								List<FundFuQuanNetValue> currentNexts = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, nextDay);
								if (CollectionUtils.isEmpty(currentNexts)) {
									fundMarketVo.setMarket_fund_daygain(9999.0);
								}
								else{
									netValueNext = currentNexts.get(0).getFuQuanNetValue();
									fundMarketVo.setMarket_fund_daygain(gainCalculate(fuQuanNetValue, netValueNext));
								}
							}
							// 计算周涨幅
							String sevenDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.DATE, AppInfo.PREVIOUSDATESEVEN, false);
							System.err.println("最新净值时间的7天前是:" + sevenDay);
							FundFuQuanNetValue currentNextSeven = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE, sevenDay);
							double netValueNextSeven = 0;
							if (currentNextSeven != null) {
								netValueNextSeven = currentNextSeven.getFuQuanNetValue();
								fundMarketVo.setMarket_fund_weekgain(gainCalculate(fuQuanNetValue,netValueNextSeven));
							} else {
								List<FundFuQuanNetValue> currentNextSevens = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, sevenDay);
								if (CollectionUtils.isEmpty(currentNextSevens)) {
									fundMarketVo.setMarket_fund_weekgain(9999.0);
								}
								else{
									netValueNextSeven = currentNextSevens.get(0).getFuQuanNetValue();
									fundMarketVo.setMarket_fund_weekgain(gainCalculate(fuQuanNetValue,netValueNextSeven));
								}
							}
							// 计算月涨幅
							String monthDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.MONTH, AppInfo.PREVIOUSDATEONE, false);
							System.err.println("最新净值时间的30天前是:" + monthDay);
							FundFuQuanNetValue currentNextMonth = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE, monthDay);
							double netValueNextMonth = 0;
							if (currentNextMonth != null) {
								netValueNextMonth = currentNextMonth.getFuQuanNetValue();
								fundMarketVo.setMarket_fund_nearlymonthgain(gainCalculate(fuQuanNetValue,netValueNextMonth));
							} else {
								List<FundFuQuanNetValue> currentNextMonths = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, monthDay);
								if (CollectionUtils.isEmpty(currentNextMonths)) {
									fundMarketVo.setMarket_fund_nearlymonthgain(9999.0);
								}
								else{
									netValueNextMonth = currentNextMonths.get(0).getFuQuanNetValue();
									fundMarketVo.setMarket_fund_nearlymonthgain(gainCalculate(fuQuanNetValue,netValueNextMonth));
								}
							}
							// 计算三月涨幅
							String threeMonthDay = DateUtil.previousDate(curentStringToDate, TimeFormaterType.MONTH,AppInfo.PREVIOUSDATETHREE, false);
							System.err.println("最新净值时间的3个月前是:" + threeMonthDay);
							FundFuQuanNetValue currentNextThreeMonth = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE,threeMonthDay);
							double netValueNextThreeMonth = 0;
							if (currentNextThreeMonth != null) {
								netValueNextThreeMonth = currentNextThreeMonth.getFuQuanNetValue();
								fundMarketVo.setMarket_fund_nearlythreemonthgain(gainCalculate(fuQuanNetValue , netValueNextThreeMonth));
							} else {
								List<FundFuQuanNetValue> currentNextThreeMonths = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, threeMonthDay);
								if (CollectionUtils.isEmpty(currentNextThreeMonths)) {
									fundMarketVo.setMarket_fund_nearlythreemonthgain(9999.0);
								}
								else{
									netValueNextThreeMonth = currentNextThreeMonths.get(0).getFuQuanNetValue();
									fundMarketVo.setMarket_fund_nearlythreemonthgain(gainCalculate(fuQuanNetValue , netValueNextThreeMonth));
								}
							}

							// 计算六个月涨幅
							String sixMonthDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.MONTH, AppInfo.PREVIOUSDATESIX,false);
							System.err.println("最新净值时间的6个月前是:" + sixMonthDay);
							
							FundFuQuanNetValue currentNextSixMonth = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE,sixMonthDay);
							double netValueNextSixMonth = 0;
							if (currentNextSixMonth != null) {
								netValueNextSixMonth = currentNextSixMonth.getFuQuanNetValue();
								fundMarketVo.setMarket_fund_nearlysixmonthgain(gainCalculate(fuQuanNetValue ,netValueNextSixMonth));
							} else {
								List<FundFuQuanNetValue> currentNextSixMonths = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, sixMonthDay);
								if (CollectionUtils.isEmpty(currentNextSixMonths)) {
									fundMarketVo.setMarket_fund_nearlysixmonthgain(9999.0);
								}
								else{
									netValueNextThreeMonth = currentNextSixMonths.get(0).getFuQuanNetValue();
									fundMarketVo.setMarket_fund_nearlysixmonthgain(gainCalculate(fuQuanNetValue ,netValueNextSixMonth));
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
								fundMarketVo.setMarket_fund_yeargain(gainCalculate(fuQuanNetValue,netValueNextYear));
							} else {
								List<FundFuQuanNetValue> currentNextYears = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, yearDay);
								if (CollectionUtils.isEmpty(currentNextYears)) {
									fundMarketVo.setMarket_fund_yeargain(9999.0);
								}
								else{
									netValueNextYear = currentNextYears.get(0).getFuQuanNetValue();
									fundMarketVo.setMarket_fund_yeargain(gainCalculate(fuQuanNetValue,netValueNextYear));
								}
							}
							// 计算三年涨幅
							String threeYearDay = DateUtil.previousDate(curentStringToDate,TimeFormaterType.YEAR, AppInfo.PREVIOUSDATETHREE, false);
							System.err.println("最新净值时间的三年前是:" + threeYearDay);
							FundFuQuanNetValue currentNextThreeYear = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE,threeYearDay);
							double netValueNextThreeYear = 0;
							if (currentNextThreeYear != null) {
								netValueNextThreeYear = currentNextThreeYear.getFuQuanNetValue();
								fundMarketVo.setMarket_fund_threeyeargain(gainCalculate(fuQuanNetValue, netValueNextThreeYear));
							} else {
								List<FundFuQuanNetValue> currentNextThreeYears = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, threeYearDay);
								if (CollectionUtils.isEmpty(currentNextThreeYears)) {
									fundMarketVo.setMarket_fund_threeyeargain(9999.0);
								}
								else{
									netValueNextThreeYear = currentNextThreeYears.get(0).getFuQuanNetValue();
									fundMarketVo.setMarket_fund_threeyeargain(gainCalculate(fuQuanNetValue, netValueNextThreeYear));
								}
							}
							//计算收益期涨幅
							double recommendGain = 0;
							Date creatDate = DateUtil.TimeStampToDate(cREATE_TIME);
							double currentNetValue = fundFuQuanNetValue.getFuQuanNetValue();
							String createTime = DateUtil.DateToString(creatDate,DateFormaterType.YYYY_MM_DD);
							FundFuQuanNetValue createRecommentDateNetValue = fundMarketMapper.selectNetValueFuQuanByCodeAndDate(FUND_CODE,createTime);
							if (createRecommentDateNetValue == null) {
								List<FundFuQuanNetValue> nexts = fundMarketMapper.selectNetValueFuQuanByCodeAndLessThanDate(FUND_CODE, createTime);
								if (CollectionUtils.isEmpty(nexts)) {
									fundMarketVo.setMarket_fund_recommendgain(9999.0);
								}
								else{
									recommendGain = nexts.get(0).getFuQuanNetValue();
									fundMarketVo.setMarket_fund_recommendgain(gainCalculate(currentNetValue,recommendGain));
								}
							} else {
								recommendGain = createRecommentDateNetValue.getFuQuanNetValue();
								fundMarketVo.setMarket_fund_recommendgain(gainCalculate(currentNetValue,recommendGain));
							}
						}
						fundMarketHandlerClient.add(fundMarketVo);
						/***
						 * 写入Redis净值数据
						 */
						Map<String, List<String>> singleFundMonthNetValues = fundDBHandlerService.selectSingleFund30NetValueByCode(fundMarketVo.getMarket_fund_code());
						if (singleFundMonthNetValues == null || singleFundMonthNetValues.size() == 0) {
							log.error("获取不到"+fundMarketVo.getMarket_fund_code()+"的净值");
							return false;
							//throw new RuntimeException("获取不到"+fundMarketVo.getMarket_fund_code()+"的净值");
						}
						for (Map.Entry<String,List<String>> entry : singleFundMonthNetValues.entrySet()) {
							String fundcode = entry.getKey();
							List<String> netvalues = entry.getValue();
							if (StringUtils.isEmpty(fundcode) || CollectionUtils.isEmpty(netvalues)) {
								log.error("获取不到"+fundMarketVo.getMarket_fund_code()+"的净值");
								return false;
								//throw new RuntimeException("获取不到"+fundMarketVo.getMarket_fund_code()+"的净值");
							}
							if(redisDao.hasKey(AppInfo.NETVALUEREDISKEYPREFIX+fundcode)){
								redisDao.delete(AppInfo.NETVALUEREDISKEYPREFIX+fundcode);
							}
							Boolean result = redisDao.setList(AppInfo.NETVALUEREDISKEYPREFIX+fundcode,netvalues);
							if(!result){
								log.error("Redis月净值写入失败"+fundMarketVo.getMarket_fund_code()+"的净值写入失败");
								return false;
								//throw new RuntimeException("Redis月净值写入失败"+fundMarketVo.getMarket_fund_code()+"的净值写入失败");
							}
							else{
								redisDao.expire(AppInfo.NETVALUEREDISKEYPREFIX+fundcode,AppInfo.REDIS_NETVALUE_EXPIRE,AppInfo.REDIS_NETVALUE_EXPIRE_UNIT);
							}
						}
						return true;
					}catch(Exception e){
						log.info("后台添加基金索引失败"+e);
						fundMarketHandlerClient.rollback();
						return false;
					}
				}
			});
		}catch(Exception e){
			System.err.println(e);
			log.info("添加基金索引失败");
			fundMarketHandlerClient.rollback();
			return false;
		}
		try {
			executorService.awaitTermination(10,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			log.info("添加基金-关闭executorService线程池失败");
			fundMarketHandlerClient.rollback();
			return false;
		}finally{
			executorService.shutdown();
		}
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteAllIndex() throws SolrServerException, IOException {
		return fundMarketHandlerClient.deleteAllIndex();
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
			return content;
		}
}
