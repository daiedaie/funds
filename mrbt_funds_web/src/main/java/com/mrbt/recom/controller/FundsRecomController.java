package com.mrbt.recom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.recom.service.FundsRecomService;

/**
 * @author yiban sun
 * @date 2016年6月23日 上午11:31:56
 * @version 1.0
 * @description 基金推荐
 * @since
 **/
@Controller
@RequestMapping("/recom")
public class FundsRecomController {

	private Logger logger = Logger.getLogger(FundsRecomController.class);
	
	@Resource
	private FundsRecomService fundsRecomService;

	@RequestMapping("/view")
	public String toIndex(HttpServletRequest request, Model model) {

		return "recommend";
	}

	@RequestMapping(value = "/getFirstThreeRecomFunds", method = RequestMethod.POST)
	public @ResponseBody Object getFirstThreeRecommFunds(HttpServletResponse response,String start, String rows, String pUrlName) {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("start", start);
		paramsMap.put("rows", rows);
		JSONObject json = fundsRecomService.getFirstThreeRecomFunds(paramsMap, pUrlName);
		
		logger.info("getFirstThreeRecomFunds----------->json: "+json);
		
		if (null != json) {
			return json;
		}
		return "null";
	}

	/**
	 * @description 获取推荐基金的列表
	 * 
	 * @param start
	 * @param rows
	 * @param pUrlName
	 * @return
	 */
	@RequestMapping(value = "/getRecomFundsList", method = RequestMethod.POST)
	public @ResponseBody Object getRecomFundsList(String start, String rows, String pUrlName, String funds_types) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		//股票型       债券型       混合型       货币型        保本型      其他
		//股票型 0   ，债券型1 ，混合型2，货币型3，指数型4，QDII5，基金型6，保本型7 ，创新型8 ，不传递参数查询全部
		paramsMap.put("start", start);
		paramsMap.put("rows", rows);
		paramsMap.put("type", funds_types);
		JSONObject json = fundsRecomService.getFirstThreeRecomFunds(paramsMap, pUrlName);
		if (null != json) {
			return json;
		}
		return "null";
	}

	/**
	 * @description 获取上期调出列表(luo)
	 * 
	 * @param start
	 * @param rows
	 * @param pUrlName
	 */
	@RequestMapping(value = "/queryPriorPeriodCalloutList", method = RequestMethod.POST)
	public @ResponseBody Object querypriorPeriodCalloutList(String start, String rows, String pUrlName) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("start", start);
		paramsMap.put("rows", rows);

		JSONObject json = new JSONObject();
		JSONArray js = fundsRecomService.queryPriorPeriodCalloutList();
		if (js != null) {
			json.put("code", 200);
			json.put("msg", "success");
			json.put("data", js);
		} else {
			json.put("code", "207");
			json.put("msg", "no success");
		}

		return json;
	}

	/**
	 * @description 获取本期调入列表(luo)
	 * 
	 * @param start
	 * @param rows
	 * @param pUrlName
	 * @return
	 */
	@RequestMapping(value = "/queryCurrentTransferredList", method = RequestMethod.POST)
	public @ResponseBody Object queryCurrentTransferredList(String start, String rows, String pUrlName) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("start", start);
		paramsMap.put("rows", rows);

		JSONObject json = new JSONObject();

		JSONArray js = fundsRecomService.queryCurrentTransferredList();

		if (js != null) {
			json.put("code", 200);
			json.put("msg", "success");
			json.put("data", js);
		} else {
			json.put("code", "207");
			json.put("msg", "no success");
		}

		return json;
	}

}
