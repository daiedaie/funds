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

import com.mrbt.config.AppInfo;
import com.mrbt.mvc.service.service.castsurely.FundCastSurelyHandlerService;

@Controller
@RequestMapping(value = "/castsurely")
public class FundCastSurelyController {
	/**
	 * 基金定投Controller
	 */
	private static final Logger log = LogManager
			.getLogger(FundCastSurelyController.class);
	@Autowired
	private FundCastSurelyHandlerService foundCastSurelyHandlerService;

	@RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectFoundCastSurely(HttpServletRequest request, HttpServletResponse response,@RequestParam("start") int start,
			@RequestParam("rows") int rows) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("进入定投基金查询"+start+"-"+rows);
		System.out.println("进入定投基金查询");
		if (start < 0 || rows < 0) {
			return AppInfo.FOUND_CASTSURELY_SELECT_MESSAGE_F;
		}
		String select = "";
		log.info("get request select ");
		try {
			select = foundCastSurelyHandlerService.select(start, rows);
			return select;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		} finally {
			foundCastSurelyHandlerService.finish();
		}
	}
	public FundCastSurelyController() {
	}
	
	@RequestMapping(value = "/selectall", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectAll(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		String select = "";
		log.info("select all indexes ...");
		try {
			select = foundCastSurelyHandlerService.selectAll();
			return select;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		} finally {
			foundCastSurelyHandlerService.finish();
		}
	}
}
