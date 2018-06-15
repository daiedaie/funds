package com.mrbt.oa.mvc.controller.funds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsPackageCurveService;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurve;

@Controller
@RequestMapping("funds/packageCurve")
public class FundsPackageCurveController extends
		BaseController<FundsPackageCurve, FundsPackageCurveService> {
	
}
