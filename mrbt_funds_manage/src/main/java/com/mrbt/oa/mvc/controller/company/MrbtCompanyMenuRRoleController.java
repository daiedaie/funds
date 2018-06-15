package com.mrbt.oa.mvc.controller.company;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.company.MrbtCompanyMenuRRoleService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyRoleService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenuRRole;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRole;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("company/menu_r_role")
public class MrbtCompanyMenuRRoleController
		extends
			BaseController<MrbtCompanyMenuRRole, MrbtCompanyMenuRRoleService> {
	@Autowired
	public MrbtCompanyRoleService mrbtCompanyRoleService;

	@RequestMapping("updateRelation")
	@ResponseBody
	public Object saveRelation(Integer roleId, String menuIds) {
		if (roleId != null && roleId > 0) {
			MrbtCompanyRole vo = mrbtCompanyRoleService.listById(roleId);
			if (StringUtils.isNotBlank(menuIds)) {
				this.getBaseService().saveRelation(vo, menuIds);
			}
			return ResponseUtils.success();
		}
		return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
	}
}
