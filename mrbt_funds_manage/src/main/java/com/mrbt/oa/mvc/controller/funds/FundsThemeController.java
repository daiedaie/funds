package com.mrbt.oa.mvc.controller.funds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsThemeService;
import com.mrbt.oa.mvc.vo.funds.FundsTheme;

@Controller
@RequestMapping("funds/theme")
public class FundsThemeController extends
		BaseController<FundsTheme, FundsThemeService> {
	
}
