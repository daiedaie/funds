package com.mrbt.oa.mvc.controller.funds;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsBaseTypeService;
import com.mrbt.oa.mvc.service.funds.FundsPackageRatioService;
import com.mrbt.oa.mvc.service.source.Ths035Service;
import com.mrbt.oa.mvc.vo.funds.FundsBaseType;
import com.mrbt.oa.mvc.vo.funds.FundsPackageRatio;
import com.mrbt.oa.mvc.vo.source.Ths035;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("funds/packageRatio")
public class FundsPackageRatioController extends
		BaseController<FundsPackageRatio, FundsPackageRatioService> {
	@Autowired
	Ths035Service ths035Service;

	@Autowired
	FundsBaseTypeService fundsBaseTypeService;

	/**
	 * 保存
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("saveAndUpdate")
	@ResponseBody
	public Object saveAndUpdate(FundsPackageRatio vo) {
		try {
			if (vo.getId() == null) {
				String fundsCode = vo.getFundsCode();
				if (StringUtils.isBlank(fundsCode)) {
					return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
							"参数错误，未找到记录");
				}
				// 根据基金代码查询基金（基金投资类型id为F007Ths035）
				Ths035 ths035 = new Ths035();
				ths035.setF002Ths001(fundsCode);
				List<Ths035> listThs035 = ths035Service.listCombo(ths035);
				if (listThs035 == null || listThs035.size() <= 0) {
					return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
							"参数错误，未找到记录");
				}
				// 根据基金代码查询是否已经存在该基金
				List<FundsPackageRatio> listFundsPackageRatio = baseService
						.findByFundsCodeAndTypeId(fundsCode, vo.getTypeId());
				System.out.println(listFundsPackageRatio);
				System.out.println(listFundsPackageRatio.size());
				System.out.println(listFundsPackageRatio != null
						&& listFundsPackageRatio.size() > 0);
				if (listFundsPackageRatio != null
						&& listFundsPackageRatio.size() > 0) {
					return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
							"该基金组合已经存在此基金，请勿重复添加");
				}
				String fundsTypeId = listThs035.get(0).getF007Ths035();
				System.out.println("fundsTypeId " + fundsTypeId);
				vo.setFundsTypeId(fundsTypeId);
				String fundsCodeInner = listThs035.get(0).getF001Ths035();
				System.out.println("fundsCodeInner " + fundsCodeInner);
				vo.setFundsCodeInner(fundsCodeInner);
				baseService.save(vo);
				baseService.fundsPackageFundsTypeRatio(vo.getTypeId());
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
	public Object update(FundsPackageRatio vo) {
		try {
			if (vo.getId() == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"参数错误，未找到记录");
			}
			FundsPackageRatio record = baseService.listById(vo.getId());
			if (record == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"参数错误，未找到记录");
			}
			String fundsCode = vo.getFundsCode();
			if (StringUtils.isBlank(fundsCode)) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"参数错误，未找到记录");
			}
			Ths035 ths035 = new Ths035();
			ths035.setF002Ths001(fundsCode);
			List<Ths035> listThs035 = ths035Service.listCombo(ths035);
			if (listThs035 == null || listThs035.size() <= 0) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"参数错误，未找到记录");
			}
			Integer count = baseService.findByFundsCodeNoId(fundsCode,
					vo.getTypeId(), vo.getId());
			System.out.println("<<" + fundsCode + "||" + vo.getTypeId() + "||"
					+ vo.getId() + ">>");
			if (count > 0) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"该基金组合已经存在此基金，请勿重复添加");
			}
			String fundsTypeId = listThs035.get(0).getF007Ths035();
			System.out.println("fundsTypeId " + fundsTypeId);
			vo.setFundsTypeId(fundsTypeId);
			String fundsCodeInner = listThs035.get(0).getF001Ths035();
			System.out.println("fundsCodeInner " + fundsCodeInner);
			vo.setFundsCodeInner(fundsCodeInner);
			baseService.updateSelective(vo);
			baseService.fundsPackageFundsTypeRatio(vo.getTypeId());
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	/**
	 * 基金组合宝 基金类型所占比例 根据基金类型id(typeId)
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("fundsPackageFundsTypeRatio")
	@ResponseBody
	public Object fundsPackageFundsTypeRatio(String id) {
		if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id)) {
			BigDecimal typeId = new BigDecimal(id);
			return baseService.fundsPackageFundsTypeRatio(typeId);
		}
		return null;
	}
}
