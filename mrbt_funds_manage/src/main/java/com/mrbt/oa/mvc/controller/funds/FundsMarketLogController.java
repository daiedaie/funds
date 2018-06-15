package com.mrbt.oa.mvc.controller.funds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsMarketLogService;
import com.mrbt.oa.mvc.vo.funds.FundsMarketLog;

@Controller
@RequestMapping("funds/marketLog")
public class FundsMarketLogController extends
		BaseController<FundsMarketLog, FundsMarketLogService> {
	
}
