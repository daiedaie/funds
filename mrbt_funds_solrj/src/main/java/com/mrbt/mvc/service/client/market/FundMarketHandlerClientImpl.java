package com.mrbt.mvc.service.client.market;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest.ACTION;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import com.mrbt.config.AppInfo;
import com.mrbt.config.ClientHandlerType;
import com.mrbt.mvc.service.client.SolrHandlerClient;
import com.mrbt.mvc.vo.FundMarketListVo;
import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.mvc.vo.SolrResponseVo;
import com.mrbt.utils.FastJsonUtils;
import com.mrbt.utils.StringCustomerUtils;

@Component(value = "fundMarketHandlerClient")
public class FundMarketHandlerClientImpl extends SolrHandlerClient implements
		FundMarketHandlerClient {
	private static final Logger log = LogManager
			.getLogger(FundMarketHandlerClientImpl.class);

	/***
	 * 基金全文检索
	 */
	@Override
	public String retrieve(String param, int start, int rows, String order)
			throws IOException, SolrServerException {
		try{
			SolrQuery select = new SolrQuery();
			select.setStart(start);
			select.setRows(rows);
			if (StringCustomerUtils.isNotEmpty(order)) {
				if (order.contains(":")) {
					String[] value = order.split(":");
					if (value == null || value.length == 0) {
						select.addSort("market_fund_code", ORDER.asc);
					} else {
						String orderValue = value[1];
						if (orderValue.equalsIgnoreCase("desc")) {
							select.addSort(value[0], ORDER.desc);
						} else {
							select.addSort(value[0], ORDER.asc);
						}
					}
				} else {
					select.addSort("market_fund_code", ORDER.asc);
				}
			} else {
				select.addSort("market_fund_code", ORDER.asc);
			}
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			select.setHighlight(true);
			select.setHighlightFragsize(120);
			StringBuffer sb = new StringBuffer();
			sb.append("market_fund_name:" + ("\"" + "*" + param + "*" + "\""))
					.append(" OR ")
					.append("market_fund_code:" + ("*" + param + "*"))
					.append(" OR ")
					.append("market_fund_shortname:"
							+ ("\"" + "*" + param + "*" + "\"")).append(" OR ")
					.append("market_fund_pinyin:" + ("*" + param + "*"));
			select.setQuery(sb.toString());
			select.set("indent", "true");
			SolrResponseVo<FundMarketListVo> solrResponseVo = new SolrResponseVo<FundMarketListVo>();
			QueryResponse response = client.query(select);
			client.close();
			if (response != null) {
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundMarketListVo> resultList = binder.getBeans(
							FundMarketListVo.class, solrDocumentLists);
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("基金超市列表检索出现异常"+e);
			return "";
		}
	}

	public void delete(String param, ClientHandlerType.DeteleType type)
			throws SolrServerException, IOException {
		if (StringUtils.isEmpty(param)) {
			return;
		}
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		// 根据名称删除
		if (type.getType().equalsIgnoreCase("name")) {
			client.deleteByQuery("market_fund_name:" + param);
		}
		// 根据代码简称删除
		else if (type.getType().equalsIgnoreCase("code")) {
			client.deleteByQuery("market_fund_code:" + param);
		}
		client.commit();
		client.optimize();
	}

	// 添加基金超市基础索引
	@Override
	public void add(FundMarketVo fundMarketVo) throws SolrServerException,
			IOException {
		if (fundMarketVo == null)
			return;
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		client.deleteByQuery("market_fund_code:"
				+ fundMarketVo.getMarket_fund_code());
		client.addBean(fundMarketVo);
		client.commit();
	}

	// 添加基金超市基础索引集合
	@Override
	public void addBeans(List<FundMarketVo> fundMarketVos)
			throws SolrServerException, IOException {
		if (fundMarketVos == null || fundMarketVos.size() == 0)
			return;
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		for (FundMarketVo fundMarketVo : fundMarketVos) {
			client.deleteByQuery("market_fund_code:"
					+ fundMarketVo.getMarket_fund_code());
			client.commit();
		}
		client.addBeans(fundMarketVos);
		client.commit();
	}

	public String select() {
		try{
			SolrQuery select = new SolrQuery("market_fund_code:*");
			select.setStart(0);
			select.setRows(10);
			select.addSort("market_fund_code", ORDER.asc);
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			client.close();
			SolrResponseVo<FundMarketListVo> solrResponseVo = new SolrResponseVo<FundMarketListVo>();
			if (response != null) {
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundMarketListVo> resultList = binder.getBeans(
							FundMarketListVo.class, solrDocumentLists);
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("查询索引出现异常"+e);
			return "";
		}
		
	}

	// 根据代码删除
	@Override
	public void deleteByCode(String fund_code) throws SolrServerException,
			IOException {
		delete(fund_code, ClientHandlerType.DeteleType.CODE);
	}

	// 根据基金简称删除
	@Override
	public void deleteByName(String fund_name) throws SolrServerException,
			IOException {
		delete(fund_name, ClientHandlerType.DeteleType.NAME);

	}

	// 查询所有数据
	@Override
	public String selectAll() throws IOException, SolrServerException {
		try{
			SolrQuery select = new SolrQuery("market_fund_code:"+AppInfo.ALL_TYPE);
			select.setStart(0);
			select.setRows(Integer.MAX_VALUE);
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			client.close();
			if (response != null && response.getResults() != null) {
				long queryCount = response.getResults().getNumFound();
				int numFound = new Long(queryCount).intValue();
				if (numFound > 0) {
					select.setRows(numFound);
					SolrResponseVo<FundMarketVo> solrResponseVo = new SolrResponseVo<FundMarketVo>();
					response = null;
					response = client.query(select);
					if (response != null) {
						log.info(response.getQTime() + ":");
						log.info(response.getQTime() + response.getStatus() + ":"
								+ response.getResults().getNumFound());
						solrResponseVo.setStatus(response.getStatus());
						SolrDocumentList solrDocumentLists = response.getResults();
						if (solrDocumentLists != null
								&& solrDocumentLists.size() > 0) {
							DocumentObjectBinder binder = new DocumentObjectBinder();
							List<FundMarketVo> resultList = binder.getBeans(
									FundMarketVo.class, solrDocumentLists);
							solrResponseVo.setIndexBean(resultList);
							solrResponseVo.setNumFound(solrDocumentLists
									.getNumFound());
						}
						return FastJsonUtils.Obj2Json(solrResponseVo);
					}
				}
				return "";
			}
			return "";
		}catch(Exception e){
			log.info("查询所有基金超市列表出现异常"+e);
			return "";
		}
	}

	
	/**
	 * 基金列表页面
	 */
	@Override
	public String selectMarketList(int start, int rows, String castsurely,
			String type, String scale, String company, String theme,
			String order) {
		try{
			// 默认查询
			StringBuffer stringBuffer = new StringBuffer("market_fund_code:"+AppInfo.ALL_TYPE);
			SolrQuery select = new SolrQuery();
			if (isNotAsterisk(scale)) {
				// 规模
				if (scale.contains("[") && scale.contains("]")) {
					stringBuffer.append(" AND market_fund_scale:" + scale);
				} else {
					stringBuffer.append(" AND market_fund_scale:" + "[" + scale
							+ "]");
				}
			}
			if (isNotAsterisk(company)) {
				// 公司加上""完全匹配
				stringBuffer.append(" AND market_fund_company:" + "\"" + company
						+ "\"");
			}
			if (isNotAsterisk(theme)) {
				// 主题
				stringBuffer
						.append(" AND market_fund_theme:" + "\"" + theme + "\"");
			}

			type = getType(type);
			if (isNotAsterisk(type)) {
				// 类型
				if(type.equals(AppInfo.OTHERS_TYPE)){
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.STOCK_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.HYBIRD_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.BOND_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.CURRENCY_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.CAPITALPRERVATION_TYPE+"\")");
				}
				else{
					stringBuffer.append(" AND market_fund_type:" + "\"" + type + "\"");
				}
			}
			castsurely = getCasturely(castsurely);
			if (isNotAsterisk(castsurely)) {
				// 定投的过滤
				select.addFilterQuery(castsurely);
			}
			select.setQuery(stringBuffer.toString());
			System.out.println(stringBuffer.toString());
			select.setStart(start);
			select.setRows(rows);
			select.set("ident", "true");
			if (StringUtils.isNotEmpty(order)) {
				if (order.contains(":")) {
					String[] value = order.split(":");
					if (value == null || value.length == 0) {
						select.addSort("market_fund_code", ORDER.asc);
					} else {
						String orderValue = value[1];
						if (orderValue.equalsIgnoreCase("desc")) {
							select.addSort(value[0], ORDER.desc);
						} else {
							select.addSort(value[0], ORDER.asc);
						}
					}
				} else {
					select.addSort("market_fund_code", ORDER.asc);
				}
			} else {
				// 默认按照基金代码升序
				select.addSort("market_fund_code", ORDER.asc);
			}
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			try {
				client.close();
			} catch (IOException e) {
				log.info("关闭Solr客户端连接出现异常"+e);
			}
			SolrResponseVo<FundMarketListVo> solrResponseVo = new SolrResponseVo<FundMarketListVo>();
			if (response != null) {
				
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundMarketListVo> resultList = binder.getBeans(
							FundMarketListVo.class, solrDocumentLists);
					System.out.println("查询的数量是-----" + resultList.size());
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("基金超市列表查询出现错误的异常"+e);
			return "";
		}
	}
	
	/**
	 * 基金列表页面
	 */
	@Override
	public List<FundMarketListVo> selectMarketReturnList(int start, int rows, String castsurely,
			String type, String scale, String company, String theme,
			String order)  {
		List<FundMarketListVo> resultList = new ArrayList<FundMarketListVo>();
		try{

			// 默认查询
			StringBuffer stringBuffer = new StringBuffer("market_fund_code:"+AppInfo.ALL_TYPE);
			SolrQuery select = new SolrQuery();
			if (isNotAsterisk(scale)) {
				// 规模
				if (scale.contains("[") && scale.contains("]")) {
					stringBuffer.append(" AND market_fund_scale:" + scale);
				} else {
					stringBuffer.append(" AND market_fund_scale:" + "[" + scale
							+ "]");
				}
			}
			if (isNotAsterisk(company)) {
				// 公司加上""完全匹配
				stringBuffer.append(" AND market_fund_company:" + "\"" + company
						+ "\"");
			}
			if (isNotAsterisk(theme)) {
				// 主题
				stringBuffer
						.append(" AND market_fund_theme:" + "\"" + theme + "\"");
			}

			type = getType(type);
			if (isNotAsterisk(type)) {
				if(type.equals(AppInfo.OTHERS_TYPE)){
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.STOCK_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.HYBIRD_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.BOND_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.CURRENCY_TYPE+"\")");
					select.addFilterQuery("market_fund_type:(NOT \""+AppInfo.CAPITALPRERVATION_TYPE+"\")");
				}
				else{
					stringBuffer.append(" AND market_fund_type:" + "\"" + type + "\"");
				}
				
			}
			castsurely = getCasturely(castsurely);
			if (isNotAsterisk(castsurely)) {
				// 定投
				select.addFilterQuery(castsurely);
			}
			select.setQuery(stringBuffer.toString());
			System.out.println(stringBuffer.toString());
			select.setStart(start);
			select.setRows(rows);
			select.set("ident", "true");
			if (StringUtils.isNotEmpty(order)) {
				if (order.contains(":")) {
					String[] value = order.split(":");
					if (value == null || value.length == 0) {
						select.addSort("market_fund_code", ORDER.asc);
					} else {
						String orderValue = value[1];
						if (orderValue.equalsIgnoreCase("desc")) {
							select.addSort(value[0], ORDER.desc);
						} else {
							select.addSort(value[0], ORDER.asc);
						}
					}
				} else {
					select.addSort("market_fund_code", ORDER.asc);
				}
			} else {
				// 默认按照基金代码升序
				select.addSort("market_fund_code", ORDER.asc);
			}
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			client.close();
			if (response != null) {
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					resultList = binder.getBeans(
							FundMarketListVo.class, solrDocumentLists);
				}
			}
			return resultList;
		}catch(Exception e){
			log.info("基金列表查询索引出现异常");
			return resultList;
		}
	}

	@Override
	public String selectMarketSearchList(int start, int rows, String namecode,
			String order) {
		try{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("market_fund_code:" + namecode).append(
					" market_fund_name:" + namecode);
			SolrQuery select = new SolrQuery(stringBuffer.toString());
			select.setStart(start);
			select.setRows(rows);
			// order排序
			select.addSort("market_fund_code", ORDER.asc);
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			client.close();
			SolrResponseVo<FundMarketListVo> solrResponseVo = new SolrResponseVo<FundMarketListVo>();
			if (response != null) {
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundMarketListVo> resultList = binder.getBeans(
							FundMarketListVo.class, solrDocumentLists);
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("selectMarketSearchList方法出现异常"+e);
			return "";
		}
		
	}
	private String getType(String type) {
		if (type.equals(AppInfo.STOCK_TYPE_NUM)) {
			type = AppInfo.STOCK_TYPE;
		} else if (type.equals(AppInfo.BOND_TYPE_NUM)) {
			type = AppInfo.BOND_TYPE;
		} else if (type.equals(AppInfo.HYBIRD_TYPE_NUM)) {
			type = AppInfo.HYBIRD_TYPE;
		} else if (type.equals(AppInfo.CURRENCY_TYPE_NUM)) {
			type = AppInfo.CURRENCY_TYPE;
		} else if (type.equals(AppInfo.INDEX_TYPE_NUM)) {
			type = AppInfo.INDEX_TYPE;
		} else if (type.equals(AppInfo.QDII_TYPE_NUM)) {
			type = AppInfo.QDII_TYPE;
		} else if (type.equals(AppInfo.FUND_TYPE_NUM)) {
			type = AppInfo.FUND_TYPE;
		} else if (type.equals(AppInfo.CAPITALPRERVATION_TYPE_NUM)) {
			type = AppInfo.CAPITALPRERVATION_TYPE;
		} else if (type.equals(AppInfo.INNOVATION_TYPE_NUM)) {
			type = AppInfo.INNOVATION_TYPE;
		} else if(type.equals(AppInfo.OTHERS_TYPE)){
			type = AppInfo.OTHERS_TYPE;
		}
		else {
			type = AppInfo.ALL_TYPE;
		}
		return type;
	}

	/***
	 * 拼接定投或定投的语法
	 * @param castsurely
	 * @return
	 */
	private String getCasturely(String castsurely) {
		StringBuffer str = new StringBuffer();
		if (castsurely.equals("0")) {
			// 非定投
			// castsurely = "(NOT 5)";
			str.append("market_fund_castsurely:(NOT 5)");
		} else if (castsurely.equals(AppInfo.FUND_ISCASTSURELY)) {
			// 定投
			str.append("market_fund_castsurely:" + AppInfo.CASTSURELYCASE);
		} else {
			// 全部
			str.append("*");
		}
		return str.toString();
	}

	/**
	 * 判断是否全部查询
	 * @param asterisk
	 * @return
	 */
	private boolean isNotAsterisk(String asterisk) {
		if (asterisk == null || asterisk.equals(AppInfo.ALL_TYPE) || asterisk.length() == 0
				|| asterisk.equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}

	/***
	 * 根据基金代码更新部分索引值
	 * 
	 * @param <K>
	 */
	@Override
	public <K, V> boolean update(String fundcode, Map<K, V> param) {
		if (StringCustomerUtils.isEmpty(fundcode)
				|| StringCustomerUtils.isNotNumber(fundcode)
				|| StringCustomerUtils.isEmptyMap(param)) {
			return false;
		}
		try {
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			SolrQuery query = new SolrQuery("market_fund_code:" + fundcode);
			query.set("ident", "true");
			QueryResponse response;
			response = client.query(query);
			if (response != null) {
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					Iterator<SolrDocument> docs = solrDocumentLists.iterator();
					while (docs.hasNext()) {
						SolrDocument sd = docs.next();
						if (sd != null) {
							if (!sd.get("market_fund_code").equals(fundcode)) {
								log.info("market_fund_code--" + fundcode
										+ "不存在,更新失败");
								return false;
							} else {
								DocumentObjectBinder binder = new DocumentObjectBinder();
								List<FundMarketVo> resultList = binder
										.getBeans(FundMarketVo.class,
												solrDocumentLists);
								if (CollectionUtils.isEmpty(resultList)) {
									break;
								}
								FundMarketVo updateFundMarketVo = resultList
										.get(0);
								if (updateFundMarketVo == null) {
									break;
								}
								client.deleteByQuery("market_fund_code:"
										+ updateFundMarketVo
												.getMarket_fund_code());
								client.commit();
								Class<FundMarketVo> t = FundMarketVo.class;
								java.lang.reflect.Field[] fields = t
										.getDeclaredFields();
								for (java.lang.reflect.Field field : fields) {
									String field_name = field.getName();
									if ("serialVersionUID".equals(field_name))
										continue;
									for (Map.Entry<?, ?> entry : param
											.entrySet()) {
										if (entry == null
												|| StringCustomerUtils
														.isEmpty(entry.getKey()
																.toString())
												|| StringCustomerUtils
														.isEmpty(entry
																.getValue()
																.toString()))
											continue;
										if (field_name.equals(entry.getKey())) {
											field.setAccessible(true);
											field.set(updateFundMarketVo,
													entry.getValue());
										}
									}
								}
								SolrInputDocument solrDoc = binder
										.toSolrInputDocument(updateFundMarketVo);
								UpdateRequest updateRequest = new UpdateRequest();
								updateRequest.setAction(ACTION.COMMIT, true,
										true);
								updateRequest.add(solrDoc);
								Object obj = updateRequest.process(client);
								client.commit();
								client.optimize();
								log.info(obj.toString());
								return true;
							}
						}
					}
				}
			}
			return false;
		} catch (Exception e) {
			try {
				client.rollback();
				client.commit();
				return false;
			} catch (SolrServerException | IOException e1) {
				log.error(e1);
			}
			log.error(e);
			return false;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
	}

	@Override
	public String getMarketListByCode(String id) throws IOException,
			SolrServerException {
		try{
			SolrQuery select = new SolrQuery("market_fund_code:" + id);
			select.addSort("market_fund_code", ORDER.asc);
			if (client == null)
				getSolrClientInstance();
			else {
				client.close();
				getSolrClientInstance();
			}
			QueryResponse response = client.query(select);
			SolrResponseVo<FundMarketListVo> solrResponseVo = new SolrResponseVo<FundMarketListVo>();
			if (response != null) {
				log.info(response.getQTime() + ":");
				log.info(response.getQTime() + response.getStatus() + ":"
						+ response.getResults().getNumFound());
				solrResponseVo.setStatus(response.getStatus());
				SolrDocumentList solrDocumentLists = response.getResults();
				if (solrDocumentLists != null && solrDocumentLists.size() > 0) {
					DocumentObjectBinder binder = new DocumentObjectBinder();
					List<FundMarketListVo> resultList = binder.getBeans(
							FundMarketListVo.class, solrDocumentLists);
					System.out.println("查询的数量是-----" + resultList.size());
					solrResponseVo.setIndexBean(resultList);
					solrResponseVo.setNumFound(solrDocumentLists.getNumFound());
				}
				return FastJsonUtils.Obj2Json(solrResponseVo);
			}
			return "";
		}catch(Exception e){
			log.info("基金超市根据代码查询出现异常"+e);
			return "";
		}
		
	}

	/***
	 * 删除索引
	 */
	@Override
	public boolean deleteAllIndex() throws SolrServerException, IOException {
		if (client == null)
			getSolrClientInstance();
		else {
			client.close();
			getSolrClientInstance();
		}
		client.deleteByQuery("market_fund_code:*");
		client.commit();
		return true;
	}
}
