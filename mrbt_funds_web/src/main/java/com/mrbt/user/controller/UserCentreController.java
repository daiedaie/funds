package com.mrbt.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/usercentre")
public class UserCentreController {
	
	@RequestMapping(value = "/view",method=RequestMethod.GET)
	public String toIndex(String menu, String index, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("menu", menu);
		model.addAttribute("index", index);
		return "usercentre";
	}
}
