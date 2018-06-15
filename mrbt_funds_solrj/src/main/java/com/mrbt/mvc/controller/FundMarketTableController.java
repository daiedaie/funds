package com.mrbt.mvc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.service.redis.FundNetValueRedisService;
import com.mrbt.mvc.service.service.market.FundMarketGenerateIndexService;
import com.mrbt.mvc.service.service.markettable.FundMarketTableHandlerService;
import com.mrbt.utils.DateUtil;

@Controller
@RequestMapping(value = "/markettable")
public class FundMarketTableController {
	private static final Logger log = LogManager
			.getLogger(FundMarketTableController.class);
	@Autowired
	private FundMarketTableHandlerService fundMarketTableHandlerService;

	public FundMarketTableController() {
	}

	/**
	 * 基金下线、删除索引
	 * @param request
	 * @param response
	 * @param funds_code
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteFoundMarketIndex(HttpServletRequest request, HttpServletResponse response,@RequestParam("funds_code") String funds_code) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		if (StringUtils.isEmpty(funds_code)) {
			return AppInfo.FOUND_MARKET_DELETE_MESSAGE_F;
		}
		log.info("delete foundmarket index" + funds_code);
		try {
			fundMarketTableHandlerService.delete(funds_code);
		} catch (Exception e) {
			log.error(e);
			fundMarketTableHandlerService.rollback();
			return AppInfo.FOUND_MARKET_DELETE_MESSAGE_F;
		} finally {
			fundMarketTableHandlerService.finish();
		}
		return AppInfo.FOUND_MARKET_DELETE_MESSAGE_T;
	}
	
	/****
	 * 后台调用，生成索引的接口
	 * 计算涨幅、净值
	 * Redis净值写入操作
	 * @param request
	 * @param response
	 * @param FUNDS_CODE
	 * @param CREATE_TIME
	 * @param IS_RECOMM
	 * @param RECOMM_ORDER
	 * @param FUNDS_CODE_INNER
	 * @param RECOMM_REASON
	 * @param FUNDS_THEME
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateFoundMarketIndex(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("funds_code") String FUNDS_CODE,
			@RequestParam("create_time") String CREATE_TIME,
			@RequestParam("is_recomm") String IS_RECOMM,
			@RequestParam("recomm_order") String RECOMM_ORDER,
			@RequestParam("funds_code_inner") String FUNDS_CODE_INNER,
			@RequestParam("recomm_reason") String RECOMM_REASON,
			@RequestParam("funds_theme") String FUNDS_THEME) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("/markettable/add/updateFundMarketIndex");
		if (StringUtils.isEmpty(FUNDS_CODE) || StringUtils.isEmpty(CREATE_TIME)
				|| StringUtils.isEmpty(IS_RECOMM)
				|| StringUtils.isEmpty(RECOMM_ORDER)
				|| StringUtils.isEmpty(FUNDS_CODE_INNER)
				|| StringUtils.isEmpty(RECOMM_REASON)
				|| StringUtils.isEmpty(FUNDS_THEME)) {
			return AppInfo.FOUND_MARKET_UPDATE_MESSAGE_F;
		}
		log.info("update foundmarket index");
		try {
			return fundMarketTableHandlerService.update(FUNDS_CODE, CREATE_TIME,
					IS_RECOMM, RECOMM_ORDER,FUNDS_CODE_INNER,RECOMM_REASON,FUNDS_THEME);
		} catch (Exception e) {
			log.error(e);
			fundMarketTableHandlerService.rollback();
			return AppInfo.FOUND_MARKET_UPDATE_MESSAGE_F;
		} finally {
			fundMarketTableHandlerService.finish();
		}
	}
	/****
	 * 后台调用，生成索引的接口
	 * 计算涨幅、净值
	 * Redis净值写入操作
	 * 后台添加基金索引的代码
	 * 后台调用
	 * @param request
	 * @param response
	 * @param FUNDS_CODE
	 * @param CREATE_TIME
	 * @param IS_RECOMM
	 * @param RECOMM_ORDER
	 * @param FUNDS_CODE_INNER
	 * @param RECOMM_REASON
	 * @param FUNDS_THEME
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addFoundMarketIndex(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("funds_code") String FUNDS_CODE,
			@RequestParam("create_time") String CREATE_TIME,
			@RequestParam("is_recomm") String IS_RECOMM,
			@RequestParam("recomm_order") String RECOMM_ORDER,
			@RequestParam("funds_code_inner") String FUNDS_CODE_INNER,
			@RequestParam("recomm_reason") String RECOMM_REASON,
			@RequestParam("funds_theme") String FUNDS_THEME) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("后台添加基金超市的索引");
		if (StringUtils.isEmpty(FUNDS_CODE) || StringUtils.isEmpty(CREATE_TIME)
				|| StringUtils.isEmpty(IS_RECOMM)
				|| StringUtils.isEmpty(RECOMM_ORDER)
				|| StringUtils.isEmpty(FUNDS_CODE_INNER)
				//|| StringUtils.isEmpty(RECOMM_REASON)
				//|| StringUtils.isEmpty(FUNDS_THEME)
				) {
			log.info("7个传递参数不能为空");
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("参数打印:").append(FUNDS_CODE).append(CREATE_TIME).
			append(IS_RECOMM).append(RECOMM_ORDER).append(FUNDS_CODE_INNER).
			append(RECOMM_REASON).append(FUNDS_THEME);
			log.info(stringBuffer.toString());
			return AppInfo.FOUND_MARKET_ADD_MESSAGE_F;
		}
		
		try {
			boolean result =  fundMarketTableHandlerService.add(FUNDS_CODE, CREATE_TIME,
					IS_RECOMM, RECOMM_ORDER,FUNDS_CODE_INNER,RECOMM_REASON,FUNDS_THEME);
			if(result){
				return AppInfo.FOUND_MARKET_ADD_MESSAGE_T;
			}
			else{
				return AppInfo.FOUND_MARKET_ADD_MESSAGE_F;
			}
		} catch (Exception e) {
			log.error(e);
			fundMarketTableHandlerService.rollback();
			return AppInfo.FOUND_MARKET_UPDATE_MESSAGE_F;
		} finally {
			fundMarketTableHandlerService.finish();
		}
	}
	
	/***
	 * 重新生成索引文件
	 */
	@Autowired
	private FundMarketGenerateIndexService fundMarketGenerateIndexService;
	@Autowired 
	private FundNetValueRedisService fundNetValueRedisService;
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String foundMarketIndexGenerateRepeat(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		String now = DateUtil.getCurrentTimes(new Date());
		log.info(now+"重新生成索引文件");
		System.out.println(now+"重新生成索引文件");
		try {
			log.info("开始生成基金列表的索引");
			boolean result = fundMarketGenerateIndexService.generateFundMarketIndex();
			if(!result){
				log.info("生成基金列表索引失败");
				return "基金超市列表索引写入失败";
			}
			log.info("开始写入Redis净值");
			double redis = fundNetValueRedisService.saveAllFundMonthNetValueToRedis();
			if(redis < 0 || redis == 0){
				log.info("Redis写入基金净值数量为0,失败");
				return "Redis净值写入失败";
			}
			return AppInfo.FOUND_MARKET_ADD_MESSAGE_T;
		} catch (Exception e) {
			log.error(e);
			fundMarketTableHandlerService.rollback();
			return AppInfo.FOUND_MARKET_UPDATE_MESSAGE_F;
		} finally {
			fundMarketTableHandlerService.finish();
		}
	}
	/***
	 * 删除索引
	 */
	@RequestMapping(value = "/rebuild", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String foundMarketIndexReBuild(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		String now = DateUtil.getCurrentTimes(new Date());
		log.info(now+"删除所有的索引");
		System.out.println(now+"删除所有的索引");
		try {
			log.info("开始删除基金列表的所有索引");
			boolean result = fundMarketTableHandlerService.deleteAllIndex();
			if(!result){
				log.info("删除所有的索引失败");
				return AppInfo.FOUND_MARKET_DELETE_MESSAGE_F;
			}
			return AppInfo.FOUND_MARKET_DELETE_MESSAGE_T;
		} catch (Exception e) {
			log.error(e);
			fundMarketTableHandlerService.rollback();
			return AppInfo.FOUND_MARKET_DELETE_MESSAGE_F;
		} finally {
			fundMarketTableHandlerService.finish();
		}
	}
	
}
