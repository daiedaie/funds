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

import com.mrbt.oa.mvc.service.company.MrbtCompanyDepartService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyUserRDepartService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyDepart;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRDepart;
import com.mrbt.utils.GridPage;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("pluging/depart_pluging")
public class DepartPlugingController {
	private Logger log = Logger.getLogger(DepartPlugingController.class);

	@Autowired
	private MrbtCompanyDepartService mrbtCompanyDepartService;
	@Autowired
	private MrbtCompanyUserRDepartService mrbtCompanyUserRDepartService;

	@RequestMapping("listByUid")
	@ResponseBody
	public Object listByUid(Integer uId, String dpName) {
		GridPage<MrbtCompanyDepart> result = new GridPage<MrbtCompanyDepart>();
		if (uId == null || uId <= 0) {
			result.setRows(new ArrayList<MrbtCompanyDepart>());
			result.setTotal(0);
		} else {
			List<MrbtCompanyDepart> list = mrbtCompanyDepartService
					.listByUidNotRelation(uId, dpName);
			result.setRows(list);
			result.setTotal(list.size());
		}
		return result;
	}

	@RequestMapping("deleteByUser")
	@ResponseBody
	public Object deleteByUser(MrbtCompanyUserRDepart vo) {
		if (vo.getDpId() != null && vo.getuId() != null) {
			try {
				mrbtCompanyUserRDepartService.deleteByUidDpid(vo);
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
	@RequestMapping("updateUserRDepart")
	@ResponseBody
	public Object updateUserRRole(String u_id, String dp_ids) {
		if (StringUtils.isNotBlank(u_id) && NumberUtils.isNumber(u_id)
				&& NumberUtils.toInt(u_id) > 0) {
			try {
				mrbtCompanyUserRDepartService.updateUserRDepart(
						NumberUtils.toInt(u_id), dp_ids);
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
