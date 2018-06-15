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

@SuppressWarnings("deprecation")
public class HttpClientUtil_MarketTable_TEST {
	public static final Logger log = LogManager
			.getLogger(HttpClientUtil_MarketTable_TEST.class);

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

	public static void main(String[] args) throws Exception {
		HttpClientUtil_MarketTable_TEST post = new HttpClientUtil_MarketTable_TEST();
		post.add(post);
		String select = post.select(post);
		System.out.println(select);

	}

	/*
	 * 执行查询的索引
	 */
	public String select(HttpClientUtil_MarketTable_TEST post) {
		String url = "http://localhost:8090/mrbt_funds_solrj/market/select";
		return post.doPost(url, new HashMap<String, String>(), "UTF-8");
	}

	/*
	 * 测试添加Market索引
	 */
	public void add(HttpClientUtil_MarketTable_TEST post) {
		Map<String, String> map = null;
		for (int i = 0; i < 12; i++) {
			map = new HashMap<String, String>();
			map.put("MARKET_FUND_TYPE", "混合型");
			map.put("MARKET_FUND_NAME", "基金超市" + i);
			map.put("MARKET_FUND_CODE", getRandomString(6));
			map.put("MARKET_FUND_SCALE", i+(Math.random()*100)+"");
			map.put("MARKET_FUND_PURCHASERATES", getFour(0.4));
			map.put("MARKET_FUND_LEASTJZ", getFour(0.35));
			map.put("MARKET_FUND_DAY_ZF", getFour(0.49));
			map.put("MARKET_FUND_ONEMONTH_ZF", getFour(1.4));
			map.put("MARKET_FUND_THREEMONTH_ZF", getFour(0.785));
			map.put("MARKET_FUND_YEAR_ZF", getFour(0.783));
			map.put("MARKET_FUND_THREEYEARMONTH_ZF", getFour(1.28));
			String url = "http://localhost:8090/mrbt_funds_solrj/market/add";
			System.out.println(post.doPost(url, map, "UTF-8"));
		}
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
		return new java.text.DecimalFormat("#.####").format(Math.random()*param)+"";
	}
}