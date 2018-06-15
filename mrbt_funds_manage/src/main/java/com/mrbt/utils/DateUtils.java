package com.mrbt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期辅助类
 * 
 * @author littlewoods
 *
 */
public class DateUtils {
	public static SimpleDateFormat sdf1 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat sdf3 = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	public static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");

	public static String getDateStr(Date dt, SimpleDateFormat dateFormat) {
		return dateFormat.format(dt);
	}

	/**
	 * 返回整日，不包含时间，分，秒
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getDay(Date dt) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 判断是否为工作日
	 * 
	 * @param dt
	 */
	public static int isWorkDay(Date dt) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 添加天数
	 * 
	 * @param dt
	 * @param day
	 * @return
	 */
	public static Date addDay(Date dt, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DAY_OF_YEAR, day);
		return c.getTime();
	}

	/**
	 * 添加月
	 * 
	 * @param dt
	 * @param m
	 * @return
	 */
	public static Date addMonth(Date dt, int m) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONTH, m);
		return c.getTime();
	}

	/**
	 * 日期相减，目标日期-原日期=天数
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static int dateDiff(Date src, Date dest) {
		return (int) ((dest.getTime() - src.getTime()) / (24 * 60 * 60 * 1000));
	}

	public static String getDateStrByFormat(Date dt, SimpleDateFormat format) {
		return format.format(dt);
	}

	/**
	 * 去年的昨天 00:00:00
	 * 
	 * @return
	 */
	public static Date getYesterdayOfLastYear() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_YEAR, -1);
		System.out.println(c.getTime());
		return c.getTime();
	}

	/**
	 * 截止到昨天 23:59:59
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		c.add(Calendar.DAY_OF_YEAR, -1);
		System.out.println(c.getTime());
		return c.getTime();
	}

	public static void main(String[] args) {
		new DateUtils().getYesterdayOfLastYear();
	}
}
