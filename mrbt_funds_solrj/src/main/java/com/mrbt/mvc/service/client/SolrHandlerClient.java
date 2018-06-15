package com.mrbt.mvc.service.client;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.stereotype.Component;

import com.mrbt.config.AppInfo;

@Component
public abstract class SolrHandlerClient {

	/**
	 * 定义solrj客户端操作组件
	 */
	protected HttpSolrClient client = null;
	private static final Logger log = LogManager
			.getLogger(SolrHandlerClient.class);

	public SolrHandlerClient() {
		log.info("solrj client constructor method");
	}

	public void finish() {
		if (client != null) {
			try {
				//log.info("solr客户端关闭连接");
				client.close();
			} catch (IOException e) {
				log.error("HttpSolrClient close failure");
			}
		}
	}

	public void rollback() {
		if (client != null) {
			try {
				client.rollback();
			} catch (SolrServerException e) {
				log.error("HttpSolrClient rollback-SolrServerExceptiom failure");
			} catch (IOException e) {
				log.error("HttpSolrClient rollback-IOException failure");
			}

		}
	}

	public HttpSolrClient getSolrClientInstance() {
		//if (client == null) {
		synchronized (this) {
			client = new HttpSolrClient(AppInfo.SOLR_SERVER_URL);
			//org.apache.solr.client.solrj.SolrServerException: Timeout occured while waiting response from server
			client.setConnectionTimeout(3000);
			client.setMaxTotalConnections(6000);
			client.setSoTimeout(3000);
			client.setUseMultiPartPost(true);
			client.setFollowRedirects(true);
			client.setAllowCompression(true);
			client.setDefaultMaxConnectionsPerHost(100);//6000
		}
		return client;
	}
}
