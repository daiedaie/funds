package com.mrbt.mvc.service.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.mrbt.mvc.service.sender.SenderServiceHandler;
import com.mrbt.mvc.service.service.market.FundMarketGenerateIndexService;

/***
 * 定时任务:生成基金超市索引
 *
 */
public class FundMarketIndexGenerateJob {
	@Autowired
	private FundMarketGenerateIndexService fundMarketGenerateIndexService;
	@Autowired
	private SenderServiceHandler senderServiceHandler;
	public void work(){
		long start = System.currentTimeMillis();
		System.out.println("基金超市索引");
		boolean result = fundMarketGenerateIndexService.generateFundMarketIndex();
		long end = System.currentTimeMillis();
		if(!result){
			System.out.println("基金超市索引执行失败");
			senderServiceHandler.suggest("基金超市索引失败,耗时:("+(end-start)/1000+")");
		}
		else{
			System.out.println("基金超市索引执行成功");
			senderServiceHandler.suggest("基金超市索引成功,耗时:("+(end-start)/1000+")");
		}
	}
}
