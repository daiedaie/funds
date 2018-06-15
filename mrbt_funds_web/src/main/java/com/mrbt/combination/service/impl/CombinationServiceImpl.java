package com.mrbt.combination.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.cache.RedisGet;
import com.mrbt.combination.dao.CombinationMapper;
import com.mrbt.combination.service.CombinationService;
import com.mrbt.units.Constants;
import com.mrbt.units.SolrUnits;

/**
 *@author yiban sun
 *@date 2016年7月11日 下午5:22:51
 *@version 1.0
 *@description 组合宝service实现
 *@since
 **/
@Service("combinationService")
public class CombinationServiceImpl implements CombinationService{
	@Resource
	private CombinationMapper combinationMapper;
	@Autowired
	private RedisGet redisGet;

	@Override
	public List<Map<String,Object>> queryCombinationList(BigDecimal id) {
		return combinationMapper.queryCombinationList(id);
	}
	
	@Override
	public List<Map<String,Object>> queryConfigDetail(String key) {
		List<Map<String,Object>> list = (List<Map<String,Object>>) redisGet.getRedisStringResult(key);
		return list;
	}

	@Override
	public Map<String, Integer> queryCombinationRatio(String key) {
		Map<String, Integer> map = (Map<String, Integer>) redisGet.getRedisStringResult(key);
		return map;
	}

	@Override
	public List<Map<String, Object>> queryCombinationType() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> typeList = combinationMapper.queryCombinationType();
		for(Map<String,Object> type : typeList) {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			BigDecimal i = (BigDecimal) type.get("COMBOID");
			String analysis = (String) type.get("ANALYZE");
			List<Map<String,Object>> tableList = queryCombinationList(i);
			for(Map<String,Object> tab : tableList) {
				JSONObject jo = queryCombinationNY((String) tab.get("FUNDCODE"));
				JSONArray ja = (JSONArray) jo.get("indexBean");
				String netValue = ja.getJSONObject(0).getString("netValue");
				String yearGain = ja.getJSONObject(0).getString("yearGain");
				tab.put("netValue", netValue);
				tab.put("yearGain", yearGain);
			}
			dataMap.put("tableData", tableList);
			List<Map<String,Object>> lineChartList = queryConfigDetail(Constants.PORTFOLIO_PERFORMANCE_TREND+i);
			if(null!=lineChartList && lineChartList.size()>0) {
				List<String> labellist = new ArrayList<String>();
				List<BigDecimal> datalist = new ArrayList<BigDecimal>();
				for(int j = 0 ; j < lineChartList.size(); j++) {
					Map<String,Object> fpc = lineChartList.get(j);
					labellist.add(sdf.format(fpc.get("cDate")));
					datalist.add((BigDecimal) fpc.get("cData"));
				}
				dataMap.put("labellist", labellist);
				dataMap.put("datalist", datalist);
			}
			Map<String,Integer> pieChartMap = queryCombinationRatio(Constants.COMBO_RATIO+i);
			if(null != pieChartMap && !pieChartMap.isEmpty()) {
				Set<String> set = pieChartMap.keySet();
				Iterator<String> it = set.iterator();
				List<Map<String,Object>> pieChartList = new ArrayList<Map<String,Object>>();
				//记录占比分割份数
				int part = 0;
				while(it.hasNext()) {
					Map<String,Object> pieChart = new HashMap<String,Object>();
					String key = (String) it.next();
					Integer val = pieChartMap.get(key);
					pieChart.put("name", key);
					pieChart.put("y", val);
					pieChart.put("sliced", false);
					pieChart.put("selected", false);
					pieChartList.add(pieChart);
					part+=val;
				}
				if(part!=0){
					part = 100/part;
				}else{
					part = 1;
				}
				dataMap.put("part", part);
				dataMap.put("pieData", pieChartList);
			}
			dataMap.put("analysis", analysis);
			list.add(dataMap);
		}
		return list;
	}

	@Override
	public JSONObject queryCombinationNY(String code) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("code", code);
		return SolrUnits.getSolrFundsByUrl(param, "fundCombo");
	}
}
