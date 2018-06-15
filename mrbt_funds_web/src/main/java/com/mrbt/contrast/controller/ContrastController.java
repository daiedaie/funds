package com.mrbt.contrast.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mrbt.contrast.service.ContrastService;
import com.mrbt.details.controller.FundsDetailsController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 基金对比
 * @author yulong
 *
 */
@Controller
@RequestMapping("/contrast")
public class ContrastController {
	
	private Logger logger = Logger.getLogger(FundsDetailsController.class);
	
	@Resource
	private ContrastService contrastService;
	
	@RequestMapping( value = "/view")
	public String toIndex(HttpServletRequest request,Model model){
		if(request.getParameter("ids") != null && !request.getParameter("ids").equals("")){
			String ids = request.getParameter("ids");
			model.addAttribute("ids", ids);
		}
		return "contrast";
	}
	
	/**
	 * 获取基金公司与基金联动
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFoudCompany",method=RequestMethod.POST)
	public @ResponseBody Object getFoudCompany(HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		//查询基金公司
		JSONObject company = contrastService.getFoudCompany();
		
		return company;
	}
	
	/**
	 * 获取基金列表
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFundsListFromIds",method=RequestMethod.POST)
	public @ResponseBody Object getFundsListFromIds(String ids, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		logger.info("ids:" + ids);
		
		JSONArray jsona = contrastService.getFundsListFromIds(ids);
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
	 * 基金对比全文搜索
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/contrasFullTextSearch",method=RequestMethod.POST)
	public @ResponseBody Object contrasFullTextSearch(String param, HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resJson = new JSONObject();
		
		logger.info("param:" + param);
		
		JSONArray jsona = contrastService.contrasFullTextSearch(param);
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
	
}
