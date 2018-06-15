package com.mrbt.oa.mvc.controller.source;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsMarketService;
import com.mrbt.oa.mvc.service.source.Ths035Service;
import com.mrbt.oa.mvc.vo.funds.FundsMarket;
import com.mrbt.oa.mvc.vo.source.Ths035;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.DateUtils;
import com.mrbt.utils.HttpClientUtil_Market_TEST;
import com.mrbt.utils.PropertiesUtil;
import com.mrbt.utils.ResponseUtils;
import com.mrbt.utils.RowBoundsUtils;

@Controller
@RequestMapping("source/ths035")
public class Ths035Controller extends BaseController<Ths035, Ths035Service> {
	@Autowired
	public FundsMarketService fundsMarketService;

	/**
	 * 查询
	 * 
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("listCombo")
	@ResponseBody
	public Object listCombo(Ths035 vo, String page, String rows) {
		return this.getBaseService().listGridCombo(vo,
				RowBoundsUtils.getRowBounds(page, rows));
	}

	/**
	 * 添加到基金超市
	 * 
	 * @param fundsCode
	 * @return
	 */
	@RequestMapping("addToFundsMarket")
	@ResponseBody
	public Object addToFundsMarket(String fundsCode, String fundsCodeInner) {
		System.out.println("添加到基金超市 fundsCode = " + fundsCode);
		if (StringUtils.isBlank(fundsCode)
				|| StringUtils.isBlank(fundsCodeInner)) {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "请选择基金！");
		}
		FundsMarket fundsMarket = fundsMarketService.findByPk(fundsCode);
		try {
			if (fundsMarket != null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"基金超市已存在该基金，请勿重复添加！");
			}
			log.info("添加基金超市索引-->添加到基金超市");
			FundsMarket vo = new FundsMarket();
			vo.setCreateTime(new Date());
			vo.setFundsCode(fundsCode);
			vo.setFundsCodeInner(fundsCodeInner);
			vo.setIsRecomm(AppCons.DEFAULT_IS_RECOMM);
			vo.setRecommOrder(AppCons.DEFAULT_RECOMM_ORDER);
			fundsMarketService.save(vo);
			HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
			Map<String, String> map = new HashMap<String, String>();
			map.put("funds_code", fundsCode);
			map.put("create_time",
					DateUtils.getDateStr(new Date(), DateUtils.sdf1));
			map.put("is_recomm", AppCons.DEFAULT_IS_RECOMM + "");
			map.put("recomm_order", AppCons.DEFAULT_RECOMM_ORDER + "");
			map.put("funds_code_inner", fundsCodeInner);
			map.put("recomm_reason", null);
			map.put("funds_theme", null);
			String result = post.doPost(PropertiesUtil.getPropertiesByKey("fundsMarketUpdate"), map, "UTF-8");
			System.out.println(result);
			if(result.contains("failure")){
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"添加基金超市索引失败");
			}
			

			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}

	}
}
