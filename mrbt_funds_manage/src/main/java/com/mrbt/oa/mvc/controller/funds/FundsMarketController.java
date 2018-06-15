package com.mrbt.oa.mvc.controller.funds;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.funds.FundsMarketLogService;
import com.mrbt.oa.mvc.service.funds.FundsMarketService;
import com.mrbt.oa.mvc.vo.funds.FundsMarket;
import com.mrbt.oa.mvc.vo.funds.FundsMarketLog;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.DateUtils;
import com.mrbt.utils.HttpClientUtil_Market_TEST;
import com.mrbt.utils.MyUtils;
import com.mrbt.utils.PropertiesUtil;
import com.mrbt.utils.ResponseUtils;

@Controller
@RequestMapping("funds/market")
public class FundsMarketController extends
		BaseController<FundsMarket, FundsMarketService> {
	private Logger log = MyUtils.getLogger(FundsMarketController.class);
	@Autowired
	FundsMarketLogService fundsMarketLogService;

	/**
	 * 设置为推荐基金
	 * 
	 * @param fundsCode
	 * @return
	 */
	@RequestMapping("setToFundsRecomm")
	@ResponseBody
	public Object setToFundsRecomm(String fundsCode) {
		System.out.println("设置为推荐基金 fundsCode = " + fundsCode);
		if (StringUtils.isBlank(fundsCode)) {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "请选择基金！");
		}
		FundsMarket fundsMarket = baseService.findByPk(fundsCode);
		try {
			if (fundsMarket == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"请选择基金！");
			}
			if (!fundsMarket.getIsRecomm().equals(AppCons.DEFAULT_IS_RECOMM)) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"该基金已设置为推荐基金，请勿重复设置！");
			}
			// 调用接口
			HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
			Map<String, String> map = new HashMap<String, String>();
			map.put("funds_code", fundsCode);
			map.put("create_time", DateUtils.getDateStr(
					fundsMarket.getCreateTime(), DateUtils.sdf1));
			map.put("is_recomm", "1");
			map.put("recomm_order", fundsMarket.getRecommOrder() + "");
			map.put("funds_code_inner", fundsMarket.getFundsCodeInner());
			map.put("recomm_reason", fundsMarket.getRecommReason());
			map.put("funds_theme", fundsMarket.getFundsTheme());
			String result = post.doPost(
					PropertiesUtil.getPropertiesByKey("fundsMarketUpdate"),
					map, "UTF-8");
			System.out.println(result);
			if (result.contains("failure")) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"修改基金超市索引失败");
			}
			// 更新数据库
			log.info("修改基金超市索引-->设置为推荐基金");
			fundsMarket.setIsRecomm((short) 1);
			baseService.updateSelective(fundsMarket);

			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}

	}

	/**
	 * 从推荐基金下线
	 * 
	 * @param fundsCode
	 * @return
	 */
	@RequestMapping("offlineFromFundsRecomm")
	@ResponseBody
	public Object offlineFromFundsRecomm(String fundsCode) {
		System.out.println("从推荐基金下线 fundsCode = " + fundsCode);
		if (StringUtils.isBlank(fundsCode)) {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "请选择基金！");
		}
		FundsMarket fundsMarket = baseService.findByPk(fundsCode);
		try {
			if (fundsMarket == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"请选择基金！");
			}
			if (fundsMarket.getIsRecomm().equals(AppCons.DEFAULT_IS_RECOMM)) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"该基金已从推荐基金下线，请勿重复设置！");
			}

			// 调用接口
			HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
			Map<String, String> map = new HashMap<String, String>();
			map.put("funds_code", fundsCode);
			map.put("create_time", DateUtils.getDateStr(
					fundsMarket.getCreateTime(), DateUtils.sdf1));
			map.put("is_recomm", AppCons.DEFAULT_IS_RECOMM + "");
			map.put("recomm_order", AppCons.DEFAULT_RECOMM_ORDER + "");
			map.put("funds_code_inner", fundsMarket.getFundsCodeInner());
			map.put("recomm_reason", fundsMarket.getRecommReason());
			map.put("funds_theme", fundsMarket.getFundsTheme());
			String result = post.doPost(
					PropertiesUtil.getPropertiesByKey("fundsMarketUpdate"),
					map, "UTF-8");
			System.out.println(result);
			if (result.contains("failure")) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"修改基金超市索引失败");
			}
			// 更新数据库
			log.info("修改基金超市索引-->从推荐基金下线");
			fundsMarket.setIsRecomm(AppCons.DEFAULT_IS_RECOMM);
			fundsMarket.setRecommOrder(AppCons.DEFAULT_RECOMM_ORDER);
			baseService.updateSelective(fundsMarket);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}

	}

	/**
	 * 设置推荐排序
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("setRecommOrder")
	@ResponseBody
	public Object setRecommOrder(FundsMarket vo) {
		System.out.println("设置推荐排序 fundsCode = " + vo.getFundsCode());
		try {
			if (StringUtils.isBlank(vo.getFundsCode())) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"请选择基金！");
			}
			if (vo.getIsRecomm().equals(AppCons.DEFAULT_IS_RECOMM)) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"该基金已从推荐基金下线，请勿重复设置！");
			}
			if (!vo.getRecommOrder().equals(AppCons.DEFAULT_RECOMM_ORDER)) {
				baseService.updateByRecommOrder(AppCons.DEFAULT_RECOMM_ORDER,
						vo.getRecommOrder());

				// 调用接口(排序规则不为999)
				List<FundsMarket> listFundsMarket = baseService
						.selectByRecommOrder(new BigDecimal(vo.getRecommOrder()));
				for (FundsMarket fundsMarket : listFundsMarket) {
					HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
					Map<String, String> map = new HashMap<String, String>();
					map.put("funds_code", fundsMarket.getFundsCode());
					map.put("create_time", DateUtils.getDateStr(
							fundsMarket.getCreateTime(), DateUtils.sdf1));
					map.put("is_recomm", fundsMarket.getIsRecomm() + "");
					map.put("recomm_order", AppCons.DEFAULT_RECOMM_ORDER + "");
					map.put("funds_code_inner", fundsMarket.getFundsCodeInner());
					map.put("recomm_reason", fundsMarket.getRecommReason());
					map.put("funds_theme", fundsMarket.getFundsTheme());
					String result = post.doPost(PropertiesUtil
							.getPropertiesByKey("fundsMarketUpdate"), map,
							"UTF-8");
					System.out.println(result);
					log.info("修改基金超市索引-->设置推荐排序(将原来的1或2或3设置为999)");
				}
			}
			baseService.updateSelective(vo);
			FundsMarket fundsMarket = baseService.findByPk(vo.getFundsCode());
			if (fundsMarket == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"请选择基金！");
			}

			HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
			Map<String, String> map = new HashMap<String, String>();
			map.put("funds_code", fundsMarket.getFundsCode());
			map.put("create_time", DateUtils.getDateStr(
					fundsMarket.getCreateTime(), DateUtils.sdf1));
			map.put("is_recomm", fundsMarket.getIsRecomm() + "");
			map.put("recomm_order", fundsMarket.getIsRecomm() + "");
			map.put("funds_code_inner", fundsMarket.getFundsCodeInner());
			map.put("recomm_reason", fundsMarket.getRecommReason());
			map.put("funds_theme", fundsMarket.getFundsTheme());
			String result = post.doPost(
					PropertiesUtil.getPropertiesByKey("fundsMarketUpdate"),
					map, "UTF-8");
			System.out.println(result);
			log.info("修改基金超市索引-->设置推荐排序");

			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}

	}

	/**
	 * 从基金超市下线
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("offlineFromFundsMarket")
	@ResponseBody
	public Object offlineFromFundsMarket(String fundsCode,
			String fundsCodeInner, String offlineNotes) {
		System.out.println("从基金超市下线 fundsCode = " + fundsCode);
		try {
			if (StringUtils.isBlank(fundsCode)
					|| StringUtils.isBlank(fundsCodeInner)) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"该基金已从基金超市下线，请勿重复设置！");
			}

			// 调用接口
			HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
			Map<String, String> map = new HashMap<String, String>();
			map.put("funds_code", fundsCode);
			String result = post.doPost(
					PropertiesUtil.getPropertiesByKey("fundsMarketDelete"),
					map, "UTF-8");
			System.out.println(result);
			if (result.contains("failure")) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"删除基金超市索引失败");
			}
			// 更新数据库
			log.info("删除基金超市索引-->从基金超市下线");
			// 从基金超市中删除该基金
			baseService.deleteByPk(fundsCode);
			// 基金超市下线日志新增记录
			FundsMarketLog fundsMarketLog = new FundsMarketLog();
			fundsMarketLog.setCreateTime(new Date());
			fundsMarketLog.setFundsCode(fundsCode);
			fundsMarketLog.setOfflineNotes(offlineNotes);
			fundsMarketLog.setFundsCodeInner(fundsCodeInner);
			fundsMarketLogService.save(fundsMarketLog);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}

	}

	/**
	 * 查询推荐理由
	 * 
	 * @param fundsCode
	 * @return
	 */
	@RequestMapping("searchRecommReason")
	@ResponseBody
	public Object searchRecommReason(String fundsCode) {
		System.out.println("查询推荐理由 fundsCode = " + fundsCode);
		if (StringUtils.isBlank(fundsCode)) {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "请选择基金！");
		}
		FundsMarket fundsMarket = baseService.findByPk(fundsCode);
		try {
			if (fundsMarket == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"请选择基金！");
			}
			return ResponseUtils.success(fundsMarket.getRecommReason());
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}

	}

	/**
	 * 设置推荐理由
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("setRecommReason")
	@ResponseBody
	public Object setRecommReason(FundsMarket vo) {
		if (StringUtils.isBlank(vo.getFundsCode())) {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "请选择基金！");
		}
		try {
			System.out.println("设置推荐理由 fundsCode = " + vo.getFundsCode());
			
			FundsMarket fundsMarket = baseService.findByPk(vo.getFundsCode());
			if (fundsMarket == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"请选择基金！");
			}
			HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
			Map<String, String> map = new HashMap<String, String>();
			map.put("funds_code", fundsMarket.getFundsCode());
			map.put("create_time", DateUtils.getDateStr(
					fundsMarket.getCreateTime(), DateUtils.sdf1));
			map.put("is_recomm", fundsMarket.getIsRecomm() + "");
			map.put("recomm_order", fundsMarket.getIsRecomm() + "");
			map.put("funds_code_inner", fundsMarket.getFundsCodeInner());
			map.put("recomm_reason", vo.getRecommReason());
			map.put("funds_theme", fundsMarket.getFundsTheme());
			String result = post.doPost(
					PropertiesUtil.getPropertiesByKey("fundsMarketUpdate"),
					map, "UTF-8");
			System.out.println(result);
			if (result.contains("failure")) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"修改基金超市索引失败");
			}
			// 更新数据库
			log.info("修改基金超市索引-->设置推荐理由");
			baseService.updateSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	/**
	 * 设置基金主题
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("setFundsTheme")
	@ResponseBody
	public Object setFundsTheme(FundsMarket vo) {
		if (StringUtils.isBlank(vo.getFundsCode())) {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "请选择基金！");
		}

		try {
			System.out.println("设置基金主题 fundsCode = " + vo.getFundsCode());
			
			FundsMarket fundsMarket = baseService.findByPk(vo.getFundsCode());
			if (fundsMarket == null) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"请选择基金！");
			}

			HttpClientUtil_Market_TEST post = new HttpClientUtil_Market_TEST();
			Map<String, String> map = new HashMap<String, String>();
			map.put("funds_code", fundsMarket.getFundsCode());
			map.put("create_time", DateUtils.getDateStr(
					fundsMarket.getCreateTime(), DateUtils.sdf1));
			map.put("is_recomm", fundsMarket.getIsRecomm() + "");
			map.put("recomm_order", fundsMarket.getIsRecomm() + "");
			map.put("funds_code_inner", fundsMarket.getFundsCodeInner());
			map.put("recomm_reason", fundsMarket.getRecommReason());
			map.put("funds_theme", vo.getFundsTheme());
			String result = post.doPost(
					PropertiesUtil.getPropertiesByKey("fundsMarketUpdate"),
					map, "UTF-8");
			System.out.println(result);
			if (result.contains("failure")) {
				return ResponseUtils.failure(ResponseUtils.ERROR_PARAM,
						"修改基金超市索引失败");
			}
			// 更新数据库
			log.info("修改基金超市索引-->设置推荐理由");
			baseService.updateSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}
}
