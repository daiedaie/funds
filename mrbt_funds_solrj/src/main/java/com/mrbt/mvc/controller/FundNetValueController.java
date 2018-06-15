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

import com.mrbt.mvc.service.service.netvalue.FundNetValueHandlerService;

@Controller
@RequestMapping(value = "/netvalue")
public class FundNetValueController {
	private static final Logger log = LogManager.getLogger(FundNetValueController.class);
	@Autowired
	private FundNetValueHandlerService fundNetValueHandlerService;
	public FundNetValueController() {
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getFundNetValueANDYearGain(HttpServletRequest request, HttpServletResponse response,@RequestParam("code") String code){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("查询净值和年收益");
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		try {
			return fundNetValueHandlerService.getFundNetValueAndYearGain(code);
		} catch (Exception e) {
			fundNetValueHandlerService.rollback();
			return null;
		}
		finally{
			fundNetValueHandlerService.finish();
		}
	}

}
