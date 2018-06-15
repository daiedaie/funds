package com.mrbt.details.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mrbt.details.service.FundsDetailsService;
import com.mrbt.units.DateUnits;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 基金详情
 * @author yulong
 *
 */

@Controller
@RequestMapping("/details")
public class FundsDetailsController {
	
	private Logger logger = Logger.getLogger(FundsDetailsController.class);
	
	@Resource
	private FundsDetailsService fundsDetailsService;
	
	/**
	 * 初始化页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/view")
	public String toIndex(HttpServletRequest request,Model model){
		try {
			String id = request.getParameter("id");
			JSONObject details = fundsDetailsService.getBaseInfo(id);
			
			logger.info("通过基金代码查询基金详情:" + id + "@" + details);
			if(details != null){
				model.addAttribute("type", details.getString("type"));
				model.addAttribute("typeName", details.getString("type_name"));
				model.addAttribute("shortName", details.getString("shortName"));
				model.addAttribute("state", details.getString("state"));
				model.addAttribute("code", details.getString("code"));
				model.addAttribute("scale", details.getString("scale"));
				model.addAttribute("upDate", DateUnits.strToDate(details.getString("upDate")));
				model.addAttribute("netValue", details.getString("netValue"));
				model.addAttribute("navTime", DateUnits.strToDate(details.getString("navTime")));
				model.addAttribute("totalTalue", details.getString("totalTalue"));
				model.addAttribute("totalTime", DateUnits.strToDate(details.getString("upDate")));
				model.addAttribute("ratez", details.getString("ratez"));
				model.addAttribute("rate", details.getString("rate"));
				model.addAttribute("fullName", details.getString("fullName"));
				model.addAttribute("fundManager", details.getString("fundManager"));
				model.addAttribute("custodian", details.getString("custodian"));
				if(details.getString("issueDate").equals("")){
					model.addAttribute("issueDate", "--");
				}else{
					model.addAttribute("issueDate", DateUnits.strToDate(details.getString("issueDate")));
				}
				model.addAttribute("perComBen", details.getString("perComBen"));
				model.addAttribute("risk", details.getString("risk"));
				model.addAttribute("dividendMethod", "现金分红");
				model.addAttribute("chargeMode", "前端收费");
				
				//查询基金经理姓名
				String fundManagers = fundsDetailsService.findFundManagersName(details.getString("f001_ths035"));
				model.addAttribute("fundManagers", fundManagers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "details";
	}
	
	
	/**
	 * 通过基金代码查询基金净值
	 * @param id 基金代码
	 * @param interval 区间 一个月：1，6个月：2，一年：3，3年：4，最大：5
	 * @param 曲线标签类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getNetValueChart",method=RequestMethod.POST)
	public @ResponseBody Object getNetValue(String id, int interval, int type, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		logger.info("id:" + id + "\tinterval:" + interval + "\ttype:" + type);
		
		JSONObject json = fundsDetailsService.getNetValueChart(id, interval, type);
		if(json != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", json);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
	
	/**
	 * 通过基金代码查询到行业配置
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/industryAlloca",method=RequestMethod.POST)
	public @ResponseBody Object getIndustryAlloca(String id, String year, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		JSONObject json = fundsDetailsService.getIndustrAlloca(id, year);
		if(json != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", json);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
	
	/**
	 * 查询持仓数据 数据来源：同花顺接口
	 * @param id
	 * @param year
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFundPositions",method=RequestMethod.POST)
	public @ResponseBody Object getFundPositions(String id, String year, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		JSONObject json = fundsDetailsService.getFundPositions(id, year);
		if(json != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", json);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
	
	/**
	 * 行业配置比较      数据来源：同花顺接口
	 * @param id
	 * @param year
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/industryAllocaCompare",method=RequestMethod.POST)
	public @ResponseBody Object getIndustryAllocaCompare(String id, String year, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		JSONObject json = fundsDetailsService.getIndustryAllocaCompare(id);
		if(json != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", json);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
	
	/**
	 * 查询基金经理信息   数据是假的，查询出来的基金经理，不是改基金的基金经理
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/fundsManagersInfo",method=RequestMethod.POST)
	public @ResponseBody Object getFundsManagersInfo(String id, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		JSONArray jsona = fundsDetailsService.getFundsManagersInfo(id);
		if(jsona != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", jsona);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
	
	/**
	 * 同类基金对比   数据来源：同花顺接口
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/similarFundsCompar",method=RequestMethod.POST)
	public @ResponseBody Object getSimilarFundsCompar(String id, int target, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		JSONArray jsona = fundsDetailsService.getFundsManagersInfo(id);
		if(jsona != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", jsona);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
	
	/**
	 * 基金评价        特色数据来源：同花顺接口数据
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFundEvaluation",method=RequestMethod.POST)
	public @ResponseBody Object getFundEvaluation(String id, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		JSONArray jsona = fundsDetailsService.getFundEvaluation(id);
		if(jsona != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", jsona);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
	
	/**
	 * 报告期规模变动
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getReportingPeriod",method=RequestMethod.POST)
	public @ResponseBody Object getReportingPeriod(String id, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		JSONObject json = fundsDetailsService.getReportingPeriod(id);
		if(json != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", json);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
	
	/**
	 * 报告期规模变动
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getRateInfo",method=RequestMethod.POST)
	public @ResponseBody Object getRateInfo(String id, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		JSONObject json = fundsDetailsService.getRateInfo(id);
		if(json != null){
			resJson.put("code", 200);
			resJson.put("msg", "success");
			resJson.put("data", json);
		}else{
			resJson.put("code", "207");
			resJson.put("msg", "no success");
		}
		
		return resJson;
	}
}
