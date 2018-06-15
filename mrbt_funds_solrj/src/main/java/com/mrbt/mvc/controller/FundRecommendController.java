package com.mrbt.mvc.controller;

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
import com.mrbt.mvc.service.service.recommend.FundRecommendHandlerService;
import com.mrbt.utils.StringCustomerUtils;
/**
 * 推荐建议
 */
@Controller
@RequestMapping(value = "/recommend")
public class FundRecommendController {
	private static final Logger log = LogManager
			.getLogger(FundRecommendController.class);
	@Autowired
	private FundRecommendHandlerService fundRecommendHandlerService;

	public FundRecommendController() {
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String selectFoundMarketIndexList(HttpServletRequest request, HttpServletResponse response,@RequestParam("start") int start,
			@RequestParam("rows") int rows,@RequestParam("type") String type) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("进入优选基金查询list");
		System.out.println("进入优选基金查询list");
		if (start < 0 || rows < 0 || StringUtils.isEmpty(type)) {
			return AppInfo.FOUND_RECOMMEND_SELECT_MESSAGE_F;
		}
		String select = "";
		log.info("get request select ");
		try {
			select = fundRecommendHandlerService.select(start, rows,type);
			return select;
		} catch (Exception e) {
			log.error(e);
			//e.printStackTrace();
			return "";
		} finally {
			fundRecommendHandlerService.finish();
		}
	}	
	@RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String selectFoundMarketIndex(HttpServletRequest request, HttpServletResponse response,@RequestParam("start") int start,
			@RequestParam("rows") int rows,@RequestParam(value = "type",required = false) String type) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("进入优选基金查询get");
		System.out.println("进入优选基金查询get");
		if (start < 0 || rows < 0 ) {
			return AppInfo.FOUND_RECOMMEND_SELECT_MESSAGE_F;
		}
		if(type == null || StringCustomerUtils.isEmpty(type))type ="*";
		String select = "";
		log.info("get request select ");
		try {
			//select = fundRecommendHandlerService.select(start, rows);
			select = fundRecommendHandlerService.select(start, rows,type);
			return select;
		} catch (Exception e) {
			log.error(e);
			//e.printStackTrace();
			return "";
			//return AppInfo.FOUND_MARKET_SELECT_MESSAGE_F;
		} finally {
			fundRecommendHandlerService.finish();
		}
	}	
}
