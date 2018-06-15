package com.mrbt.oa.mvc.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转的forwanrd
 * 
 * @author airgilbert
 *
 */
@Controller
@RequestMapping("forward")
public class ForwanrdController {
	@RequestMapping("/{name}")
	public String forward(@PathVariable String name, String rn, ModelMap map) {
		map.put("rn", rn);
		if (StringUtils.isNotBlank(name) && name.equalsIgnoreCase("main")) {
			return name;
		}
		return name.replace("_", "/") + "/show";
	}
}
