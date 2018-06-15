package com.mrbt.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.mvc.service.service.detail.FundDetailHandlerService;
import com.mrbt.utils.StringCustomerUtils;

/**
 * 基金详情Controller
 *
 */
@Controller
@RequestMapping(value = "/detail")
public class FundDetailController {
	private static final Logger log = LogManager.getLogger(FundDetailController.class);
	@Autowired
	private FundDetailHandlerService fundDetailHandlerService;
	public FundDetailController() {
		log.info("基金详情Controller初始化");
	}
	
	/***
	 * 根据基金代码查询基金的详情数据
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getFundDetailByID(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") String id){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("进入基金详情查询/get");
		System.out.println("进入基金详情查询/get");
		if (StringCustomerUtils.isEmpty(id)) {
			return null;
		}
		try {
			return fundDetailHandlerService.getFundDetailByCode(id);
		} catch (Exception e) {
			fundDetailHandlerService.rollback();
			return null;
		}
		finally{
			fundDetailHandlerService.finish();
		}
	}
	/****
	 * 基金详情列表
	 * @param request
	 * @param response
	 * @param start
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getFundDetailList(HttpServletRequest request, 
			HttpServletResponse response,@RequestParam("start") int start,@RequestParam("rows") int rows){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("进入基金详情查询/list");
		System.out.println("进入基金详情查询/list");
		if (start < 0 || rows < 0) {
			return null;
		}
		try {
			return fundDetailHandlerService.getFundDetailList(start,rows);
		} catch (Exception e) {
			fundDetailHandlerService.rollback();
			return null;
		}
		finally{
			fundDetailHandlerService.finish();
		}
	}

}
