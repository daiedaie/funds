package com.mrbt.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
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

public class HttpClientUtil {
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

	public static void main(String[] args) throws Exception {
		HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
		Map<String, String> map = new HashMap<String, String>();
		//map.put("FUNDS_CODE_OLD", "KFC006");
		map.put("FUNDS_CODE", "KFGC");
		map.put("CREATE_TIME", "2016-06-22 03:00:01");
		map.put("IS_RECOMM", "0");
		map.put("RECOMM_ORDER", "999");
		String url = "http://localhost:9999/mrbt_funds_solrj/foundmarket/add";
		String result = post.doPost(url, map, "UTF-8");
		System.out.println(result);
		
		//String select = "http://localhost:9999/mrbt_funds_solrj/foundmarket/select/KFGC";
		//添加 http://192.168.1.175:9999/mrbt_funds_solrj/foundmarket/add
		//更新 http://192.168.1.175:9999/mrbt_funds_solrj/foundmarket/update
		//查询 http://192.168.1.175:9999/mrbt_funds_solrj/foundmarket/select/KFGC
		//删除 http://192.168.1.175:9999/mrbt_funds_solrj/foundmarket/delete/KFGC
		
	}
}