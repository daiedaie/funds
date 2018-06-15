package com.mrbt.user.model;

/**
 * 常量类
 * 
 * @author lzp
 *
 */
public class AppCons {

	


	public static int DEFAULT_PAGE_SIZE = 10;
	public static String USER_PICTURE_DIR = "TUPAN";
	public static String COMPANY_URL="http://www.lingmoney.com.cn/regist.html";
	
	//收益类型0:固定收益类，1:浮动类
	public static int PROFIT_TYPE_0 = 0 ; 
	public static int PROFIT_TYPE_1 = 1;
	//	0:无,1:固定不变，2:区间;
	public static int PROFIT_FIX_TYPE_0 = 0;
	public static int PROFIT_FIX_TYPE_1 = 1;
	public static int PROFIT_FIX_TYPE_2 = 2;
	
	public enum CostType {
		pre_cost(0, "前置费用收取"), after_cost(1, "后置费用收取");
		private int code;
		private String msg;

		private CostType(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
	}

	
	/**
	 * 默认当前页为第一页
	 */
	public static int DEFAULT_PAGE_START = 1;
	/**
	 * 初始化用户密码
	 */
	public static String DEFAULT_PSW = "0000";
	/**
	 * 默认的可删除的状态代码
	 */
	public static Integer DEFAULT_STATE = 1;
	/**
	 * 存入session的对象名称
	 */
	public static String SESSION_USER = "CURRENT_USER";
	/**
	 * 页面级别的前缀
	 */
	public static String PAGE_PREFFIX = "rest/forward/";

	/**
	 * 交易的时间
	 */
	public static int TRAD_HOUR = 16;

	/**
	 * 无规则限制
	 */
	public static int RULE_NONE = 3;
	/**
	 * 金额限制
	 */
	public static int RULE_MONEY = 0;
	/**
	 * 时间限制
	 */
	public static int RULE_DATE = 1;
	/**
	 * 金额时间双重限制
	 */
	public static int RULE_MONEY_DATE = 2;
	/**
	 * 单位为月，value=1
	 */
	public static int UNIT_TIME_MONTH = 2;
	/**
	 * 单位为天，value=0
	 */
	public static int UNIT_TIME_DAY = 0;

	/**
	 * 单位为周，value=2
	 */
	public static int UNIT_TIME_WEEK = 1;

	/**
	 * 单位为无，value=3
	 */
	public static int UNIT_TIME_NONE = 4;
	/**
	 * 购买的状态 0
	 */
	public static int BUY_STATUS = 0;

	/**
	 * 买入的支付成功状态 1
	 */
	public static int BUY_OK = 1;

	/**
	 * 卖出的状态2
	 */
	public static int SELL_STATUS = 2;

	/**
	 * 卖出的支付成功状态3
	 */
	public static int SELL_OK = 3;
	/**
	 * 固定收益类的标志 value=0
	 */
	public static int FIX_FLAG = 0;
	/**
	 * 固定类产品子类型为固定不变 value=1
	 */
	public static int FIX_FLAG_SUB_FIX = 1;

	/**
	 * 固定类产品子类型为区间 value=2
	 */
	public static int FIX_FLAG_SUB_AREA = 2;
	/**
	 * 浮动收益类的标志
	 */
	public static int FLOAT_FLAG = 1;

	/**
	 * 默认的产品分类类型(浮动)
	 */
	public static int DEFAULT_TYPE = 1;
	/**
	 * 收取佣金标志，value=1
	 */
	public static int FEES_COST = 1;
	/**
	 * 不收取佣金标志，value=0
	 */
	public static int FEES_NONE = 0;
	/**
	 * 审批通过的产品 value=2
	 */
	public static int PRODUCT_APPROVAL_OK = 2;
	/**
	 * 审批提交的产品 value=1
	 */
	public static int PRODUCT_APPROVAL_SUTMIT = 1;

	/**
	 * 审批初始化的产品 value=0
	 */
	public static int PRODUCT_APPROVAL_INIT = 0;
	/**
	 * 提交状态的产品
	 */
	public static int PRODUCT_APPROVAL_SUBMIT = 1;
	/**
	 * 审核通过的产品
	 */
	public static int PRODUCT_APPROVAL_PUBLISH = 2;
	/**
	 * 项目筹集期 value=1
	 */
	public static int PRODUCT_STATUS_READY = 1;

	/**
	 * 项目运行期 value=2
	 */
	public static int PRODUCT_STATUS_RUN = 2;
	/**
	 * 发布状态
	 */
	public static int PUBLIST_STATUS = 1;
	
	public static int LINT_BAO_FRIST_COUNT=1000;
	public static int LINT_BAO_FRIST_COUNT_T=500;
	
	public static String  USER_BUY_REQUESTNO = "USER_BUY_REQUESTNO";
	public static String  USER_ZHIC_REQUESTNO ="USER_ZHIC_REQUESTNO";
	public static String  USER_TIANX_REQUESTNO ="USER_TIANX_REQUESTNO";
	//public static String  USER_SELL_REQUESTNO ="USER_TIANX_REQUESTNO";
	
	public static String HOMR_BANNER_INFRO="HOMR_BANNER_INFRO";
	public static String HOMR_USER_ASK="HOMR_USER_ASK";
	public static String HOMR_MEDIA="HOMR_MEDIA";
	public static String HOMR_NOTICE="HOMR_NOTICE";
	public static String HOMR_LISTX="HOMR_LISTX";
	public static String HOMR_LISTY="HOMR_LISTY";
	public static String USER_NAME_PREFIX="lingqian";
	
	public static String HOME_CARTOONCATEGORY="HOME_CARTOONCATEGORY";
	public static String HOME_CARTOONCONTENT="HOME_CARTOONCONTENT";
	public static String HOME_FINANCIALMANAGEMENT="HOME_FINANCIALMANAGEMENT";
	public static String HOMELIST_FINANCIALMANAGEMENT="HOMELIST_FINANCIALMANAGEMENT";
	
	public static String pay_certificate_path=Thread.currentThread().getContextClassLoader().getResource("").getPath() +"hk1001001@test.com.p12.pfx";
	public static String pay_certificate_password="123qwe";
	public static String pay_certificate_String="yeepay.com";
	
	/*
	注册阶段：

	1、您正在注册领钱儿，您的短信验证码是 XXXXX，为了保障您的账号安全，验证短信请勿泄露给其他人，此验证码一分钟内有效，请尽快使用。【领钱儿】

	2、尊敬的XXX（此为用户名），欢迎您注册领钱儿。领钱儿已将1000领宝放入您的账户中，快去查收吧。关注微信（lingqianer888）接收消息更便捷。【领钱儿】


	充值提醒：

	【领钱儿】亲爱的XXX，您刚刚在领钱儿进行了充值，金额为XX元。如非本人操作，请致电400-005-1655或联系微信客服(lingqianer888)。


	购买提醒：

	【领钱儿】亲爱的XXX，您刚刚成功认购XXX(产品名称)，认购金额XXXX元。如有疑问，请致电400-005-1655或微信客服(lingqianer888)。

	赎回提醒：

	【领钱儿】亲爱的XXX，您在XXX年X月X日于领钱儿申请的XXX（产品）已赎回，金额为XX元。如非本人操作，请致电400-005-1655。*/
	public static String regist_content_y="您正在注册领钱儿，您的短信验证码是{0}，为了保障您的账号安全，验证短信请勿泄露给其他人，此验证码{1}分钟内有效，请尽快使用。";
	public static String regist_content="欢迎您注册领钱儿。领钱儿已将1000领宝放入您的账户中，快去查收吧。";
	
	public static String chongzhi_content="亲爱的{0}，您刚刚在领钱儿进行了充值，金额为{1}元。如非本人操作，请致电400-005-1655或联系微信客服(lingqianer888)。";
	public static String buy_content="亲爱的{0}，您刚刚成功认购{1}，认购金额{2}元。如有疑问，请致电400-005-1655或微信客服(lingqianer888)。";
 
	public static String sell_content="亲爱的{0}，您在{1}于领钱儿申请的{2}已赎回，金额为{3}元。如非本人操作，请致电400-005-1655。";
	
	public static String password_content_y="您的短信验证码是{0}，为了保障您的账号安全，验证短信请勿泄露给其他人，此验证码{1}分钟内有效，请尽快使用。";
	
	public static String fist_tianx="fist_tianx";
	
	public static String authentication = "authentication";
	
	public static String fist_chongzhi="fist_chongzhi";
	public static String password_code ="password_code";
	public static String  login_error_verification="login_error_verification";
	
	
	public static String regist_content_new="尊敬的{0}，您好！恭喜您成功注册“领钱儿”网理财平台。为提升您的账户安全，请尽快完成实名认证吧！";
	public static String certification_content="恭喜您已完成实名认证，领钱儿已将100领宝放入您的账户中，快去看看吧！";
	
	public static String  untreated_request_no = "untreated_request_no";
	
}
