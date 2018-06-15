package com.mrbt.units;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataFormatUnits {
	static DecimalFormat df = new DecimalFormat("#,###.##");
	static DecimalFormat int_df = new DecimalFormat("#");
	static SimpleDateFormat sdf_birthday = new SimpleDateFormat("yyyy");
	
	public static String thousandBits(double number){
		return df.format(number); 
	}
	
	public static String mathRint(double number){
		return int_df.format(Math.rint(number)); 
	}
	
	/**
	 * 返回申购和赎回的状态
	 * @param x
	 * @return
	 * 1-开放；2-暂停；3-未开放；4-终止
	 */
	public static String getApplyRedeemState(int x) {
		String ret = "未知";
		switch (x) {
		case 1:
			ret = "开放";
			break;
		case 2:
			ret = "暂停";
			break;
		case 3:
			ret = "为开放";
			break;
		case 4:
			ret = "终止";
			break;
		}												
		return ret;
	}

	public static String getManagersAge(Date birthday) {
		if(birthday == null){
			return "";
		}
		Calendar c = Calendar.getInstance();
		c.setTime(birthday);
		int bir = c.get(Calendar.YEAR);
		c.setTime(new Date());
		return c.get(Calendar.YEAR) - bir + "";
	}
}
