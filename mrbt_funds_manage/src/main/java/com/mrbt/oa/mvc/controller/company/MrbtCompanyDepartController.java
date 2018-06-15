package com.mrbt.oa.mvc.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.company.MrbtCompanyDepartService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyDepart;

@Controller
@RequestMapping("company/depart")
public class MrbtCompanyDepartController extends
		BaseController<MrbtCompanyDepart, MrbtCompanyDepartService> {

}
