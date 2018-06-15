package com.mrbt.mvc.service.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.mrbt.mvc.service.redis.FundNetValueRedisService;
import com.mrbt.mvc.service.sender.SenderServiceHandler;

/*****
 * 基金详情索引
 *
 */
public class FundNetValueIndexGenerateJob {
	@Autowired 
	private FundNetValueRedisService fundNetValueRedisService;
	@Autowired
	private SenderServiceHandler senderServiceHandler;
	public void work(){
		long start = System.currentTimeMillis();
		System.out.println("Redis基金净值写入");
		//double result = fundNetValueRedisService.saveAllFundNormalMonthNetValueToRedis();
		double result = fundNetValueRedisService.saveAllFundMonthNetValueToRedis();
		long end = System.currentTimeMillis();
		if(result < 0 || result == 0){
			System.out.println("Redis写入基金净值数量为0,失败");
			senderServiceHandler.suggest("Redis写入基金净值失败,耗时:("+(end-start)/1000+")");
		}
		else{
			System.out.println("Redis写入基金净值执行成功");
			senderServiceHandler.suggest("Redis写入基金净值成功,耗时:("+(end-start)/1000+")");
		}
	}
}
