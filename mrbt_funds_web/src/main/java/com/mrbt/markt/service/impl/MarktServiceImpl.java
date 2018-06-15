package com.mrbt.markt.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.cache.RedisGet;
import com.mrbt.cache.RedisSet;
import com.mrbt.markt.dao.MarktMapper;
import com.mrbt.markt.service.MarktService;
import com.mrbt.units.Constants;
import com.mrbt.units.GetPinyin;
import com.mrbt.units.SolrUnits;

@Service("marktService")
public class MarktServiceImpl implements MarktService{

	@Autowired
	private RedisGet redisGet;
	@Autowired
	private MarktMapper marktMapper;
	@Autowired
	private RedisSet redisSet;
	
	@Override
	public void getDataList() {
		SolrUnits.getData1();
	}

	@Override
	public List<Map<String,Object>> getHotSellFund(Map<String, String> paramsMap,
			String pUrlName) {
		JSONObject jo = SolrUnits.getSolrFundsByUrl(paramsMap, pUrlName);
		if(null!=jo) {
			List<Map<String,String>> list = (List<Map<String, String>>) jo.get("indexBean");
			List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
			for(Map<String,String> li : list) {
				Map<String,Object> mapData = new HashMap<String,Object>();
				mapData = getLatestNetValue(li.get("hotsale_fund_code"));
				mapData.put("hS_TYPE", li.get("hotsale_fund_type"));
				mapData.put("hS_FUND_MANAGER", li.get("hotsale_fund_manager"));
				mapData.put("hS_FUND_CODE", li.get("hotsale_fund_code"));
				mapData.put("hS_FUND_JC", li.get("hotsale_fund_abbreviation"));
				mapData.put("hS_FUND_LEASTJZ", li.get("hotsale_fund_latestnetvalue"));
				mapData.put("hS_FUND_YEARZF", li.get("hotsale_fund_yeargain"));
				datalist.add(mapData);
			}
			return datalist;
		}
		return null;
	}

	@Override
	public List<Map<String,Object>> getFundPledge(Map<String, String> paramsMap,
			String pUrlName) {
		JSONObject jo = SolrUnits.getSolrFundsByUrl(paramsMap, pUrlName);
		if(null!=jo && null!=jo.get("indexBean")) {
			List<Map<String,String>> list = (List<Map<String, String>>) jo.get("indexBean");
			List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
			for(Map<String,String> li : list) {
				Map<String,Object> mapData = new HashMap<String,Object>();
				mapData = getLatestNetValue(li.get("dt_fund_code"));
				mapData.put("dT_TYPE", li.get("dt_fund_type"));
				mapData.put("dT_FUND_MANAGER", li.get("dt_fund_manager"));
				mapData.put("dT_FUND_CODE", li.get("dt_fund_code"));
				mapData.put("dT_FUND_JC", li.get("dt_fund_abbreviation"));
				mapData.put("dT_FUND_LEASTJZ", li.get("dt_fund_latestnetvalue"));
				mapData.put("dT_FUND_YEARZF", li.get("dt_fund_yeargain"));
				datalist.add(mapData);
			}
			return datalist;
		}
		return null;
	}

	@Override
	public JSONObject queryFundMarketList(Map<String, String> paramsMap,
			String pUrlName) {
		return SolrUnits.getSolrFundsByUrl(paramsMap, pUrlName);
	}

	@Override
	public Map<String, Object> getLatestNetValue(String fundCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> lists = redisGet.getList(Constants.NET_VALUE+fundCode);
		List<String> labelList = new ArrayList<String>();
		List<BigDecimal> dataList = new ArrayList<BigDecimal>();
		int i = 0;
		//如果redis查不到，查询数据库，并写入redis.
		if(null==lists || lists.size()==0) {
			List<Map<String, Object>> netlist = queryLatestNetValueList(fundCode);
			List<Object> li = new ArrayList<Object>();
			if(null!=netlist && netlist.size()>0){
				List<Object> redisNVlist = new ArrayList<Object>();
				for(Map<String,Object> ls : netlist) {
					String dates = (String) ls.get("DATES");
					BigDecimal vals = (BigDecimal) ls.get("VALS");
					String str = ls.get("DATES")+"&"+ls.get("VALS");
					redisNVlist.add(str);
					labelList.add(dates.substring(5, dates.length()));
					dataList.add(vals);
					i++;
				}
				//转为降序
				Collections.reverse(redisNVlist);
				li.add(redisNVlist);
				redisSet.setExpireList(Constants.NET_VALUE+fundCode, li, 1, TimeUnit.DAYS);
			}
		} else {
			//获取第一条list
			List<Object> list = (List<Object>) lists.get(0);
			//转为升序
			Collections.reverse(list);
			for(Object str : list) {
				String s[] = ((String) str).split("&");
				labelList.add(s[0].substring(5, s[0].length()));
				dataList.add(new BigDecimal(s[1]));
				i++;
			}
		}
		map.put("labelList", labelList);
		map.put("dataList", dataList);
		return map;
	}

	@Override
	public List<Map<String, Object>> queryLatestNetValueList(String fundCode) {
		List<Map<String, Object>> list = marktMapper.queryLatestNetValue(fundCode);
		return list;
	}

	@Override
	public Map<Object,Object> queryFundCompanyShortName() {
		List<String> list = marktMapper.queryFundCompanyShortName();
		Map<Object,Object> map = new HashMap<Object,Object>();
		for(String cnStr : list) {
			List<String> names = new ArrayList<String>();
			String p = GetPinyin.getPinYinHeadChar(cnStr).toUpperCase();
			switch (p) {
				case "ZSJJ":
					p="CSJJ";
					break;
				case "ZXJJ":
					p="CXJJ";
					break;
				case "ZAJJ":
					p="CAJJ";
					break;
				case "ZCJJ":
					p="CCJJ";
					break;
				default:
					break;
			}
			p = p.substring(0,1);
			names.add(cnStr);
			if(map.containsKey(p)){
				List<String> li = (List<String>) map.get(p);
				li.add(cnStr);
				map.put(p, li);
			} else {
				map.put(p, names);
			}
		}
		return map;
	}

	@Override
	public Map<Object, Object> queryMarketCompanyNameMap(String key) {
		Map<Object, Object> map = redisGet.getMap(key);
		//如果redis里面为空，去数据库查询，并写入redis
		if(null== map || map.isEmpty()) {
			map = queryFundCompanyShortName();
			//七天过期
			redisSet.setExpireMap(key, map, 7, TimeUnit.DAYS);
		}
		return map;
	}

}
