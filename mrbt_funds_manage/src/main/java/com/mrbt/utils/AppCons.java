package com.mrbt.utils;

import java.util.List;
import java.util.Map;

import com.mrbt.oa.mvc.vo.funds.FundsPackageCurve;

/**
 * oa的常量类
 * 
 * @author airgilbert
 *
 */
public class AppCons {

	public static String FUNDSMARKET_ADD = "http://192.168.1.88:9999/mrbt_funds_solrj/markettable/add";
	public static String FUNDSMARKET_DELETE = "http://192.168.1.88:9999/mrbt_funds_solrj/markettable/delete";
	public static String FUNDSMARKET_UPDATE = "http://192.168.1.88:9999/mrbt_funds_solrj/markettable/update";

	/**
	 * 默认请求list的limit长度
	 */
	public static int DEFAULT_PAGE_SIZE = 10;
	/**
	 * 用户添加的初始化密码
	 */
	public static String INIT_PASSWORD = "000000";

	/**
	 * 存入session的对象名称
	 */
	public static String SESSION_USER = "CURRENT_USER";
	/**
	 * 页面级别的前缀
	 */
	public static String PAGE_PREFFIX = "rest/";
	/**
	 * 默认数据库状态为ok
	 */
	public static int STATUS_OK = 0;
	/**
	 * 菜单类型为事件
	 */
	public static int MENY_TYPE_EVENT = 2;
	/**
	 * 权限控制的基本路径
	 */
	public static String BASE_URL = "/rest/forward/";

	/**
	 * 是否推荐,0->否，1->是，默认0
	 */
	public static Short DEFAULT_IS_RECOMM = 0;
	/**
	 * 推荐排序,默认999，只能有1,2,3,只能有一个，如果设置一个新的1，要把上一个1替换成999
	 */
	public static Short DEFAULT_RECOMM_ORDER = 999;

	/**
	 * 是否上线,0->否，1->是，默认1
	 */
	public static Short DEFAULT_ON_LINE = 1;
	/**
	 * 操作方式,0->定时任务，1->手动，默认0
	 */
	public static Short DEFAULT_OPERATE_STYLE = 0;
	/**
	 * 默认图片大小
	 */
	public static long DEFAULT_IMAGE_SIZE = 10;
	/**
	 * redis key = 基金组合首次计算时间+基金组合类型id
	 */
	public static String FUNDS_PACKAGE_CURVE_OLD_DATE_ = "FUNDS_PACKAGE_CURVE_OLD_DATE_";
	/**
	 * redis key = 基金组合曲线+基金组合类型id, value = List<Map>
	 */
	public static String FUNDS_PACKAGE_CURVE_ = "FUNDS_PACKAGE_CURVE_";
	/**
	 * redis key = 基金组合基金类型占比前缀+基金组合类型id, value = Map<String, Integer> <基金类型，个数> {"债券型":3,"保本型":1}
	 */
	public static String FUNDS_PACKAGE_FUNDS_TYPE_RATIO_ = "FUNDS_PACKAGE_FUNDS_TYPE_RATIO_";

}
