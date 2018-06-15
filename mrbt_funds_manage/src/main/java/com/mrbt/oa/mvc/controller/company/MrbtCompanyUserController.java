package com.mrbt.oa.mvc.controller.company;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.company.MrbtCompanyUserRDepartService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyUserRRoleService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyUserService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUser;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRRole;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.MD5Utils;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("company/user")
public class MrbtCompanyUserController extends
		BaseController<MrbtCompanyUser, MrbtCompanyUserService> {
	@Autowired
	public MrbtCompanyUserRRoleService mrbtCompanyUserRRoleService;
	@Autowired
	public MrbtCompanyUserRDepartService mrbtCompanyUserRDepartService;

	/**
	 * 保存权限
	 * 
	 * @param vo
	 * @return
	 */
	@Override
	@RequestMapping("save")
	@ResponseBody
	public Object save(MrbtCompanyUser vo) {
		try {
			vo.setLoginPass(AppCons.INIT_PASSWORD);
			vo.setLoginPass(MD5Utils.MD5(vo.getLoginPass()));
			baseService.saveSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	/**
	 * 查找权限
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping("findRoleByUser")
	@ResponseBody
	public Object findRoleByUser(Integer uid) {
		MrbtCompanyUserRRole vo = mrbtCompanyUserRRoleService.findByUser(uid);
		if (vo != null) {
			return vo.getRolse();
		}
		return new ArrayList<MrbtCompanyUserRRole>(0);
	}

}
