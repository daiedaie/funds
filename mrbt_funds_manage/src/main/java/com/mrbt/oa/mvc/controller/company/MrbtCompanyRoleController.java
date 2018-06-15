package com.mrbt.oa.mvc.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.company.MrbtCompanyRoleService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRole;

@Controller
@RequestMapping("company/role")
public class MrbtCompanyRoleController
		extends
			BaseController<MrbtCompanyRole, MrbtCompanyRoleService> {

}
