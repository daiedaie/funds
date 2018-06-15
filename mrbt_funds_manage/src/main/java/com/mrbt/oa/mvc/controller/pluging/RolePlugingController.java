package com.mrbt.oa.mvc.controller.pluging;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.service.company.MrbtCompanyRoleService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyUserRRoleService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRole;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRRole;
import com.mrbt.utils.GridPage;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("pluging/role_pluging")
public class RolePlugingController {
	private Logger log = Logger.getLogger(RolePlugingController.class);
	@Autowired
	public MrbtCompanyRoleService mrbtCompanyRoleService;

	@Autowired
	public MrbtCompanyUserRRoleService mrbtCompanyUserRRoleService;
	@RequestMapping("listByCheckUid")
	@ResponseBody
	public Object listByCheckUid(Integer uId, String rName) {
		GridPage<MrbtCompanyRole> result = new GridPage<MrbtCompanyRole>();
		if (uId == null || uId <= 0) {
			result.setRows(new ArrayList<MrbtCompanyRole>());
			result.setTotal(0);
		} else {
			List<MrbtCompanyRole> list = mrbtCompanyRoleService.listByCheckUid(
					uId, rName);
			result.setRows(list);
			result.setTotal(list.size());
		}
		return result;
	}

	@RequestMapping("deleteByUser")
	@ResponseBody
	public Object deleteByUser(MrbtCompanyUserRRole vo) {
		if (vo.getrId() != null && vo.getuId() != null) {
			try {
				mrbtCompanyUserRRoleService.deleteByUidRid(vo);
				return ResponseUtils.success();
			} catch (Exception ex) {
				log.error(ex);
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						ex.getMessage());
			}
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}
	}

	/**
	 * 更新用户的全线路列表
	 * 
	 * @param u_id
	 * @param r_ids
	 * @return
	 */
	@RequestMapping("updateUserRRole")
	@ResponseBody
	public Object updateUserRRole(String u_id, String r_ids) {
		if (StringUtils.isNotBlank(u_id) && NumberUtils.isNumber(u_id)
				&& NumberUtils.toInt(u_id) > 0) {
			try {
				mrbtCompanyUserRRoleService.updateUserRRole(
						NumberUtils.toInt(u_id), r_ids);
				return ResponseUtils.success();
			} catch (Exception ex) {
				log.error(ex);
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						ex.getMessage());
			}
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}
	}
}
