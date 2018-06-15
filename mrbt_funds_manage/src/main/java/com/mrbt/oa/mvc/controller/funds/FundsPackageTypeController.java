package com.mrbt.oa.mvc.controller.funds;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsPackageTypeService;
import com.mrbt.oa.mvc.vo.funds.FundsPackageType;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.DateUtils;
import com.mrbt.utils.ResponseUtils;
import com.mrbt.utils.redis.RedisDao;

@Controller
@RequestMapping("funds/packageType")
public class FundsPackageTypeController extends
		BaseController<FundsPackageType, FundsPackageTypeService> {
	@Autowired
	public RedisDao redisDao;

	/**
	 * 保存
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("saveAndUpdate")
	@ResponseBody
	public Object saveAndUpdate(FundsPackageType vo) {
		try {
			if (vo.getId() == null) {
				vo.setCreateTime(new Date());
				vo.setOnLine(AppCons.DEFAULT_ON_LINE);
				baseService.save(vo);
			} else {
				return update(vo);
			}
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	/**
	 * 更新
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	public Object update(FundsPackageType vo) {
		try {
			if (vo.getId() == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"参数错误，未找到记录");
			}
			FundsPackageType record = baseService.listById(vo.getId());
			if (record == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"参数错误，未找到记录");
			}
			baseService.updateSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	/**
	 * 修改在线状态
	 * 
	 * @param id
	 * @param online
	 * @return
	 */
	@RequestMapping("online")
	@ResponseBody
	public Object online(String id, String online) {
		if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id)
				&& StringUtils.isNotBlank(online)
				&& NumberUtils.isNumber(online)) {
			try {
				FundsPackageType record = baseService.listById(new BigDecimal(
						id));
				if (record != null) {
					if ("0".equals(online)) {// 未发布
						record.setOnLine(AppCons.DEFAULT_ON_LINE);
					} else if ("1".equals(online)) {
						record.setOnLine((short) 0);
					}
					baseService.updateSelective(record);
				} else {
					return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
							"参数错误，未找到记录");
				}
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
	 * 生成组合基金曲线
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("generateCurve")
	@ResponseBody
	public Object generateCurve(String id) {
		try {
			if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id)) {
				Date oldDate = DateUtils.getYesterdayOfLastYear();
				redisDao.delete(AppCons.FUNDS_PACKAGE_CURVE_OLD_DATE_ + id);
				redisDao.set(AppCons.FUNDS_PACKAGE_CURVE_OLD_DATE_ + id,
						oldDate);
				List<Map<String, Object>> listMap = baseService.generateCurve(
						new BigDecimal(id), oldDate, DateUtils.getYesterday(),
						(short) 1);
				if (listMap != null && listMap.size() > 0) {
					return ResponseUtils.success(listMap);
				}
			}
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "生成失败");
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

}
