package com.mrbt.oa.mvc.controller.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mrbt.oa.mvc.service.funds.FundsPackageRatioService;
import com.mrbt.oa.mvc.service.funds.FundsPackageTypeService;
import com.mrbt.oa.mvc.service.source.Ths043Service;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.DateUtils;
import com.mrbt.utils.MyUtils;
import com.mrbt.utils.redis.RedisDao;

@Component
public class AutoAddFundsPackageCurveTask extends ApplicationObjectSupport {

	public Logger log = MyUtils.getLogger(AutoAddFundsPackageCurveTask.class);
	@Autowired
	public FundsPackageTypeService fundsPackageTypeService;
	@Autowired
	public FundsPackageRatioService fundsPackageRatioService;
	@Autowired
	public Ths043Service ths043Service;
	@Autowired
	public RedisDao redisDao;

	// 每1分钟触发一次
	// @Scheduled(cron = "0 0/5 * * * ? ")
	// 0 15 0 ? * *
	// 每天早上0：15触发
	//@Scheduled(cron = "0 49 17 ? * *")
	//@Test
	public void autoAddFundsPackageCurve() throws Exception {
		try {

			List<BigDecimal> listbd = fundsPackageTypeService.selectAllId();

			for (BigDecimal bigDecimal : listbd) {
				Date oldDate = (Date) redisDao
						.get(AppCons.FUNDS_PACKAGE_CURVE_OLD_DATE_ + bigDecimal);
				System.out.println("bigDecimal = " + bigDecimal + " oldDate = "
						+ oldDate);
				fundsPackageTypeService
						.generateCurve(bigDecimal, oldDate,
								DateUtils.getYesterday(),
								AppCons.DEFAULT_OPERATE_STYLE);
			}
			System.out.println("生成了" + listbd.size() + "个组合宝的基金曲线");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Scheduled(cron = "0 0/1 * * * ?")
	public void aaa() throws Exception {
		try {

			System.out.println("123456789");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
