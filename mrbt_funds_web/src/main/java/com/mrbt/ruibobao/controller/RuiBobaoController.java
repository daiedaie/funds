package com.mrbt.ruibobao.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ruibobao")
public class RuiBobaoController {
	
	@RequestMapping("/view")
	public String toIndex(HttpServletRequest request,Model model){
		
		return "ruibobao";
	}
}
