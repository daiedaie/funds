package com.mrbt.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.user.service.UserActiveService;

import net.sf.json.JSONObject;

/**
 * 用户注册登录相关
 * @author yulong
 *
 */

@Controller
@RequestMapping("/user")
public class UserActiveController {
	
	@Resource
	private UserActiveService userActiveService;
	
	@RequestMapping(value = "/setSession",method=RequestMethod.POST)
	public @ResponseBody Object setSession(HttpServletRequest request, HttpServletResponse response){

		JSONObject json = new JSONObject();
		try {
			HttpSession session = request.getSession();
			System.out.println(request.getParameter("pUrl"));
			session.setAttribute("pUrl", request.getParameter("pUrl"));
			
			json.put("code", 200);
			json.put("msg", "success");
		} catch (Exception e) {
			json.put("code", 500);
			json.put("msg", "server error");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String toLoginIndex(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
		System.out.println(session.getAttribute("pUrl"));
		
		return "login";
	}
	
	@RequestMapping(value = "/regis",method=RequestMethod.GET)
	public String toRegisIndex(HttpServletRequest request, HttpServletResponse response){
		return "regis";
	}
	
}
