package com.mvc.index;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.utils.HttpClientUtil;

public class addCastSurely88tTest {
	// @Test
	public void suggest() {
		String url = "http://192.168.1.88:9999/mrbt_funds_solrj/markettable/update";
		Map<String, String> map = new HashMap<String, String>();
		map.put("funds_code", "东方");
		map.put("create_time", "2016-07-27 10:01:01");
		map.put("is_recomm", "1");
		map.put("recomm_order", "1001");
		map.put("funds_code_inner", "东方");
		map.put("recomm_reason", "推荐");
		map.put("funds_theme", "信息产业");
		String result = HttpClientUtil.doPost(url, map, "UTF-8");
		System.err.println(result);
	}

	/**
	 * 测试添加基金的接口程序
	 */
	@Test
	public void test() {
		FundMarketVo fundMarketVo = new FundMarketVo();
		fundMarketVo.setMarket_fund_type("债券型");
		fundMarketVo.setMarket_fund_shortname("鹏华酒B基金");
		fundMarketVo.setMarket_fund_name("鹏华酒B基金");
		fundMarketVo.setMarket_fund_code("999002");
		fundMarketVo.setMarket_fund_scale(900000000);
		fundMarketVo.setMarket_fund_buyrate(2.3890);
		fundMarketVo.setMarket_fund_latestnetvalue(0.2621);
		fundMarketVo.setMarket_fund_daygain(4.9909);
		fundMarketVo.setMarket_fund_weekgain(6.2222);
		fundMarketVo.setMarket_fund_nearlymonthgain(-8.0582);

		fundMarketVo.setMarket_fund_nearlythreemonthgain(-4.0032);
		fundMarketVo.setMarket_fund_nearlysixmonthgain(7.5968);
		fundMarketVo.setMarket_fund_yeargain(0.0232);
		fundMarketVo.setMarket_fund_yeargain(5.2922);
		fundMarketVo.setMarket_fund_castsurely("5");
		fundMarketVo.setMarket_fund_company("大成基金公司");
		fundMarketVo.setMarket_fund_theme("高新技术");
		fundMarketVo.setMarket_fund_hotsale(-999999999);
		fundMarketVo.setMarket_fund_manager("计算机高新技术产业");
		fundMarketVo.setMarket_fund_recommendreason("保底");

		fundMarketVo.setMarket_fund_recommendgain(9.2907);
		fundMarketVo.setMarket_fund_isrecommcreatetime("2016-07-27 11:00:00");
		fundMarketVo.setMarket_fund_isrecomm("1");
		fundMarketVo.setMarket_fund_recommorder(1027);
		fundMarketVo.setMarket_fund_codeinner("KF999004");
		fundMarketVo.setMarket_fund_pinyin("dachengjijin");
		fundMarketVo.setMarket_fund_shortcompany("大成基金公司");
		fundMarketVo.setMarket_fund_upDate("2012-01-01 16:00:02");

		// 详情
		fundMarketVo.setMarket_fund_detailShortName("基金详情名称");
		fundMarketVo.setMarket_fund_state("2");
		fundMarketVo.setMarket_fund_navTime("2016-07-26 10:00:00");
		fundMarketVo.setMarket_fund_totalTalue(2.22);
		fundMarketVo.setMarket_fund_rate(-10.02);
		fundMarketVo.setMarket_fund_ratez(1.27);
		fundMarketVo.setMarket_fund_risk("高风险");
		fundMarketVo.setMarket_fund_fundManagerId("236278372");
		fundMarketVo.setMarket_fund_custodianId("232332");
		fundMarketVo.setMarket_fund_custodian("custodian");
		fundMarketVo.setMarket_fund_issueDate("2016-08-20 09:00:00");
		fundMarketVo.setMarket_fund_perComBen("测试数据");
		
		JSONObject jsonIndex = JSONObject.fromObject(fundMarketVo);
		
		String url = "http://192.168.1.88:9999/mrbt_funds_solrj/market/add";
		Map<String, String> map = new HashMap<String, String>();
		map.put("index", jsonIndex.toString());
		String result = HttpClientUtil.doPost(url, map, "UTF-8");
		System.err.println(result);
		
	}
}
