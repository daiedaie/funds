package com.mrbt.oa.mvc.controller.funds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsBaseTypeService;
import com.mrbt.oa.mvc.vo.funds.FundsBaseType;

@Controller
@RequestMapping("funds/baseType")
public class FundsBaseTypeController extends
		BaseController<FundsBaseType, FundsBaseTypeService> {
	
}
