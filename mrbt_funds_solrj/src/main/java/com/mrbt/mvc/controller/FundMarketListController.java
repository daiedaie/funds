package com.mrbt.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.service.service.market.FundMarketHandlerService;
import com.mrbt.mvc.vo.FundMarketListVo;
import com.mrbt.mvc.vo.FundMarketVo;
import com.mrbt.utils.StringCustomerUtils;

@Controller
@RequestMapping(value = "/market")
public class FundMarketListController {
	private static final Logger log = LogManager
			.getLogger(FundMarketListController.class);
	@Autowired
	private FundMarketHandlerService fundMarketHandlerService;

	public FundMarketListController() {
	}
	/****
	 * 基金超市的搜索
	 * @param request
	 * @param response
	 * @param param
	 * @param start
	 * @param rows
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/retrieve", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String retrieveMarketIndex(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "param") String param,
			@RequestParam(value = "start") int start,
			@RequestParam(value = "rows") int rows,
			@RequestParam(value = "order",required = false) String order) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		if(StringCustomerUtils.isEmpty(param) || start < 0 || rows <0){
			return "";
		}
		String select = "";
		try {
			select = fundMarketHandlerService.retrieve(param,start,rows,order);
		} catch (Exception e) {
			log.error(e);
			return "";
		} finally {
			fundMarketHandlerService.finish();
		}
		return select;
	}
	/**
	 * 查询基金超市索引
	 * 
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectMarketIndex(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		String select = "";
		try {
			select = fundMarketHandlerService.select();
		} catch (Exception e) {
			log.error(e);
			//e.printStackTrace();
			return AppInfo.FOUND_MARKET_SELECT_MESSAGE_F;
		} finally {
			fundMarketHandlerService.finish();
		}
		return select;
	}
	
	/***
	 * 基金超市列表
	 * 
	 * @RequestParam(value="castsurely",required = false) String castsurely
	 * @param castsurely
	 * @param type
	 * @param scale
	 * @param company
	 * @param theme
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectMarketList( HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "start") int start,
			@RequestParam(value = "rows") int rows,
			@RequestParam(value = "castsurely",required = false) String castsurely,
			@RequestParam(value = "type",required = false) String type,
			@RequestParam(value = "scale" ,required = false) String scale,
			@RequestParam(value = "company",required = false) String company,
			@RequestParam(value = "theme",required = false) String theme,
			@RequestParam(value = "order",required = false) String order) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("进入基金超市列表查询");
		System.out.println("进入基金超市列表查询");
		if(start < 0 || rows < 0){
			return null;
		}
		if(castsurely == null || StringCustomerUtils.isEmpty(castsurely))castsurely ="*";
		if(type == null || StringCustomerUtils.isEmpty(type))type ="*";
		if(scale == null || StringCustomerUtils.isEmpty(scale))scale ="*";
		if(company == null || StringCustomerUtils.isEmpty(company))company ="*";
		if(theme == null || StringCustomerUtils.isEmpty(theme))theme ="*";
		//排序条件
		String select = "";
		try {
			select = fundMarketHandlerService.selectMarketList(start, rows,
					castsurely, type, scale, company, theme, order);
			return select;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		} finally {
			fundMarketHandlerService.finish();
		}
	}
	
	/**
	 * 根据基金代码返回数据
	 * 提供基金详情的部分接口数据
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMarketList( HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id",required = false) String id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("进入基金列表/get");
		System.out.println("进入基金列表/get");
		if(StringCustomerUtils.isEmpty(id)){
			return "";
		}
		String select = "";
		try {
			select = fundMarketHandlerService.getMarketListByCode(id);
			return select;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		} finally {
			fundMarketHandlerService.finish();
		}
	}
	

	/**
	 * 根据基金名或基金代码搜索
	 * 
	 * @param fundname_code
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectMarketSearchList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "start") int start,
			@RequestParam(value = "rows") int rows,
			@RequestParam(value = "namecode") String namecode,
			@RequestParam(value = "order") String order) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		String select = "";
		try {
			select = fundMarketHandlerService.selectMarketSearchList(start,
					rows, namecode, order);
			return select;
		} catch (Exception e) {
			log.error(e);
			//e.printStackTrace();
			return "";
		} finally {
			fundMarketHandlerService.finish();
		}
	}
	
	
	@RequestMapping(value = "/returnlist", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<FundMarketListVo> selectMarketReturnList( HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "start") int start,
			@RequestParam(value = "rows") int rows,
			@RequestParam(value = "castsurely",required = false) String castsurely,
			@RequestParam(value = "type",required = false) String type,
			@RequestParam(value = "scale" ,required = false) String scale,
			@RequestParam(value = "company",required = false) String company,
			@RequestParam(value = "theme",required = false) String theme,
			@RequestParam(value = "order",required = false) String order) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		if(start < 0 || rows < 0){
			return null;
		}
		if(castsurely == null || StringCustomerUtils.isEmpty(castsurely))castsurely ="*";
		if(type == null || StringCustomerUtils.isEmpty(type))type ="*";
		if(scale == null || StringCustomerUtils.isEmpty(scale))scale ="*";
		if(company == null || StringCustomerUtils.isEmpty(company))company ="*";
		if(theme == null || StringCustomerUtils.isEmpty(theme))theme ="*";
		List<FundMarketListVo> select = null;
		try {
			select = fundMarketHandlerService.selectMarketReturnList(start, rows,
					castsurely, type, scale, company, theme, order);
			return select;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return select;
		} finally {
			fundMarketHandlerService.finish();
		}
	}
	@RequestMapping(value = "/marketlist", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectMarketAllList( HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		String select = "";
		try {
			log.info("进入查询====================================================");
			select = fundMarketHandlerService.selectMarketAllList();
			return select;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		} finally {
			fundMarketHandlerService.finish();
		}
	}
	
	/***
	 * 基金列表索引的添加接口
	 * @param request
	 * @param response
	 * @param index
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addMarketIndex(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "index") String index) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		if(StringCustomerUtils.isEmpty(index)){
			return "调用接口添加索引失败,传入JSON不能为空";
		}
		try {
			JSONObject jsonIndex = JSONObject.fromObject(index);
			FundMarketVo fundMarketVo = (FundMarketVo) JSONObject.toBean(jsonIndex,FundMarketVo.class);
			fundMarketHandlerService.addBean(fundMarketVo);
			log.info("调用接口添加索引成功");
			return "调用接口添加索引失败";
		} catch (Exception e) {
			log.error(e);
			return "调用接口添加索引失败"+e.getMessage();
		} finally {
			fundMarketHandlerService.finish();
		}
	}
}
