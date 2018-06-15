package com.mrbt.contrast.service.impl;

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
import com.mrbt.contrast.dao.ContrastMapper;
import com.mrbt.contrast.model.ContrastFoundsInfo;
import com.mrbt.contrast.model.FundCompany;
import com.mrbt.contrast.model.SelectContrastData;
import com.mrbt.contrast.service.ContrastService;
import com.mrbt.units.CrawlerWebSousePost;
import com.mrbt.units.DataFormatUnits;
import com.mrbt.units.DateUnits;
import com.mrbt.units.SolrUnits;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("contrastService")
public class ContrastServiceImpl implements ContrastService {
	
	private Logger logger = Logger.getLogger(ContrastServiceImpl.class);
	
	@Resource
	private ContrastMapper contrastMapper;
	
	@Autowired
	private RedisDao redisDao;
	
	@Override
	public JSONObject getFoudCompany() {
		JSONObject json = new JSONObject();
		
		//查询redis
		String redis_key = "contrast_foud_company";
		Object redis_value = redisDao.get(redis_key);
		logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
		
		if(redis_value == null){
			logger.info("从数据看中查询数据");
			
			JSONArray jsona = new JSONArray();
			JSONObject jsonf = new JSONObject();
			
			List<FundCompany> company = contrastMapper.getFundCompany(); 
			for (int i = 0; i < company.size(); i++) {
				JSONObject jsonc = new JSONObject();
				FundCompany com = company.get(i);
				
				JSONArray jsonfa = new JSONArray();
				JSONObject addJson = new JSONObject();
				
				if(jsonf.get(com.getCompanyId()) != null){
					jsonfa = jsonf.getJSONArray(com.getCompanyId());
					
					addJson.put("fundCode", com.getFundCode());
					addJson.put("fundName", com.getFundName());
					jsonfa.add(addJson);
					
					jsonf.put(com.getCompanyId(), jsonfa);
				}else{
					addJson.put("fundCode", com.getFundCode());
					addJson.put("fundName", com.getFundName());
					jsonfa.add(addJson);
					
					jsonf.put(com.getCompanyId(), jsonfa);
					
					//组成基金公司json
					jsonc.put("companyId", com.getCompanyId());
					jsonc.put("companyName", com.getCompanyName());
					
					jsona.add(jsonc);
				}
			}
			
			json.put("companys", jsona);
			json.put("funds", jsonf);

			//数据的查询结果写入到redis中
			redisDao.set(redis_key, json);
			redisDao.expire(redis_key, DateUnits.getTomorrowDate());
		}else{
			json = JSONObject.fromObject(redis_value);
		}
		return json;
	}

	@Override
	public JSONArray getFundsListFromIds(String ids) {
		JSONArray jsona = new JSONArray();//返回json数组
		
		Map<String, String> param = new HashMap<String, String>();//设置请求传值
		String[] idArray = ids.split(",");//拆分url传值
		if(idArray.length > 5){
			return null;
		}
		
		//循环获取每个基金代码的数据
		for (int i = 0; i < idArray.length; i++) {
			//查询redis
			String redis_key = "contrast_foud_lsit_fromids_" + idArray[i];
			Object redis_value = redisDao.get(redis_key);
			logger.info("查询redis中的数据:key" + redis_key + "\n" + redis_value);
			
			if(redis_value == null){
				JSONObject json = new JSONObject();//设置每个ID的json
				
				logger.info("从数据看中查询数据");
				param.put("id", idArray[i]);
				JSONObject solrJson = SolrUnits.getFundsFromIds(param);
				
				if(solrJson.getInt("numFound") > 0){
					ContrastFoundsInfo cfi = new ContrastFoundsInfo();
					json = solrJson.getJSONArray("indexBean").getJSONObject(0);
					
					cfi.setCompanyName(json.getString("market_fund_shortcompany"));
					cfi.setShortName(json.getString("market_fund_shortname"));
					cfi.setFundCode(json.getString("market_fund_code"));
					cfi.setType(json.getString("market_fund_type"));
					cfi.setNetValue(json.getString("market_fund_latestnetvalue"));
					//查询风险等级
					cfi.setRisk(contrastMapper.getFundRisk(cfi.getFundCode()));

					//申购状态
					SelectContrastData as = contrastMapper.getApplyRedeemState(cfi.getFundCode(), 1);
					if(as != null){
						cfi.setApplyState(DataFormatUnits.getApplyRedeemState(as.getApplyRedeemState()));
					}else{
						cfi.setApplyState("未知");
					}
					
					//赎回状态
					SelectContrastData rs = contrastMapper.getApplyRedeemState(cfi.getFundCode(), 2);
					if(rs != null){
						cfi.setRedeemState(DataFormatUnits.getApplyRedeemState(rs.getApplyRedeemState()));
					}else{
						cfi.setRedeemState("未知");
					}
								
					cfi.setScale(json.getString("market_fund_scale"));
					cfi.setAdminName(json.getString("market_fund_shortcompany"));
					cfi.setApplyRates(json.getString("market_fund_buyrate"));
					
					//累计分红
					SelectContrastData cd = contrastMapper.getCumulativeDividend(cfi.getFundCode());
					if(cd != null){
						cfi.setCumulativeDividend(cd.getAvgNum());
					}else{
						cfi.setCumulativeDividend(0);
					}
					
					cfi.setUpDate(DateUnits.getFormatDate(json.getString("market_fund_upDate")));
					cfi.setWeekFeat(json.getString("market_fund_weekgain"));
					cfi.setMonthFeat(json.getString("market_fund_nearlymonthgain"));
					cfi.setSeasonFeat(json.getString("market_fund_nearlythreemonthgain"));
					cfi.setHalfYearFeat(json.getString("market_fund_nearlysixmonthgain"));
					cfi.setOneYearFeat(json.getString("market_fund_yeargain"));
					cfi.setOneYearCSI300("1.000");
					cfi.setOneYearSSE("1.002");
					cfi.setThreeYearFeat(json.getString("market_fund_threeyeargain"));
					cfi.setFullFeat("1.000");
					cfi.setSevenRateReturn("1.002");
					cfi.setExtremeYield("1.0022");
					cfi.setMarchWithdrawal("1.002");
					cfi.setHalfYearWithdrawal("1.002");
					cfi.setOneYearWithdrawal("1.002");
					cfi.setSharpeValue("1.002");
					cfi.setIncomeDeviation("1.00");
					
					//基金经理
					List<SelectContrastData> managersInfo = contrastMapper.getManagersInfo(cfi.getFundCode());
					
					StringBuffer name = new StringBuffer();
					StringBuffer agv = new StringBuffer();
					StringBuffer workingTime = new StringBuffer();
					StringBuffer adminFundsNum = new StringBuffer();
					
					System.out.println(JSON.toJSON(managersInfo));
					int x = managersInfo.size();
					if(x > 0){
						for (int j = 0; j < x; j++) {
							SelectContrastData scd = managersInfo.get(j);
							if(j == x -1){
								name.append(scd.getManagersName());
								agv.append(DataFormatUnits.getManagersAge(scd.getBirthday()));
								workingTime.append(DateUnits.dateToStr(scd.getStateWorkTime()));
								adminFundsNum.append(contrastMapper.getAdminCount(scd.getManagersId()));
							}else{
								name.append(scd.getManagersName() + "/");
								agv.append(DataFormatUnits.getManagersAge(scd.getBirthday()) + "/");
								workingTime.append(DateUnits.dateToStr(scd.getStateWorkTime()) + "/");
								adminFundsNum.append(contrastMapper.getAdminCount(scd.getManagersId()) + "/");
							}
						}
						cfi.setFundManagers(name.toString());
						cfi.setAge(agv.toString());
						cfi.setWorkingTime(workingTime.toString());
						cfi.setAdminFundsNum(adminFundsNum.toString());
					}else{
						cfi.setFundManagers("");
						cfi.setAge("");
						cfi.setWorkingTime("");
						cfi.setAdminFundsNum("");
					}
					
					cfi.setRankingRange("2.00");
					cfi.setTheLargestBond1("q");
					cfi.setTheLargestBond2("w");
					cfi.setTheLargestBond3("e");
					cfi.setTheLargestBond4("r");
					cfi.setTheLargestBond5("t");
					
					
					//获取5个行业配置
					List<String> industrys = contrastMapper.getIndustryDistribution(cfi.getFundCode());
					for (int j = 0; j < industrys.size(); j++) {
						switch (j) {
						case 0:
							cfi.setTheLargestIndustry1(industrys.get(j));
							break;
						case 1:
							cfi.setTheLargestIndustry2(industrys.get(j));
							break;
						case 2:
							cfi.setTheLargestIndustry3(industrys.get(j));
							break;
						case 3:
							cfi.setTheLargestIndustry4(industrys.get(j));
							break;
						case 4:
							cfi.setTheLargestIndustry5(industrys.get(j));
							break;
						}
					}
					
					//累计分红 ths050 f003_ths050 累加/10
					//申购状态，赎回状态ths038表
					//近一年沪深300增长率  待定
					//近一年上证指数增长率  待定
					//七日年化收益率   接口数据
					//万分收益率 接口数据
					//回撤数据 接口
					//sharpe值  接口数据
					//收益标准差  接口
					//基金经理年龄，计算
					//从业日期去掉
					//旗下基金数：select * from ths035 where f001_ths035 in( select ths036.f001_ths036 from ths036 where f003_ths036 = 'H000426544' and f006_ths036 is null);
					//同类基金区间收益排名  接口数据
					//重仓债券：要加数据
					//行业配置取前5个				
					//数据的查询结果写入到redis中
					json = JSONObject.fromObject(cfi);
					redisDao.set(redis_key, json);
					redisDao.expire(redis_key, 60 * 1000, TimeUnit.MILLISECONDS);
					jsona.add(json);
				}
			}else{
				jsona.add(redis_value);
			}
		}
		return jsona;
	}

	@Override
	public JSONArray contrasFullTextSearch(String param) {
		Map<String, String> pram = new HashMap<String, String>();
		//http://192.168.1.88:9999/mrbt_funds_solrj/detail/get	{order=market_fund_code:asc, param=广发, start=0, rows=10}
		pram.put("content", "000055");
		String xml = CrawlerWebSousePost.post("http://192.168.1.88:9999/mrbt_funds_solrj/search/suggest", pram);
		System.out.println(xml);
		return null;
	}
}
