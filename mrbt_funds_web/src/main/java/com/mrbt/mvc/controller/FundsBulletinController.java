package com.mrbt.mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mrbt.mvc.model.FundsBulletin;
import com.mrbt.mvc.service.FundsBulletinService;

/**
 *@author yiban sun
 *@date 2016年6月20日 下午5:06:47
 *@version 1.0
 *@description 
 *@since
 **/
@Controller
@RequestMapping("/")
public class FundsBulletinController {
	
	@Autowired
	private FundsBulletinService fundsBulletinService;
	
	@RequestMapping("/queryAll")
	public void showFundsBanner(HttpServletRequest request,Model model,HttpServletResponse response){
		List<FundsBulletin> list = fundsBulletinService.queryNewestOne();
		model.addAttribute("list",list);
		String json = JSONObject.toJSONString(list);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/index")
	public String toIndex(){
		return "index";
	}
}
