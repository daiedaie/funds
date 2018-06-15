package com.mrbt.combination.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.combination.service.CombinationService;

/**
 *@author yiban sun
 *@date 2016年7月11日 下午2:50:36
 *@version 1.0
 *@description 组合宝控制器
 *@since
 **/
@Controller
@RequestMapping("/combination")
public class CombinationController {
	
	private Logger logger = Logger.getLogger(CombinationController.class);
	
	@Autowired
	private CombinationService combinationService;
	
	@RequestMapping("/view")
	public String view(){
		return "combination";
	}
	
	/**
	 * 查询组合宝列表
	 *@Description  从数据库中查询上线的组合宝，并根据组合宝ID从redis中查询图表数据
	 *@param response
	 *@return
	 */
	@RequestMapping(value="/getList",method=RequestMethod.POST)
	public @ResponseBody Object queryCombinationList(HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		List<Map<String,Object>> list = combinationService.queryCombinationType();
		
		logger.info("组合宝查询："+list);
		
		return list;
	}
}
