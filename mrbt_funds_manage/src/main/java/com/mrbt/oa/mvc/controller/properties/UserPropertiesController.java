package com.mrbt.oa.mvc.controller.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.service.company.MrbtCompanyDepartService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyRoleService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyUserRDepartService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyDepart;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRole;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRDepart;
import com.mrbt.utils.GridPage;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("properties/user_properties")
public class UserPropertiesController {
	@Autowired
	public MrbtCompanyRoleService mrbtCompanyRoleService;
	@Autowired
	public MrbtCompanyDepartService mrbtCompanyDepartService;
	@Autowired
	public MrbtCompanyUserRDepartService mrbtCompanyUserRDepartService;

	@RequestMapping("listByUid")
	@ResponseBody
	public Object listByUid(Integer uId) {
		GridPage<MrbtCompanyRole> result = new GridPage<MrbtCompanyRole>();

		if (uId == null || uId <= 0) {
			result.setRows(new ArrayList<MrbtCompanyRole>());
			result.setTotal(0);
		} else {
			List<MrbtCompanyRole> list = mrbtCompanyRoleService.listByUid(uId);
			result.setRows(list);
			result.setTotal(list.size());
		}
		return result;
	}
	/**
	 * 根据用户查找部门
	 * 
	 * @param uId
	 * @return
	 */
	@RequestMapping("listDepartByUid")
	@ResponseBody
	public Object listDepartByUid(Integer uId) {
		GridPage<MrbtCompanyDepart> result = new GridPage<MrbtCompanyDepart>();
		if (uId == null || uId <= 0) {
			result.setRows(new ArrayList<MrbtCompanyDepart>());
			result.setTotal(0);
		} else {
			List<MrbtCompanyDepart> list = mrbtCompanyDepartService
					.listDepartByUid(uId);
			result.setRows(list);
			result.setTotal(list.size());
		}
		return result;
	}
	/**
	 * 更改用户是否为领导人的权限
	 * 
	 * @param uId
	 * @param dpId
	 * @param checked
	 * @return
	 */
	@RequestMapping("changeLeader")
	@ResponseBody
	public Object changeLeader(Integer uId, Integer dpId, Boolean checked) {
		if (uId != null && dpId != null && checked != null) {
			MrbtCompanyUserRDepart vo = new MrbtCompanyUserRDepart();
			vo.setDpId(dpId);
			vo.setuId(uId);
			vo.setIsLeader(checked ? 1 : 0);
			mrbtCompanyUserRDepartService.updateUserRDepart(vo);
			return ResponseUtils.success();
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}

	}

}
