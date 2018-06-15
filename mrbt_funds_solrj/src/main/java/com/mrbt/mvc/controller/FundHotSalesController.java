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
import com.mrbt.mvc.service.service.hotsale.FundHotSalesHandlerService;

@Controller
@RequestMapping(value = "/hotsale")
public class FundHotSalesController {
	/**
	 * 热销基金Controller
	 */
	private static final Logger log = LogManager
			.getLogger(FundHotSalesController.class);
	@Autowired
	private FundHotSalesHandlerService foundHotSalesHandlerService;

	public FundHotSalesController() {
	}

	/**
	 * 热销查询
	 * @param start
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectFoundHotSales(HttpServletRequest request, HttpServletResponse response,@RequestParam("start") int start,
			@RequestParam("rows") int rows) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", " application/json");
		response.setCharacterEncoding("utf-8");
		log.info("进入热销基金查询/select");
		System.out.println("进入热销基金查询/select");
		if (start < 0 || rows < 0) {
			return AppInfo.FOUND_HOTSALES_SELECT_MESSAGE_F;
		}
		String select = "";
		log.info("get request select ");
		try {
			select = foundHotSalesHandlerService.select(start, rows);
			return select;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		} finally {
			foundHotSalesHandlerService.finish();
		}
	}
}
