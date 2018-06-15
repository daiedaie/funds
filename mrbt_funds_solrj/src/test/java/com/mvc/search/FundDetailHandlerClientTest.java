package com.mvc.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

@SuppressWarnings("deprecation")
public class FundDetailHandlerClientTest {
	public static final Logger log = LogManager.getLogger(FundDetailHandlerClientTest.class);

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void recommend() {
		FundDetailHandlerClientTest post = new FundDetailHandlerClientTest();
		//String url = "http://192.168.1.88:9999/mrbt_funds_solrj/marketvo/list";
		//String url = "http://localhost:9999/mrbt_funds_solrj/detail/get";
		String url = "http://localhost:9999/mrbt_funds_solrj/detail/get";
		Map map = new HashMap();
		map.put("id","000055");
		String result = post.doPost(url,map, "UTF-8");
		System.out.println(result);
	}

}