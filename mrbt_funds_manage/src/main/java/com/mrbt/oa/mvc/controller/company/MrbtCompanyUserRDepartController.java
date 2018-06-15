package com.mrbt.oa.mvc.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.company.MrbtCompanyUserRDepartService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRDepart;

@Controller
@RequestMapping("company/user_r_depart")
public class MrbtCompanyUserRDepartController extends
		BaseController<MrbtCompanyUserRDepart, MrbtCompanyUserRDepartService> {
	/**
	 * 根据部门id查找用户
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping("findUserByDepart")
	@ResponseBody
	public Object findUserByDepart(Integer d_id) {
		return this.getBaseService().findUserByDepart(d_id);
	}

	/**
	 * 根据部门id查找用户
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping("findDepartByUser")
	@ResponseBody
	public Object findDepartByUser(Integer u_id) {
		return this.getBaseService().findDepartByUser(u_id);
	}
}
