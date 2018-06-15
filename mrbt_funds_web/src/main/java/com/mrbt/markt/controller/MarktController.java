package com.mrbt.markt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.markt.entity.MarketQueryParamEntity;
import com.mrbt.markt.service.MarktService;
import com.mrbt.units.Constants;


/**
 * 基金超市
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/markt")
public class MarktController{
	
	private static Logger logger = Logger.getLogger(MarktController.class);
	
	@Resource
	private MarktService marktService;
	
	@RequestMapping("/view")
	public String toIndex(HttpServletRequest request,Model model){
		
		//定义进去超市页面时，默认的查询条件，定投为5
		String start = request.getParameter("start");
		System.out.println("查询定投的基金" + start);
		
		if(start != null && !start.equals("")){
			if(start.equals("5")){
				model.addAttribute("cast_surely", 1);
			}else if (start.equals("3")){
				//热销基金
				model.addAttribute("hotsale","1");
				model.addAttribute("cast_surely", 0);
			}else{
				model.addAttribute("cast_surely", 2);
			}
		}else{
			model.addAttribute("cast_surely", 0);
		}
		
		return "/markt";
	}
	
	/**
	 * 首页--》获取基金定投列表
	 *@Description  根据solr接口获取基金定投列表
	 *@param response
	 *@param start 分页开始
	 *@param rows  每页条数
	 *@param pUrlName URL配置文件中对应的SOLR地址名
	 *@return
	 */
	@RequestMapping(value="/getFundPledge",method = RequestMethod.POST)
	public @ResponseBody Object getFundPledge(HttpServletResponse response,String start, String rows, String pUrlName){
		//避免跨域请求错误
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		Map<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("start", start);
		paramsMap.put("rows", rows);
		List<Map<String,Object>> list = marktService.getFundPledge(paramsMap,pUrlName);
		
		logger.info("FundPledge----->param:"+paramsMap+" response:"+list);
		
		if(null!=list){
			return list;
		}
		return "null";
	}
	
	/**
	 * 首页--》获取热销基金列表
	 *@Description  根据solr接口获取热销基金列表
	 *@param response
	 *@param start 分页开始
	 *@param rows 每页条数
	 *@param pUrlName URL配置文件中对应的SOLR地址名
	 *@return
	 */
	@RequestMapping(value="/getHotSellFund",method = RequestMethod.POST)
	public @ResponseBody Object getHotSellFund(HttpServletResponse response,String start, String rows, String pUrlName){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		Map<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("start", start);
		paramsMap.put("rows", rows);
		List<Map<String,Object>> list = marktService.getHotSellFund(paramsMap,pUrlName);
		
		logger.info("getHotSellFund----->param:"+paramsMap+" response:"+list);
		
		if(null!=list){
			return list;
		}
		return "null";
	}
	
	/**
	 * 基金超市--》查询基金超市列表
	 *@Description  根据solr接口查询基金超市数据，一次传入所有参数（包括排序），无值则传入null
	 *@param response
	 *@param queryParams 基金超市筛选条件实体类
	 *@return
	 */
	@RequestMapping(value="/queryFundMarketList",method = RequestMethod.POST)
	public @ResponseBody Object queryFundMarketList(HttpServletResponse response,@ModelAttribute("queryParams") MarketQueryParamEntity queryParams){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		Map<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("start", queryParams.getStart());
		paramsMap.put("rows", queryParams.getRows());
		String order = Constants.getMarketOrderNameByCode(queryParams.getOrder())+":"+queryParams.getOrderType();
		paramsMap.put("order", order);//排序
		
		logger.info("queryFundMarketList---------->param:"+queryParams.getParam());
		
		//全文搜索
		if(StringUtils.isNotBlank(queryParams.getParam())){
			paramsMap.put("param", queryParams.getParam());
		}else { //点击筛选
			paramsMap.put("castsurely", queryParams.getCastsurely());//是否定投 5定投，0非定投，null不限制
			paramsMap.put("company", queryParams.getCompany());//基金公司
			paramsMap.put("scale", queryParams.getScale());//基金规模
			paramsMap.put("theme", queryParams.getTheme());//基金主题
			paramsMap.put("type", queryParams.getType());//基金类型
		}
		JSONObject json = marktService.queryFundMarketList(paramsMap, queryParams.getpUrlName());
		
		logger.info("queryFundMarketList---------->params:"+queryParams+" json:"+json);
		
		return json;
	}

	/**
	 * 查询基金公司首字母：基金公司名称列表  Map对象
	 *@Description  从redis中查询所有公司首字母：名称对应数据，七天过期重置
	 *@param response
	 *@return
	 */
	@RequestMapping(value="/getMCByFristWord")
	public @ResponseBody Object test(HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setHeader("Accept", " application/json");
		response.setCharacterEncoding("utf-8");
		
		Map<Object,Object> map = marktService.queryMarketCompanyNameMap(Constants.MC_FIRSTWORD);
		
		logger.info("getMCByFristWord--------->map:"+map);
		
		return map;
	}
}
