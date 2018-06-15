package com.mrbt.oa.mvc.service.funds;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.funds.FundsPackageTypeDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.service.source.Ths043Service;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurve;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurveLog;
import com.mrbt.oa.mvc.vo.funds.FundsPackageRatio;
import com.mrbt.oa.mvc.vo.funds.FundsPackageType;
import com.mrbt.oa.mvc.vo.funds.FundsPackageTypeExample;
import com.mrbt.oa.mvc.vo.source.Ths043;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.BigDecimalUtils;
import com.mrbt.utils.MyUtils;
import com.mrbt.utils.NumberUtils;
import com.mrbt.utils.redis.RedisDao;

@Service
public class FundsPackageTypeService extends
		BaseService<FundsPackageType, FundsPackageTypeDao> {
	public Logger log = MyUtils.getLogger(FundsPackageTypeService.class);
	@Autowired
	public FundsPackageRatioService fundsPackageRatioService;
	@Autowired
	public FundsPackageCurveService fundsPackageCurveService;
	@Autowired
	public FundsPackageCurveLogService fundsPackageCurveLogService;
	@Autowired
	public Ths043Service ths043Service;
	@Autowired
	private RedisDao redisDao;

	@Override
	public FundsPackageTypeExample getExample(FundsPackageType vo) {
		FundsPackageTypeExample example = new FundsPackageTypeExample();
		FundsPackageTypeExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getName())) {
			cri.andNameLike("%" + vo.getName() + "%");
		}
		example.setOrderByClause("id");
		return example;
	}

	public List<BigDecimal> selectAllId() {
		return this.generalDao.selectAllId();
	}

	/**
	 * 生成基金组合曲线(保存到数据库和redis)
	 * 
	 * @param typeId
	 *            基金组合id
	 */
	public List<Map<String, Object>> generateCurve(BigDecimal typeId,
			Date oldDate, Date newDate, short operateStyle) {
		/*
		 * String dateStr = "2015-07-06"; try { oldDate =
		 * DateUtils.sdf2.parse(dateStr); } catch (ParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		// 基金曲线集合
		List<FundsPackageCurve> list = new ArrayList<FundsPackageCurve>();
		boolean flag = false;
		// 基金组合类型
		FundsPackageType fundsPackageType = this.generalDao.listById(typeId);
		if (fundsPackageType != null) {
			// 查询基金比例根据基金组合类型
			FundsPackageRatio fundsPackageRatio = new FundsPackageRatio();
			fundsPackageRatio.setTypeId(typeId);
			List<FundsPackageRatio> listFundsPackageRatio = fundsPackageRatioService
					.list(fundsPackageRatio, null);
			if (listFundsPackageRatio == null
					|| listFundsPackageRatio.size() <= 0) {
				return null;
			}
			for (FundsPackageRatio fundsPackageRatio2 : listFundsPackageRatio) {
				System.out.println(fundsPackageRatio2.toString());
			}

			// 基金组合中有1个基金
			if (listFundsPackageRatio.size() == 1) {
				// 每个基金组合里的基金
				FundsPackageRatio fr1 = listFundsPackageRatio.get(0);
				BigDecimal x1 = fr1.getRatio();// x1所占比例
				System.out.println("x1 = " + x1);
				/*
				 * // 根据基日时间查询基日净值 Ths043 ths043_base = new Ths043();
				 * ths043_base.setF001Ths043(fr1.getFundsCodeInner());
				 * ths043_base.setF002Ths043(oldDate); List<Ths043>
				 * listThs043_base = ths043Service.list(ths043_base, new
				 * RowBounds(0, 1)); BigDecimal ths043_base2 = new
				 * BigDecimal(1);// f1基日净值 if (listThs043_base != null &&
				 * listThs043_base.size() > 0) { // 基金净值表(截取当前基金第一条记录) Ths043
				 * ths043_1 = listThs043_base.get(0); f1 =
				 * ths043_1.getF005Ths043(); } System.out.println("f1 = " + f1);
				 */
				System.out.println(oldDate);
				System.out.println(newDate);
				// 获取两个时间段之间的净值数据
				Ths043 ths043_cal = new Ths043();
				ths043_cal.setF001Ths043(fr1.getFundsCodeInner());
				List<Ths043> listThs043_cal = ths043Service.listByDate(
						ths043_cal, oldDate, newDate, null);// 第1个基金两个时间段之间的净值数据

				if (listThs043_cal != null && listThs043_cal.size() > 0) {
					BigDecimal f1 = new BigDecimal(1);// f1基日净值
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base = listThs043_cal.get(0);
					f1 = ths043_base.getF005Ths043();
					System.out.println("f1 = " + f1);
					for (int i = 0; i < listThs043_cal.size(); i++) {
						// 基金组合曲线帮助类
						FundsPackageCurve fc1 = new FundsPackageCurve();
						BigDecimal y1 = listThs043_cal.get(i).getF005Ths043();// y1计算日净值
						Double d = ((x1.multiply(y1).doubleValue()) / 100 / f1
								.doubleValue());// 计算涨幅
						BigDecimal rate = BigDecimalUtils.money(4,
								new BigDecimal(d));
						fc1.setcDate(listThs043_cal.get(i).getF002Ths043());
						fc1.setcData(rate);
						fc1.setTypeId(typeId);
						list.add(fc1);
					}
					for (FundsPackageCurve fundsPackageCurve : list) {
						System.out.println(fundsPackageCurve.toString());
					}
				}

			}

			// 基金组合中有2个基金
			if (listFundsPackageRatio.size() == 2) {
				FundsPackageRatio frp1 = listFundsPackageRatio.get(0);
				FundsPackageRatio frp2 = listFundsPackageRatio.get(1);
				BigDecimal xp1 = frp1.getRatio();
				BigDecimal xp2 = frp2.getRatio();

				Ths043 ths043_cal1 = new Ths043();
				ths043_cal1.setF001Ths043(frp1.getFundsCodeInner());
				List<Ths043> listThs043_calp1 = ths043Service.listByDate(
						ths043_cal1, oldDate, newDate, null);// 第1个基金两个时间段之间的净值数据
				Ths043 ths043_cal2 = new Ths043();
				ths043_cal2.setF001Ths043(frp2.getFundsCodeInner());
				List<Ths043> listThs043_calp2 = ths043Service.listByDate(
						ths043_cal2, oldDate, newDate, null);// 第2个基金两个时间段之间的净值数据
				int size1 = 0;
				if (listThs043_calp1 != null && listThs043_calp1.size() > 0) {
					size1 = listThs043_calp1.size();
				}
				int size2 = 0;
				if (listThs043_calp2 != null && listThs043_calp2.size() > 0) {
					size2 = listThs043_calp2.size();
				}
				FundsPackageRatio fr1;
				FundsPackageRatio fr2;
				BigDecimal x1;
				BigDecimal x2;
				List<Ths043> listThs043_cal1 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal2 = new ArrayList<Ths043>();
				System.out.println("长度分别为：" + size1 + " " + size2);
				int size = NumberUtils.getMaxNum(size1, size2);
				System.out.println("最大的为： " + size);
				// 比较每支支基金对应的净值个数
				if (size1 == size) {
					fr1 = frp1;
					fr2 = frp2;
					x1 = xp1;
					x2 = xp2;
					listThs043_cal1 = listThs043_calp1;
					listThs043_cal2 = listThs043_calp2;
				} else {
					fr1 = frp2;
					fr2 = frp1;
					x1 = xp2;
					x2 = xp1;
					listThs043_cal1 = listThs043_calp2;
					listThs043_cal2 = listThs043_calp1;
				}
				System.out.println("x1 = " + x1);
				System.out.println("x2 = " + x2);

				BigDecimal f1 = new BigDecimal(1);// f1基日净值
				// 基金净值表(截取当前基金第一条记录)
				Ths043 ths043_base1 = listThs043_cal1.get(0);
				f1 = ths043_base1.getF005Ths043();
				System.out.println("f1 = " + f1);
				// 第一个基金基日
				Date baseDate1 = ths043_base1.getF002Ths043();
				// 第二个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_2 = new Ths043();
				ths043_2.setF001Ths043(fr2.getFundsCodeInner());
				ths043_2.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base2 = ths043Service.list(ths043_2,
						new RowBounds(0, 1));
				BigDecimal f2 = new BigDecimal(1);// f2基日净值
				if (listThs043_base2 != null && listThs043_base2.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base2 = listThs043_base2.get(0);
					f2 = ths043_base2.getF005Ths043();
				}
				System.out.println("f2 = " + f2);

				// 第一个基金净值放入map
				Map<Date, BigDecimal> map1 = new HashMap<Date, BigDecimal>();
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					map1.put(listThs043_cal1.get(i).getF002Ths043(),
							listThs043_cal1.get(i).getF005Ths043());
				}

				// 第二个基金净值放入map
				Map<Date, BigDecimal> map2 = new HashMap<Date, BigDecimal>();
				map2.put(baseDate1, f2);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal2.size(); i++) {
					map2.put(listThs043_cal2.get(i).getF002Ths043(),
							listThs043_cal2.get(i).getF005Ths043());
				}
				// 如果不存在该日期，或该日期下净值为null，则将上一个值添加到map中
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					if (!map2.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map2.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map2.put(listThs043_cal1.get(i).getF002Ths043(),
								map2.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
				}
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					FundsPackageCurve fc1 = new FundsPackageCurve();
					BigDecimal y1 = listThs043_cal1.get(i).getF005Ths043();
					BigDecimal y2 = map2.get(listThs043_cal1.get(i)
							.getF002Ths043());
					Double d = ((x1.multiply(y1).doubleValue()) / 100 / f1
							.doubleValue())
							+ ((x2.multiply(y2).doubleValue()) / 100 / f2
									.doubleValue());
					BigDecimal rate = BigDecimalUtils.money(4,
							new BigDecimal(d));
					fc1.setcDate(listThs043_cal1.get(i).getF002Ths043());
					fc1.setcData(rate);
					fc1.setTypeId(typeId);
					list.add(fc1);
				}
				for (FundsPackageCurve fundsPackageCurve : list) {
					System.out.println(fundsPackageCurve.toString());
				}

			}

			// 基金组合中有3个基金
			if (listFundsPackageRatio.size() == 3) {
				// 每个基金组合里的基金
				FundsPackageRatio frp1 = listFundsPackageRatio.get(0);
				BigDecimal xp1 = frp1.getRatio();// x1所占比例
				FundsPackageRatio frp2 = listFundsPackageRatio.get(1);
				BigDecimal xp2 = frp2.getRatio();// x2所占比例
				FundsPackageRatio frp3 = listFundsPackageRatio.get(2);
				BigDecimal xp3 = frp3.getRatio();// x3所占比例

				Ths043 ths043_cal1 = new Ths043();
				ths043_cal1.setF001Ths043(frp1.getFundsCodeInner());
				List<Ths043> listThs043_calp1 = ths043Service.listByDate(
						ths043_cal1, oldDate, newDate, null);// 第1个基金两个时间段之间的净值数据
				Ths043 ths043_cal2 = new Ths043();
				ths043_cal2.setF001Ths043(frp2.getFundsCodeInner());
				List<Ths043> listThs043_calp2 = ths043Service.listByDate(
						ths043_cal2, oldDate, newDate, null);// 第2个基金两个时间段之间的净值数据
				Ths043 ths043_cal3 = new Ths043();
				ths043_cal3.setF001Ths043(frp3.getFundsCodeInner());
				List<Ths043> listThs043_calp3 = ths043Service.listByDate(
						ths043_cal3, oldDate, newDate, null);// 第3个基金两个时间段之间的净值数据
				int size1 = 0;
				if (listThs043_calp1 != null && listThs043_calp1.size() > 0) {
					size1 = listThs043_calp1.size();
				}
				int size2 = 0;
				if (listThs043_calp2 != null && listThs043_calp2.size() > 0) {
					size2 = listThs043_calp2.size();
				}
				int size3 = 0;
				if (listThs043_calp3 != null && listThs043_calp3.size() > 0) {
					size3 = listThs043_calp3.size();
				}
				FundsPackageRatio fr1;
				FundsPackageRatio fr2;
				FundsPackageRatio fr3;
				BigDecimal x1;
				BigDecimal x2;
				BigDecimal x3;
				List<Ths043> listThs043_cal1 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal2 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal3 = new ArrayList<Ths043>();
				System.out
						.println("长度分别为：" + size1 + " " + size2 + " " + size3);
				int size = NumberUtils.getMaxNum(size1, size2, size3);
				System.out.println("最大的为： " + size);
				// 比较每支支基金对应的净值个数
				if (size1 == size) {
					fr1 = frp1;
					fr2 = frp2;
					fr3 = frp3;
					x1 = xp1;
					x2 = xp2;
					x3 = xp3;
					listThs043_cal1 = listThs043_calp1;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp3;
				} else if (size2 == size) {
					fr1 = frp2;
					fr2 = frp1;
					fr3 = frp3;
					x1 = xp2;
					x2 = xp1;
					x3 = xp3;
					listThs043_cal1 = listThs043_calp2;
					listThs043_cal2 = listThs043_calp1;
					listThs043_cal3 = listThs043_calp3;
				} else {
					fr1 = frp3;
					fr2 = frp2;
					fr3 = frp1;
					x1 = xp3;
					x2 = xp2;
					x3 = xp1;
					listThs043_cal1 = listThs043_calp3;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp1;
				}
				System.out.println("x1 = " + x1);
				System.out.println("x2 = " + x2);
				System.out.println("x3 = " + x3);

				BigDecimal f1 = new BigDecimal(1);// f1基日净值
				// 基金净值表(截取当前基金第一条记录)
				Ths043 ths043_base1 = listThs043_cal1.get(0);
				f1 = ths043_base1.getF005Ths043();
				System.out.println("f1 = " + f1);
				// 第一个基金基日
				Date baseDate1 = ths043_base1.getF002Ths043();
				// 第二个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_2 = new Ths043();
				ths043_2.setF001Ths043(fr2.getFundsCodeInner());
				ths043_2.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base2 = ths043Service.list(ths043_2,
						new RowBounds(0, 1));
				BigDecimal f2 = new BigDecimal(1);// f2基日净值
				if (listThs043_base2 != null && listThs043_base2.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base2 = listThs043_base2.get(0);
					f2 = ths043_base2.getF005Ths043();
				}
				System.out.println("f2 = " + f2);
				// 第3个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_3 = new Ths043();
				ths043_3.setF001Ths043(fr3.getFundsCodeInner());
				ths043_3.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base3 = ths043Service.list(ths043_3,
						new RowBounds(0, 1));
				BigDecimal f3 = new BigDecimal(1);// f3基日净值
				if (listThs043_base3 != null && listThs043_base3.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base3 = listThs043_base3.get(0);
					f3 = ths043_base3.getF005Ths043();
				}
				System.out.println("f3 = " + f3);

				// 第1个基金净值放入map
				Map<Date, BigDecimal> map1 = new HashMap<Date, BigDecimal>();
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					map1.put(listThs043_cal1.get(i).getF002Ths043(),
							listThs043_cal1.get(i).getF005Ths043());
				}

				// 第2个基金净值放入map
				Map<Date, BigDecimal> map2 = new HashMap<Date, BigDecimal>();
				map2.put(baseDate1, f2);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal2.size(); i++) {
					map2.put(listThs043_cal2.get(i).getF002Ths043(),
							listThs043_cal2.get(i).getF005Ths043());
				}
				// 第3个基金净值放入map
				Map<Date, BigDecimal> map3 = new HashMap<Date, BigDecimal>();
				map3.put(baseDate1, f3);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal3.size(); i++) {
					map3.put(listThs043_cal3.get(i).getF002Ths043(),
							listThs043_cal3.get(i).getF005Ths043());
				}
				// 如果不存在该日期，或该日期下净值为null，则将上一个值添加到map中
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					if (!map2.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map2.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map2.put(listThs043_cal1.get(i).getF002Ths043(),
								map2.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
					if (!map3.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map3.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map3.put(listThs043_cal1.get(i).getF002Ths043(),
								map3.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
				}
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					FundsPackageCurve fc1 = new FundsPackageCurve();
					BigDecimal y1 = listThs043_cal1.get(i).getF005Ths043();
					BigDecimal y2 = map2.get(listThs043_cal1.get(i)
							.getF002Ths043());
					BigDecimal y3 = map3.get(listThs043_cal1.get(i)
							.getF002Ths043());
					Double d = ((x1.multiply(y1).doubleValue()) / 100 / f1
							.doubleValue())
							+ ((x2.multiply(y2).doubleValue()) / 100 / f2
									.doubleValue())
							+ ((x3.multiply(y3).doubleValue()) / 100 / f3
									.doubleValue());
					BigDecimal rate = BigDecimalUtils.money(4,
							new BigDecimal(d));
					fc1.setcDate(listThs043_cal1.get(i).getF002Ths043());
					fc1.setcData(rate);
					fc1.setTypeId(typeId);
					list.add(fc1);
				}
				for (FundsPackageCurve fundsPackageCurve : list) {
					System.out.println(fundsPackageCurve.toString());
				}
			}

			// 基金组合中有4个基金
			if (listFundsPackageRatio.size() == 4) {
				// 每个基金组合里的基金
				FundsPackageRatio frp1 = listFundsPackageRatio.get(0);
				BigDecimal xp1 = frp1.getRatio();// x1所占比例
				FundsPackageRatio frp2 = listFundsPackageRatio.get(1);
				BigDecimal xp2 = frp2.getRatio();// x2所占比例
				FundsPackageRatio frp3 = listFundsPackageRatio.get(2);
				BigDecimal xp3 = frp3.getRatio();// x3所占比例
				FundsPackageRatio frp4 = listFundsPackageRatio.get(3);
				BigDecimal xp4 = frp4.getRatio();// x4所占比例

				Ths043 ths043_cal1 = new Ths043();
				ths043_cal1.setF001Ths043(frp1.getFundsCodeInner());
				List<Ths043> listThs043_calp1 = ths043Service.listByDate(
						ths043_cal1, oldDate, newDate, null);// 第1个基金两个时间段之间的净值数据
				Ths043 ths043_cal2 = new Ths043();
				ths043_cal2.setF001Ths043(frp2.getFundsCodeInner());
				List<Ths043> listThs043_calp2 = ths043Service.listByDate(
						ths043_cal2, oldDate, newDate, null);// 第2个基金两个时间段之间的净值数据
				Ths043 ths043_cal3 = new Ths043();
				ths043_cal3.setF001Ths043(frp3.getFundsCodeInner());
				List<Ths043> listThs043_calp3 = ths043Service.listByDate(
						ths043_cal3, oldDate, newDate, null);// 第3个基金两个时间段之间的净值数据
				Ths043 ths043_cal4 = new Ths043();
				ths043_cal4.setF001Ths043(frp4.getFundsCodeInner());
				List<Ths043> listThs043_calp4 = ths043Service.listByDate(
						ths043_cal4, oldDate, newDate, null);// 第4个基金两个时间段之间的净值数据
				int size1 = 0;
				if (listThs043_calp1 != null && listThs043_calp1.size() > 0) {
					size1 = listThs043_calp1.size();
				}
				int size2 = 0;
				if (listThs043_calp2 != null && listThs043_calp2.size() > 0) {
					size2 = listThs043_calp2.size();
				}
				int size3 = 0;
				if (listThs043_calp3 != null && listThs043_calp3.size() > 0) {
					size3 = listThs043_calp3.size();
				}
				int size4 = 0;
				if (listThs043_calp4 != null && listThs043_calp4.size() > 0) {
					size4 = listThs043_calp4.size();
				}
				FundsPackageRatio fr1;
				FundsPackageRatio fr2;
				FundsPackageRatio fr3;
				FundsPackageRatio fr4;
				BigDecimal x1;
				BigDecimal x2;
				BigDecimal x3;
				BigDecimal x4;
				List<Ths043> listThs043_cal1 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal2 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal3 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal4 = new ArrayList<Ths043>();
				System.out.println("长度分别为：" + size1 + " " + size2 + " " + size3
						+ " " + size4);
				int size = NumberUtils.getMaxNum(size1, size2, size3, size4);
				System.out.println("最大的为： " + size);
				// 比较每支支基金对应的净值个数
				if (size1 == size) {
					fr1 = frp1;
					fr2 = frp2;
					fr3 = frp3;
					fr4 = frp4;
					x1 = xp1;
					x2 = xp2;
					x3 = xp3;
					x4 = xp4;
					listThs043_cal1 = listThs043_calp1;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp3;
					listThs043_cal4 = listThs043_calp4;
				} else if (size2 == size) {
					fr1 = frp2;
					fr2 = frp1;
					fr3 = frp3;
					fr4 = frp4;
					x1 = xp2;
					x2 = xp1;
					x3 = xp3;
					x4 = xp4;
					listThs043_cal1 = listThs043_calp2;
					listThs043_cal2 = listThs043_calp1;
					listThs043_cal3 = listThs043_calp3;
					listThs043_cal4 = listThs043_calp4;
				} else if (size3 == size) {
					fr1 = frp3;
					fr2 = frp2;
					fr3 = frp1;
					fr4 = frp4;
					x1 = xp3;
					x2 = xp2;
					x3 = xp1;
					x4 = xp4;
					listThs043_cal1 = listThs043_calp3;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp1;
					listThs043_cal4 = listThs043_calp4;
				} else {
					fr1 = frp4;
					fr2 = frp2;
					fr3 = frp3;
					fr4 = frp1;
					x1 = xp4;
					x2 = xp2;
					x3 = xp3;
					x4 = xp1;
					listThs043_cal1 = listThs043_calp4;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp3;
					listThs043_cal4 = listThs043_calp1;
				}
				System.out.println("x1 = " + x1);
				System.out.println("x2 = " + x2);
				System.out.println("x3 = " + x3);
				System.out.println("x4 = " + x4);

				BigDecimal f1 = new BigDecimal(1);// f1基日净值
				// 基金净值表(截取当前基金第一条记录)
				Ths043 ths043_base1 = listThs043_cal1.get(0);
				f1 = ths043_base1.getF005Ths043();
				System.out.println("f1 = " + f1);
				// 第1个基金基日
				Date baseDate1 = ths043_base1.getF002Ths043();
				// 第2个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_2 = new Ths043();
				ths043_2.setF001Ths043(fr2.getFundsCodeInner());
				ths043_2.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base2 = ths043Service.list(ths043_2,
						new RowBounds(0, 1));
				BigDecimal f2 = new BigDecimal(1);// f2基日净值
				if (listThs043_base2 != null && listThs043_base2.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base2 = listThs043_base2.get(0);
					f2 = ths043_base2.getF005Ths043();
				}
				System.out.println("f2 = " + f2);
				// 第3个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_3 = new Ths043();
				ths043_3.setF001Ths043(fr3.getFundsCodeInner());
				ths043_3.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base3 = ths043Service.list(ths043_3,
						new RowBounds(0, 1));
				BigDecimal f3 = new BigDecimal(1);// f3基日净值
				if (listThs043_base3 != null && listThs043_base3.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base3 = listThs043_base3.get(0);
					f3 = ths043_base3.getF005Ths043();
				}
				System.out.println("f3 = " + f3);
				// 第4个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_4 = new Ths043();
				ths043_4.setF001Ths043(fr4.getFundsCodeInner());
				ths043_4.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base4 = ths043Service.list(ths043_4,
						new RowBounds(0, 1));
				BigDecimal f4 = new BigDecimal(1);// f4基日净值
				if (listThs043_base4 != null && listThs043_base4.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base4 = listThs043_base4.get(0);
					f4 = ths043_base4.getF005Ths043();
				}
				System.out.println("f4 = " + f4);

				// 第1个基金净值放入map
				Map<Date, BigDecimal> map1 = new HashMap<Date, BigDecimal>();
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					map1.put(listThs043_cal1.get(i).getF002Ths043(),
							listThs043_cal1.get(i).getF005Ths043());
				}

				// 第2个基金净值放入map
				Map<Date, BigDecimal> map2 = new HashMap<Date, BigDecimal>();
				map2.put(baseDate1, f2);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal2.size(); i++) {
					map2.put(listThs043_cal2.get(i).getF002Ths043(),
							listThs043_cal2.get(i).getF005Ths043());
				}
				// 第3个基金净值放入map
				Map<Date, BigDecimal> map3 = new HashMap<Date, BigDecimal>();
				map3.put(baseDate1, f3);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal3.size(); i++) {
					map3.put(listThs043_cal3.get(i).getF002Ths043(),
							listThs043_cal3.get(i).getF005Ths043());
				}
				// 第4个基金净值放入map
				Map<Date, BigDecimal> map4 = new HashMap<Date, BigDecimal>();
				map4.put(baseDate1, f4);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal4.size(); i++) {
					map4.put(listThs043_cal4.get(i).getF002Ths043(),
							listThs043_cal4.get(i).getF005Ths043());
				}
				// 如果不存在该日期，或该日期下净值为null，则将上一个值添加到map中
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					if (!map2.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map2.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map2.put(listThs043_cal1.get(i).getF002Ths043(),
								map2.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
					if (!map3.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map3.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map3.put(listThs043_cal1.get(i).getF002Ths043(),
								map3.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
					if (!map4.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map4.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map4.put(listThs043_cal1.get(i).getF002Ths043(),
								map4.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
				}
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					FundsPackageCurve fc1 = new FundsPackageCurve();
					BigDecimal y1 = listThs043_cal1.get(i).getF005Ths043();
					BigDecimal y2 = map2.get(listThs043_cal1.get(i)
							.getF002Ths043());
					BigDecimal y3 = map3.get(listThs043_cal1.get(i)
							.getF002Ths043());
					BigDecimal y4 = map4.get(listThs043_cal1.get(i)
							.getF002Ths043());
					Double d = ((x1.multiply(y1).doubleValue()) / 100 / f1
							.doubleValue())
							+ ((x2.multiply(y2).doubleValue()) / 100 / f2
									.doubleValue())
							+ ((x3.multiply(y3).doubleValue()) / 100 / f3
									.doubleValue())
							+ ((x4.multiply(y4).doubleValue()) / 100 / f4
									.doubleValue());
					BigDecimal rate = BigDecimalUtils.money(4,
							new BigDecimal(d));
					fc1.setcDate(listThs043_cal1.get(i).getF002Ths043());
					fc1.setcData(rate);
					fc1.setTypeId(typeId);
					list.add(fc1);
				}
				for (FundsPackageCurve fundsPackageCurve : list) {
					System.out.println(fundsPackageCurve.toString());
				}
			}

			// 基金组合中有5个基金
			if (listFundsPackageRatio.size() == 5) {
				// 每个基金组合里的基金
				FundsPackageRatio frp1 = listFundsPackageRatio.get(0);
				BigDecimal xp1 = frp1.getRatio();// x1所占比例
				FundsPackageRatio frp2 = listFundsPackageRatio.get(1);
				BigDecimal xp2 = frp2.getRatio();// x2所占比例
				FundsPackageRatio frp3 = listFundsPackageRatio.get(2);
				BigDecimal xp3 = frp3.getRatio();// x3所占比例
				FundsPackageRatio frp4 = listFundsPackageRatio.get(3);
				BigDecimal xp4 = frp4.getRatio();// x4所占比例
				FundsPackageRatio frp5 = listFundsPackageRatio.get(4);
				BigDecimal xp5 = frp5.getRatio();// x5所占比例

				Ths043 ths043_cal1 = new Ths043();
				ths043_cal1.setF001Ths043(frp1.getFundsCodeInner());
				List<Ths043> listThs043_calp1 = ths043Service.listByDate(
						ths043_cal1, oldDate, newDate, null);// 第1个基金两个时间段之间的净值数据
				Ths043 ths043_cal2 = new Ths043();
				ths043_cal2.setF001Ths043(frp2.getFundsCodeInner());
				List<Ths043> listThs043_calp2 = ths043Service.listByDate(
						ths043_cal2, oldDate, newDate, null);// 第2个基金两个时间段之间的净值数据
				Ths043 ths043_cal3 = new Ths043();
				ths043_cal3.setF001Ths043(frp3.getFundsCodeInner());
				List<Ths043> listThs043_calp3 = ths043Service.listByDate(
						ths043_cal3, oldDate, newDate, null);// 第3个基金两个时间段之间的净值数据
				Ths043 ths043_cal4 = new Ths043();
				ths043_cal4.setF001Ths043(frp4.getFundsCodeInner());
				List<Ths043> listThs043_calp4 = ths043Service.listByDate(
						ths043_cal4, oldDate, newDate, null);// 第4个基金两个时间段之间的净值数据
				Ths043 ths043_cal5 = new Ths043();
				ths043_cal5.setF001Ths043(frp5.getFundsCodeInner());
				List<Ths043> listThs043_calp5 = ths043Service.listByDate(
						ths043_cal5, oldDate, newDate, null);// 第5个基金两个时间段之间的净值数据
				int size1 = 0;
				if (listThs043_calp1 != null && listThs043_calp1.size() > 0) {
					size1 = listThs043_calp1.size();
				}
				int size2 = 0;
				if (listThs043_calp2 != null && listThs043_calp2.size() > 0) {
					size2 = listThs043_calp2.size();
				}
				int size3 = 0;
				if (listThs043_calp3 != null && listThs043_calp3.size() > 0) {
					size3 = listThs043_calp3.size();
				}
				int size4 = 0;
				if (listThs043_calp4 != null && listThs043_calp4.size() > 0) {
					size4 = listThs043_calp4.size();
				}
				int size5 = 0;
				if (listThs043_calp5 != null && listThs043_calp5.size() > 0) {
					size5 = listThs043_calp5.size();
				}
				FundsPackageRatio fr1;
				FundsPackageRatio fr2;
				FundsPackageRatio fr3;
				FundsPackageRatio fr4;
				FundsPackageRatio fr5;
				BigDecimal x1;
				BigDecimal x2;
				BigDecimal x3;
				BigDecimal x4;
				BigDecimal x5;
				List<Ths043> listThs043_cal1 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal2 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal3 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal4 = new ArrayList<Ths043>();
				List<Ths043> listThs043_cal5 = new ArrayList<Ths043>();
				System.out.println("长度分别为：" + size1 + " " + size2 + " " + size3
						+ " " + size4 + " " + size5);
				int size = NumberUtils.getMaxNum(size1, size2, size3, size4,
						size5);
				System.out.println("最大的为： " + size);
				// 比较每支支基金对应的净值个数
				if (size1 == size) {
					fr1 = frp1;
					fr2 = frp2;
					fr3 = frp3;
					fr4 = frp4;
					fr5 = frp5;
					x1 = xp1;
					x2 = xp2;
					x3 = xp3;
					x4 = xp4;
					x5 = xp5;
					listThs043_cal1 = listThs043_calp1;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp3;
					listThs043_cal4 = listThs043_calp4;
					listThs043_cal5 = listThs043_calp5;
				} else if (size2 == size) {
					fr1 = frp2;
					fr2 = frp1;
					fr3 = frp3;
					fr4 = frp4;
					fr5 = frp5;
					x1 = xp2;
					x2 = xp1;
					x3 = xp3;
					x4 = xp4;
					x5 = xp5;
					listThs043_cal1 = listThs043_calp2;
					listThs043_cal2 = listThs043_calp1;
					listThs043_cal3 = listThs043_calp3;
					listThs043_cal4 = listThs043_calp4;
					listThs043_cal5 = listThs043_calp5;
				} else if (size3 == size) {
					fr1 = frp3;
					fr2 = frp2;
					fr3 = frp1;
					fr4 = frp4;
					fr5 = frp5;
					x1 = xp3;
					x2 = xp2;
					x3 = xp1;
					x4 = xp4;
					x5 = xp5;
					listThs043_cal1 = listThs043_calp3;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp1;
					listThs043_cal4 = listThs043_calp4;
					listThs043_cal5 = listThs043_calp5;
				} else if (size4 == size) {
					fr1 = frp4;
					fr2 = frp2;
					fr3 = frp3;
					fr4 = frp1;
					fr5 = frp5;
					x1 = xp4;
					x2 = xp2;
					x3 = xp3;
					x4 = xp1;
					x5 = xp5;
					listThs043_cal1 = listThs043_calp4;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp3;
					listThs043_cal4 = listThs043_calp1;
					listThs043_cal5 = listThs043_calp5;
				} else {
					fr1 = frp5;
					fr2 = frp2;
					fr3 = frp3;
					fr4 = frp4;
					fr5 = frp1;
					x1 = xp5;
					x2 = xp2;
					x3 = xp3;
					x4 = xp4;
					x5 = xp1;
					listThs043_cal1 = listThs043_calp5;
					listThs043_cal2 = listThs043_calp2;
					listThs043_cal3 = listThs043_calp3;
					listThs043_cal4 = listThs043_calp4;
					listThs043_cal5 = listThs043_calp1;
				}
				System.out.println("x1 = " + x1);
				System.out.println("x2 = " + x2);
				System.out.println("x3 = " + x3);
				System.out.println("x4 = " + x4);
				System.out.println("x5 = " + x5);

				BigDecimal f1 = new BigDecimal(1);// f1基日净值
				// 基金净值表(截取当前基金第一条记录)
				Ths043 ths043_base1 = listThs043_cal1.get(0);
				f1 = ths043_base1.getF005Ths043();
				System.out.println("f1 = " + f1);
				// 第1个基金基日
				Date baseDate1 = ths043_base1.getF002Ths043();
				// 第2个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_2 = new Ths043();
				ths043_2.setF001Ths043(fr2.getFundsCodeInner());
				ths043_2.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base2 = ths043Service.list(ths043_2,
						new RowBounds(0, 1));
				BigDecimal f2 = new BigDecimal(1);// f2基日净值
				if (listThs043_base2 != null && listThs043_base2.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base2 = listThs043_base2.get(0);
					f2 = ths043_base2.getF005Ths043();
				}
				System.out.println("f2 = " + f2);
				// 第3个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_3 = new Ths043();
				ths043_3.setF001Ths043(fr3.getFundsCodeInner());
				ths043_3.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base3 = ths043Service.list(ths043_3,
						new RowBounds(0, 1));
				BigDecimal f3 = new BigDecimal(1);// f3基日净值
				if (listThs043_base3 != null && listThs043_base3.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base3 = listThs043_base3.get(0);
					f3 = ths043_base3.getF005Ths043();
				}
				System.out.println("f3 = " + f3);
				// 第4个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_4 = new Ths043();
				ths043_4.setF001Ths043(fr4.getFundsCodeInner());
				ths043_4.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base4 = ths043Service.list(ths043_4,
						new RowBounds(0, 1));
				BigDecimal f4 = new BigDecimal(1);// f4基日净值
				if (listThs043_base4 != null && listThs043_base4.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base4 = listThs043_base4.get(0);
					f4 = ths043_base4.getF005Ths043();
				}
				System.out.println("f4 = " + f4);
				// 第5个基金该日有值，则为基日净值，否则为1
				Ths043 ths043_5 = new Ths043();
				ths043_5.setF001Ths043(fr5.getFundsCodeInner());
				ths043_5.setF002Ths043(baseDate1);
				List<Ths043> listThs043_base5 = ths043Service.list(ths043_5,
						new RowBounds(0, 1));
				BigDecimal f5 = new BigDecimal(1);// f5基日净值
				if (listThs043_base5 != null && listThs043_base5.size() > 0) {
					// 基金净值表(截取当前基金第一条记录)
					Ths043 ths043_base5 = listThs043_base5.get(0);
					f5 = ths043_base5.getF005Ths043();
				}
				System.out.println("f5 = " + f5);

				// 第1个基金净值放入map
				Map<Date, BigDecimal> map1 = new HashMap<Date, BigDecimal>();
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					map1.put(listThs043_cal1.get(i).getF002Ths043(),
							listThs043_cal1.get(i).getF005Ths043());
				}

				// 第2个基金净值放入map
				Map<Date, BigDecimal> map2 = new HashMap<Date, BigDecimal>();
				map2.put(baseDate1, f2);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal2.size(); i++) {
					map2.put(listThs043_cal2.get(i).getF002Ths043(),
							listThs043_cal2.get(i).getF005Ths043());
				}
				// 第3个基金净值放入map
				Map<Date, BigDecimal> map3 = new HashMap<Date, BigDecimal>();
				map3.put(baseDate1, f3);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal3.size(); i++) {
					map3.put(listThs043_cal3.get(i).getF002Ths043(),
							listThs043_cal3.get(i).getF005Ths043());
				}
				// 第4个基金净值放入map
				Map<Date, BigDecimal> map4 = new HashMap<Date, BigDecimal>();
				map4.put(baseDate1, f4);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal4.size(); i++) {
					map4.put(listThs043_cal4.get(i).getF002Ths043(),
							listThs043_cal4.get(i).getF005Ths043());
				}
				// 第5个基金净值放入map
				Map<Date, BigDecimal> map5 = new HashMap<Date, BigDecimal>();
				map5.put(baseDate1, f5);// 基日基金净值放入map
				for (int i = 0; i < listThs043_cal5.size(); i++) {
					map5.put(listThs043_cal5.get(i).getF002Ths043(),
							listThs043_cal5.get(i).getF005Ths043());
				}
				// 如果不存在该日期，或该日期下净值为null，则将上一个值添加到map中
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					if (!map2.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map2.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map2.put(listThs043_cal1.get(i).getF002Ths043(),
								map2.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
					if (!map3.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map3.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map3.put(listThs043_cal1.get(i).getF002Ths043(),
								map3.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
					if (!map4.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map4.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map4.put(listThs043_cal1.get(i).getF002Ths043(),
								map4.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
					if (!map5.containsKey(listThs043_cal1.get(i)
							.getF002Ths043())
							|| map5.get(listThs043_cal1.get(i).getF002Ths043()) == null) {
						map5.put(listThs043_cal1.get(i).getF002Ths043(),
								map5.get(listThs043_cal1.get(i - 1)
										.getF002Ths043()));
					}
				}
				for (int i = 0; i < listThs043_cal1.size(); i++) {
					FundsPackageCurve fc1 = new FundsPackageCurve();
					BigDecimal y1 = listThs043_cal1.get(i).getF005Ths043();
					BigDecimal y2 = map2.get(listThs043_cal1.get(i)
							.getF002Ths043());
					BigDecimal y3 = map3.get(listThs043_cal1.get(i)
							.getF002Ths043());
					BigDecimal y4 = map4.get(listThs043_cal1.get(i)
							.getF002Ths043());
					BigDecimal y5 = map5.get(listThs043_cal1.get(i)
							.getF002Ths043());
					Double d = ((x1.multiply(y1).doubleValue()) / 100 / f1
							.doubleValue())
							+ ((x2.multiply(y2).doubleValue()) / 100 / f2
									.doubleValue())
							+ ((x3.multiply(y3).doubleValue()) / 100 / f3
									.doubleValue())
							+ ((x4.multiply(y4).doubleValue()) / 100 / f4
									.doubleValue())
							+ ((x5.multiply(y5).doubleValue()) / 100 / f5
									.doubleValue());
					BigDecimal rate = BigDecimalUtils.money(4,
							new BigDecimal(d));
					fc1.setcDate(listThs043_cal1.get(i).getF002Ths043());
					fc1.setcData(rate);
					fc1.setTypeId(typeId);
					list.add(fc1);
				}
				for (FundsPackageCurve fundsPackageCurve : list) {
					System.out.println(fundsPackageCurve.toString());
				}
			}

			try {
				// 更新到redis
				for (FundsPackageCurve fundsPackageCurve : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("typeId", fundsPackageCurve.getTypeId());
					map.put("cDate", fundsPackageCurve.getcDate());
					map.put("cData", fundsPackageCurve.getcData());
					listMap.add(map);
				}

				if (listMap.size() > 0) {
					String redisKey = AppCons.FUNDS_PACKAGE_CURVE_ + typeId;
					redisDao.delete(redisKey);
					redisDao.set(redisKey, listMap);
				}

			} catch (Exception e) {
				log.error(e);
			}
			/**
			 * 更新到数据库
			 */
			// ①将原记录备份到funds_package_curve_log表
			List<FundsPackageCurveLog> listFundsPackageCurveLog = new ArrayList<FundsPackageCurveLog>();
			for (FundsPackageCurve fundsPackageCurve : list) {
				FundsPackageCurveLog fundsPackageCurveLog = new FundsPackageCurveLog();
				fundsPackageCurveLog.setTypeId(typeId);
				fundsPackageCurveLog.setcData(fundsPackageCurve.getcData());
				fundsPackageCurveLog.setcDate(fundsPackageCurve.getcDate());
				fundsPackageCurveLog.setOperateStyle(operateStyle);
				fundsPackageCurveLog.setCreateTime(new Date());
				listFundsPackageCurveLog.add(fundsPackageCurveLog);
			}
			fundsPackageCurveLogService
					.batchInsertFundsPackageCurveLog(listFundsPackageCurveLog);
			// ②清空原记录
			FundsPackageCurve fundsPackageCurve = new FundsPackageCurve();
			fundsPackageCurve.setTypeId(typeId);
			fundsPackageCurveService.deleteByExample(fundsPackageCurve);
			// ③批量保存到数据库
			fundsPackageCurveService.batchInsertFundsPackageCurve(list);
		}
		return listMap;
	}
}
