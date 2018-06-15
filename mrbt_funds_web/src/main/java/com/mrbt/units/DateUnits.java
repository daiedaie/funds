package com.mrbt.units;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUnits {

	static SimpleDateFormat in_sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static SimpleDateFormat out_sdf = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat seasonMonth = new SimpleDateFormat("yyyy-MM");
	static SimpleDateFormat interval = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat y_sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static String strToDate(String str) {
		String res = null;
		try {
			res = out_sdf.format(in_sdf.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static String dateToStr(Date date) {
		return interval.format(date);
	}

	/**
	 * 取得季度月
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}

	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}


	/**
	 * 返回一个月的区间
	 * 
	 * @return
	 */
	public static String[] getMonthlyInterval() {
		String[] str = new String[2];
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		str[1] = interval.format(cal.getTime());
		
		cal.add(Calendar.MARCH, -1);
		str[0] = interval.format(cal.getTime());
		
		return str;
	}

	/**
	 * 返回6个月的区间
	 * 
	 * @return
	 */
	public static String[] getHalfYearInterval() {
		String[] str = new String[2];
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		str[1] = interval.format(cal.getTime());
		
		cal.add(Calendar.MARCH, -6);
		str[0] = interval.format(cal.getTime());
		
		return str;
	}

	/**
	 * 返回一年的区间
	 * 
	 * @return
	 */
	public static String[] getOneYearInterval() {
		String[] str = new String[2];
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		str[1] = interval.format(cal.getTime());
		
		cal.add(Calendar.YEAR, -1);
		str[0] = interval.format(cal.getTime());
		
		return str;
	}

	/**
	 * 返回3年的区间
	 * 
	 * @return
	 */
	public static String[] getTreeYearInterval() {
		String[] str = new String[2];
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		str[1] = interval.format(cal.getTime());
		
		cal.add(Calendar.YEAR, -3);
		str[0] = interval.format(cal.getTime());
		
		return str;
	}


	public static String getStrDate(Date netDate) {
		return y_sdf.format(netDate);
	}
	
	
	public static String[] getSqlDate(String year, int i) {
		String[] sqlDate = new String[2];
		
		Calendar cal = Calendar.getInstance();
		
		//如果当前年份等于查询到的最大年份，返回当前季度的前后日期
		if((cal.get(Calendar.YEAR) + "").equals(year)){
			//返回当前季度
			sqlDate = getSeasonDate(cal.get(Calendar.YEAR) + "", getSeason(new Date()) - 1 - i);
		}else{
			sqlDate = getSeasonDate(year, 4 -i);
		}
		
		return sqlDate;
	}
	
	public static String[] getSeasonDate(String year,int i) {
		String[] sqlDate = new String[2];
		
		switch (i) {
		case 1:
			sqlDate[0] = year + "-01-01";
			sqlDate[1] = year + "-03-31";
			break;
		case 2:
			sqlDate[0] = year + "-04-01";
			sqlDate[1] = year + "-06-30";
			break;
		case 3:
			sqlDate[0] = year + "-07-01";
			sqlDate[1] = year + "-09-30";
			break;
		default:
			sqlDate[0] = year + "-10-01";
			sqlDate[1] = year + "-12-31";
			break;
		}
		return sqlDate;
	}
	

	public static String getFormatDate(String date) {
		try {
			Date d = in_sdf.parse(date);
			return interval.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}

	public static int getDateSubtraction(String sDate, String eDate) {
		try {
			long e = 0L;
			if(eDate.equals("至今")){
				e = new Date().getTime();
			}else{
				e = interval.parse(eDate).getTime();
			}
			long s = interval.parse(sDate).getTime();
			return (int) ((e - s)/1000/60/60/24);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取redis的有效期
	 * @return
	 */
	public static Date getTomorrowDate() {
		try {
			Date date = interval.parse(interval.format(new Date()));
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			return calendar.getTime(); // 这个时间就是日期往后推一天的结果
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
