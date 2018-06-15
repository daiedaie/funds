package com.mvc.cloud;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.Test;

import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.utils.FastJsonUtils;

public class SolrCloudHandlerClient {
	private final static String SolrCloudURL = "http://192.168.1.223:8080/solr/core";
	protected CloudSolrClient client = null;
	private static final Logger log = LogManager
			.getLogger(SolrCloudHandlerClient.class);

	public SolrCloudHandlerClient() {
		log.info("solrj cloud client constructor method");
	}

	public synchronized void finish() {
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				log.error("CloudSolrClient close failure");
			}
		}
	}

	public synchronized void rollback() {
		if (client != null) {
			try {
				
				client.rollback();
			} catch (SolrServerException e) {
				log.error("CloudSolrClient rollback-SolrServerExceptiom failure");
			} catch (IOException e) {
				log.error("CloudSolrClient rollback-IOException failure");
			}

		}
	}

	public CloudSolrClient getSolrCloudClientInstance() {
		synchronized (this) {
			//不能写http://
			client = new CloudSolrClient("192.168.1.223:2181", true);
		    client.setParallelUpdates(true);
		    client.setDefaultCollection("core");
		    client.getLbClient().setConnectionTimeout(30000);
		    client.getLbClient().setSoTimeout(60000);
		}
		return client;
	}
	/***
	 * 测试solrcloud
	 * 
	 */
	private static class MyTask implements Runnable {
		private ReentrantLock lock;
		public MyTask(ReentrantLock lock){
			this.lock = lock;
		}
		public MyTask(){
		}
		@Override
		public void run() {
			//lock.lock();
			CloudSolrClient client =null;
			try{
				while(true){
					Thread.sleep(1000);
					client = new CloudSolrClient("192.168.1.223:2181", true);
				    client.setParallelUpdates(true);
				    client.setDefaultCollection("core");
				    client.getLbClient().setConnectionTimeout(30000);
				    client.getLbClient().setSoTimeout(60000);
					ModifiableSolrParams queryParams = new ModifiableSolrParams();
				    queryParams.add("q", "market_fund_code:*");
				    SolrDocumentList solrDocumentList = client.query(queryParams).getResults();
				    DocumentObjectBinder binder = client.getBinder();
					List<FundMarketVo> markets = binder.getBeans(FundMarketVo.class, solrDocumentList);
					System.err.println(FastJsonUtils.list2Json(markets));
					client.close();
				}
			}catch(Exception e){
				log.info("出现异常");
				throw new RuntimeException(e);
			}finally{
				//lock.unlock();
			}
		}
	}
	@Test
	public void test(){
		System.err.println("select start");
		try {
			ExecutorService executorService = Executors.newCachedThreadPool();
			for(int i = 0 ;i < 100 ;i++){
				Future<?> future = executorService.submit(new Runnable(){
					@Override
					public void run() {
						CloudSolrClient client =null;
						try{
							while(true){
								Thread.sleep(1000);
								client = new CloudSolrClient("192.168.1.223:2181", true);
							    client.setParallelUpdates(true);
							    client.setDefaultCollection("core");
							    client.getLbClient().setConnectionTimeout(30000);
							    client.getLbClient().setSoTimeout(60000);
								ModifiableSolrParams queryParams = new ModifiableSolrParams();
							    queryParams.add("q", "market_fund_code:*");
							    SolrDocumentList solrDocumentList = client.query(queryParams).getResults();
							    DocumentObjectBinder binder = client.getBinder();
								List<FundMarketVo> markets = binder.getBeans(FundMarketVo.class, solrDocumentList);
								System.err.println(FastJsonUtils.list2Json(markets));
								client.close();
							}
						}catch(Exception e){
							log.info("出现异常");
							throw new RuntimeException(e);
						}finally{
						}
					}
				});
				try {
					Object obj = future.get();
					System.err.println(FastJsonUtils.Obj2Json(obj));
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			log.info("出现异常");
			throw new RuntimeException(e);
		}
	}
}
