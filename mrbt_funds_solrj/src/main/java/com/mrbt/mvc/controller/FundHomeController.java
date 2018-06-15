package com.mrbt.mvc.controller;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.service.redis.FundNetValueRedisService;
import com.mrbt.mvc.service.sender.SenderServiceHandler;
import com.mrbt.mvc.service.service.market.FundMarketGenerateIndexService;
import com.mrbt.utils.DateUtil;

@Controller
@RequestMapping(value = "/mrbt")
public class FundHomeController {
	private Logger log = LogManager.getLogger(FundHomeController.class);
	@RequestMapping(value = "/index")
	public String home()
	{
		return "home";
	}
	
	@Autowired
	private FundMarketGenerateIndexService fundMarketGenerateIndexService;
	@Autowired
	@Qualifier(value = "senderServiceHandler")
	private SenderServiceHandler senderServiceHandler;
	@Autowired 
	private FundNetValueRedisService fundNetValueRedisService;
	
	@RequestMapping(value = "/init")
	public String  init()
	{
		String email = AppInfo.email;
		String now = DateUtil.getCurrentTimes(new Date());
		log.info("开始生成基金超市和基金详情索引:"+now);
		long start = System.currentTimeMillis();
		boolean result = fundMarketGenerateIndexService.generateFundMarketIndex();
		if(!result){
			senderServiceHandler.suggest("基金超市索引生成失败",email);
			log.info("基金超市索引生成失败");
		}
		else if(result){
			log.info("基金超市索引生成成功"+System.currentTimeMillis());
			senderServiceHandler.suggest("基金超市索引生成成功",email);
		}
		log.info("开始写入Redis净值");
		double redis = fundNetValueRedisService.saveAllFundMonthNetValueToRedis();
		if(redis < 0 || redis == 0){
			log.info("Redis写入基金净值数量为0,失败");
			senderServiceHandler.suggest("Redis写入基金净值失败",email);
		}
		else{
			log.info("Redis写入基金净值执行成功");
			senderServiceHandler.suggest("Redis写入基金净值成功",email);
		}
		long end = System.currentTimeMillis();
		System.out.println("耗费时间:("+(end-start)+")");
		return "home";
	}
}
