package com.mvc.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.mrbt.mvc.vo.FundMarketListVo;
import com.mrbt.utils.FastJsonUtils;

@SuppressWarnings("deprecation")
public class SearchMarket {
	public static final Logger log = LogManager.getLogger(SearchMarket.class);

	@SuppressWarnings({ "resource", "unchecked", "rawtypes" })
	public String doPost(String url, Map<String, String> map, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(url);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator
						.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
						charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/*
	 * 基金公司列表
	 */
	// @Test
	public void company() {
		SearchMarket post = new SearchMarket();
		String url = "http://localhost:8090/mrbt_funds_solrj/market/company";
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "国泰");
		String result = post.doPost(url, map, "UTF-8");
		System.out.println(result);
	}

	/*
	 * 基金超市列表
	 */
	// @Test
	public void retieve() {
		SearchMarket post = new SearchMarket();
		String param = "我爱北京天安门";
		String url = "http://192.168.1.88:9999/mrbt_funds_solrj/market/retrieve";
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", "0");
		map.put("rows", "10");
		map.put("param", param);
		map.put("order", "market_fund_code:desc");
		String result = post.doPost(url, map, "UTF-8");
		System.out.println(result);
	}

	/*
	 * 基金超市列表
	 */
	//@Test
	public void select() {
		SearchMarket post = new SearchMarket();
		String url = "http://192.168.1.88:9999/mrbt_funds_solrj/market/search";
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", "1");
		map.put("rows", "16");
		//map.put("namecode", "金信基金");
		//map.put("order", null);
		String result = post.doPost(url, map, "UTF-8");
		System.out.println(result);
	}

	/*
	 * 基金超市名称或者代码搜索显示
	 */
	@Test
	public void select2() {
		// 基金列表
		SearchMarket post = new SearchMarket();
		// String url =
		// "http://192.168.1.222:9999/mrbt_funds_solrj/market/list";
		//String url = "http://192.168.1.88:9999/mrbt_funds_solrj/market/list";
		String url = "http://192.168.1.88:9999/mrbt_funds_solrj/market/list";
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", "30");// 分页
		map.put("rows", "10");// 分页
		// "0" "股票型";
		// "1" "债券型";
		// "2" "混合型";
		// "3" "货币型";
		// "4" "指数型";
		// "5" "QDII";
		// "6" "基金型";
		// "7" "保本型";
		// "8" "创新型";
		// map.put("type","0");//基金类型
		// map.put("castsurely","0");//基金定投
		// map.put("company","东方");//基金公司
		// map.put("scale","[147112950 TO 9957112950]");//基金规模
		// 一亿到两亿的规模
		//map.put("scale", "100000000 TO 200000000");// 基金规模
		// map.put("theme",null);//基金主题
		// map.put("order","market_fund_scale:desc");//排序条件
		String result = post.doPost(url, map, "UTF-8");
		System.out.println(result);

		// map.put("start","1");//分页
		// map.put("rows","16");//分页
		// map.put("type","创新型");//基金类型
		// map.put("castsurely","非定投");//基金定投
		// map.put("company","基金公司2");//基金公司
		// map.put("scale","2.1");//基金规模
		// map.put("theme","信息产业");//基金主题
		// map.put("order",null);//排序条件

	}

	/*
	 * 随机6位字符串
	 */
	public static String getRandomString(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/*
	 * 保留4位小数点
	 */

	public static String getFour(double param) {
		return new java.text.DecimalFormat("#.####").format(Math.random()
				* param)
				+ "";
	}

	// @Test
	public void test() {
		double d = new Random().nextDouble();
		System.out.println(d);
		double w = Math.random();
		System.out.println(w);
	}

	@SuppressWarnings("unchecked")
	// @Test
	public void returnList() {
		// 基金列表
		SearchMarket post = new SearchMarket();
		String url = "http://localhost:9999/mrbt_funds_solrj/market/returnlist";
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", "0");// 分页
		map.put("rows", "6000");// 分页
		// "0" "股票型";"1" "债券型"; "2" "混合型"; "3" "货币型"; "4" "指数型";"5" "QDII";"6"
		// "基金型";
		// "7" "保本型";"8" "创新型";map.put("type","0");//基金类型
		// map.put("castsurely","0");//基金定投
		// map.put("company","东方");//基金公司
		// map.put("scale","[147112950 TO 9957112950]");//基金规模
		// 一亿到两亿的规模
		// 数值型才能比较
		map.put("scale", "500000000 TO 1000000000");// 基金规模
		// map.put("theme",null);//基金主题
		// map.put("order","market_fund_scale:desc");//排序条件
		String result = post.doPost(url, map, "UTF-8");
		List<FundMarketListVo> list = ((List<FundMarketListVo>) FastJsonUtils
				.JsonToList(result, FundMarketListVo.class));
		for (FundMarketListVo vo : list) {
			System.out
					.println(vo.getMarket_fund_scale() / 10000 / 10000 + "亿元");
			if (vo.getMarket_fund_scale() / 10000 / 10000 > 90) {
				throw new RuntimeException("不能大于末尾的数字");
			}
			System.err.println(vo.toString());
			System.out.println("============");
		}
	}

	//测试基金超市、根据基金代码获取数据
	//@Test
	public void marketByID() {
		SearchMarket post = new SearchMarket();
		String url = "http://192.168.1.88:9999/mrbt_funds_solrj/market/get";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "000240");
		String result = post.doPost(url, map, "UTF-8");
		System.err.println(result);
	}
}