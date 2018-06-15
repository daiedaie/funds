package com.mrbt.knowledge.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.knowledge.model.KnowledgeInfo;
import com.mrbt.knowledge.service.KnowledgeService;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {
	
	@Resource
	private KnowledgeService knowledgeServic;
	
	@RequestMapping("/view")
	public String toIndex(HttpServletRequest request,Model model){
		
		Integer pageNum = 1;
		Integer rows = 10;
		if(request.getParameter("page") != null && !request.getParameter("page").equals("")){
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
			
		//获取知识库列表
		List<KnowledgeInfo> resList = knowledgeServic.getKnoledgeList(pageNum, rows);
		Integer current = knowledgeServic.getKnoledgeCount();
		
		model.addAttribute("list", resList);//数据列表
		model.addAttribute("currentpage", pageNum);//当前页pagenum
		model.addAttribute("pagenum", (current > current/rows * rows) ? current/rows + 1 : current/rows);//页数
		
		return "knowledge";
	}
}
