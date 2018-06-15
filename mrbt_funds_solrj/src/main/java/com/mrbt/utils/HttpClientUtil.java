package com.mrbt.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@SuppressWarnings("deprecation")
public class HttpClientUtil {
	public static final Logger log = LogManager.getLogger(HttpClientUtil.class);
	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	public static String doPost(String url, Map<String, String> map, String charset) {
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

	@SuppressWarnings("resource")
	public String doGet(String url, String charset) throws Exception {
		String strResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				strResult = EntityUtils.toString(response.getEntity());
				/** 把json字符串转换成json对象 **/
				//jsonResult = JSONObject.fromObject(strResult);
				//url = URLDecoder.decode(url, "UTF-8");
			} else {
			}
		} catch (IOException e) {
		}
		return strResult;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		HttpClientUtil post = new HttpClientUtil();
		Map<String, String> map = new HashMap<String, String>();
		map.put("FUNDS_CODE_OLD","测试1980");
		map.put("FUNDS_CODE","测试1989");
		map.put("CREATE_TIME", "2022-06-22 10:00:01");
		map.put("IS_RECOMM", "0");
		map.put("RECOMM_ORDER", "-1");
		String url = "http://localhost:8090/mrbt_funds_solrj/foundmarket/update";
		String url2 = "http://localhost:8090/mrbt_funds_solrj/foundmarket/add";
		//String result = post.doPost(url,map, "UTF-8");
		//System.out.println(result);
		//log.info(result);
		
		String full = "http://localhost:8090/mrbt_funds_solrj/foundmarket/full-import";
	    //String fullresult = post.doPost(full,new HashMap<String,String>(), "UTF-8");
		//System.out.println(fullresult);
		//log.info(fullresult);
		
		//Map<String,Integer> maps = new HashMap<String,Integer>();
		//String recomm = "http://localhost:9999/mrbt_funds_solrj/foundrecom/select";
		//maps.put("start", 0);
		//maps.put("end", 10);
		//System.out.println(post.doPost(recomm, maps, "UTF-8"));
		//String select = "http://localhost:9999/mrbt_funds_solrj/foundmarket/select/KFGC";
		//添加 http://192.168.1.175:9999/mrbt_funds_solrj/foundmarket/add
		//更新 http://192.168.1.175:9999/mrbt_funds_solrj/foundmarket/update
		//查询 http://192.168.1.175:9999/mrbt_funds_solrj/foundmarket/select/KFGC
		//删除 http://192.168.1.175:9999/mrbt_funds_solrj/foundmarket/delete/KFGC
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("HS_TYPE", "基金型");
		map2.put("HS_FUND_MANAGER", "于凡");
		map2.put("HS_FUND_JC", "天天基金");
		map2.put("HS_FUND_CODE", "KC076");
		map2.put("HS_FUND_LEASTJZ", "8.24");
		map2.put("HS_FUND_YEARZF", "3.15");
		String rx = "http://localhost:8090/mrbt_funds_solrj/hotsales/add";
		String dt = "http://localhost:8090/mrbt_funds_solrj/castsurely/add";
		System.out.println(HttpClientUtil.doPost(rx,
				map2, "UTF-8"));
		
	}
}