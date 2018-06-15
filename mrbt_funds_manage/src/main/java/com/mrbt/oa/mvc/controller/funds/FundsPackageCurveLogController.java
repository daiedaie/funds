package com.mrbt.oa.mvc.controller.funds;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsPackageCurveLogService;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurveLog;

@Controller
@RequestMapping("funds/packageCurveLog")
public class FundsPackageCurveLogController extends
		BaseController<FundsPackageCurveLog, FundsPackageCurveLogService> {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}
