package com.mrbt.oa.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("error")
public class ErrorController {
	@RequestMapping(value = "noperms")
	public String noperms() {
		return "error/noperms";
	}

	@RequestMapping(value = "noperms", produces = "application/json")
	@ResponseBody
	public Object nopermsAjax(HttpServletRequest req, Model model) {
		return ResponseUtils.failure(ResponseUtils.ERROR_NOPERMS_CODE,
				"您无此操作权限");
	}
}
