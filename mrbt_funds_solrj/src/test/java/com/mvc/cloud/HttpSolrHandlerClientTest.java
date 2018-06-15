package com.mvc.cloud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.Test;

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.utils.FastJsonUtils;

@SuppressWarnings("deprecation")
public class HttpSolrHandlerClientTest {
	protected CloudSolrClient client = null;
	private static final Logger log = LogManager
			.getLogger(HttpSolrHandlerClientTest.class);

	public HttpSolrHandlerClientTest() {
		log.info("Http Solr client constructor method");
	}

	public synchronized void finish() {
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				log.error("HttpSolr client close failure");
			}
		}
	}
	public synchronized void rollback() {
		if (client != null) {
			try {
				client.rollback();
			} catch (SolrServerException e) {
				log.error("HttpSolr client rollback-SolrServerExceptiom failure");
			} catch (IOException e) {
				log.error("HttpSolr client rollback-IOException failure");
			}

		}
	}
	//@Test
	public void test(){
		System.err.println("select start");
		try {
			ExecutorService executorService = Executors.newCachedThreadPool();
			for(int i = 0 ;i < 100 ;i++){
				Future<?> future = executorService.submit(new Runnable(){
					@Override
					public void run() {
						HttpSolrClient client =null;
						try{
							while(true){
								Thread.sleep(1000);
								client = new HttpSolrClient(AppInfo.SOLR_SERVER_URL);
								client.setConnectionTimeout(3000);
								client.setMaxTotalConnections(6000);
								client.setSoTimeout(5000);
								client.setUseMultiPartPost(true);
								client.setFollowRedirects(true);
								client.setAllowCompression(true);
								client.setDefaultMaxConnectionsPerHost(6000);
								ModifiableSolrParams queryParams = new ModifiableSolrParams();
							    queryParams.add("q", "market_fund_code:*");
							    SolrDocumentList solrDocumentList = client.query(queryParams).getResults();
							    DocumentObjectBinder binder = client.getBinder();
								List<FundMarketVo> markets = binder.getBeans(FundMarketVo.class, solrDocumentList);
								System.err.println(FastJsonUtils.list2Json(markets));
								client.close();
							}
						}catch(Exception e){
							log.info("查询出现异常");
							throw new RuntimeException(e);
						}finally{
						}
					}
				});
				
				try {
					Object obj = future.get();
					System.err.println(FastJsonUtils.Obj2Json(obj));
				} catch (InterruptedException | ExecutionException e) {
					log.info("解析JSON出现异常");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			log.info("ExecutorService出现异常");
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void mvc(){
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		while(true){
			Future<String> mvc = executorService.submit(new Callable<String>(){
				String url = "http://192.168.1.175:9999/mrbt_funds_solrj/market/list";
				Map<String,String> map = new HashMap<String,String>();
				@Override
				public String call() throws Exception {
					map.put("start","0");
					map.put("rows","40");
					return doPost(url,map, "UTF-8");
				}
			});
			String json = null;
			try {
				json = mvc.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println(json);
		}
	}
	
	@SuppressWarnings({ "resource", "rawtypes", "unchecked"})
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
}
