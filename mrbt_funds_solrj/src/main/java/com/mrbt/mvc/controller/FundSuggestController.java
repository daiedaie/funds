package com.mrbt.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.mvc.service.service.suggest.FundSuggestHandlerService;
import com.mrbt.utils.StringCustomerUtils;


/**
 * 全文搜索基金代码和基金名称
 *
 */
@Controller
@RequestMapping(value = "/search")
public class FundSuggestController {
	private Logger log = LogManager.getLogger(FundSuggestController.class);
	@Autowired
	private FundSuggestHandlerService fundSuggestHandlerService;
	@RequestMapping(value = "/suggest", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String suggest(HttpServletRequest request, HttpServletResponse response,@RequestParam("content") String content){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		if(StringCustomerUtils.isEmpty(content)){
			return null;
		}
		log.info("执行全文检索:"+content);
		String result = null;
		try {
			result = fundSuggestHandlerService.suggest(content);
			return result;
		} catch (IOException | SolrServerException e) {
			log.error(e);
			return null;
		}
		finally{
			fundSuggestHandlerService.finish();
		}
	}
}
