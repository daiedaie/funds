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
public class HttpClientUtil_FundRecommend_TEST {
	public static final Logger log = LogManager
			.getLogger(HttpClientUtil_FundRecommend_TEST.class);

	@SuppressWarnings({ "resource", "unchecked", "rawtypes" })
	public String doPost(String url, Map<String, String> map, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			// httpClient = new SSLClient();
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
		HttpClientUtil_FundRecommend_TEST post = new HttpClientUtil_FundRecommend_TEST();
		post.add(post);
		//String select = post.select(post);
		//System.out.println(select);

	}

	/*
	 * 执行查询的索引
	 */
	public String select(HttpClientUtil_FundRecommend_TEST post) {
		String url = "http://localhost:8090/mrbt_funds_solrj/market/select";
		return post.doPost(url, new HashMap<String, String>(), "UTF-8");
	}

	/*
	 * 测试添加Market索引
	 */
	public void add(HttpClientUtil_FundRecommend_TEST post) {
		Map<String, String> map = null;
		map = new HashMap<String, String>();
		map.put("RECOMMEND_FUND_TYPE", "创新型");
		map.put("RECOMMEND_FUND_ABBREVIATION", "广发聚财信用B");
		map.put("RECOMMEND_FUND_CODE", "005299");
		map.put("RECOMMEND_FUND_BUYRATE", "5.98");
		map.put("RECOMMEND_FUND_LATESTNETVALUE", "8.8888");
		map.put("RECOMMEND_FUND_NEARLYMONTHGAIN", "-2.36%");
		map.put("RECOMMEND_FUND_NEARLYTHREEMONTHGAIN", "-7.28%");
		map.put("RECOMMEND_FUND_RECOMMENDGAIN", "-5.06%");
		map.put("RECOMMEND_FUND_RECOMMENDREASON", "基金经理倾情推荐");
		String url = "http://localhost:8090/mrbt_funds_solrj/recommend/add";
		System.out.println(post.doPost(url, map, "UTF-8"));
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