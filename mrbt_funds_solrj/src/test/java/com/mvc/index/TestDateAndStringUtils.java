package com.mvc.index;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.mrbt.config.AppInfo;
import com.mrbt.utils.DateFormaterType;
import com.mrbt.utils.DateUtil;
import com.mrbt.utils.TimeFormaterType;

public class TestDateAndStringUtils {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void test(){
		boolean alpha = StringUtils.isAlpha("中国");
		System.err.println(alpha);
		//数字
		boolean number = StringUtils.isNumeric("12322");
		System.out.println(number);
		
		boolean numberwhite = StringUtils.isNumericSpace("123   22");
		System.err.println(numberwhite);
		
		boolean zimu = StringUtils.isAlpha("abcdefg胜多负少控件");
		System.err.println(zimu);
		
		
	}
	
	//@Test
	public void testZhoumo() throws ParseException{
		Date date = new Date();		
		date = sdf.parse("2016-07-11");
		String time = DateUtil.previousDate(date, TimeFormaterType.DATE,-1,true);
		System.out.println(time);
	}
	//@Test
	public void testWeek() throws ParseException{
		Date date = new Date();
		
		date = sdf.parse("2016-06-04");
		
		String name = DateUtil.getWeek(date).getName();
		int number = DateUtil.getWeek(date).getNumber();
		System.out.println(name+":"+number);
	}
	
	public static void test(Date param){
		String tDate = DateUtil.DateToString(param,DateFormaterType.YYYY_MM_DD);
		System.out.println("今天"+tDate);
		//昨天
		String yesDate = DateUtil.previousDate(param,TimeFormaterType.DATE,AppInfo.PREVIOUSDATEONE,true);
		System.out.println("昨天"+yesDate);
		//前天
		String beforeyesterDate = DateUtil.previousDate(param,TimeFormaterType.DATE,AppInfo.PREVIOUSDATETWO,true);
		System.out.println("前天"+beforeyesterDate);
		
		//一个月前
		String monthDate = DateUtil.previousDate(param,TimeFormaterType.MONTH,AppInfo.PREVIOUSDATEONE,true);
		System.out.println("上个月"+monthDate);
		//三个月前
		String threemonthDate = DateUtil.previousDate(param,TimeFormaterType.MONTH,AppInfo.PREVIOUSDATETHREE,true);
		//计算三个月涨幅
		System.out.println("三个月前"+threemonthDate);
		//计算年涨幅
		String lastyearDate = DateUtil.previousDate(param,TimeFormaterType.YEAR,AppInfo.PREVIOUSDATEONE,true);
		System.out.println("一年前"+lastyearDate);
		
		//计算三年来涨幅
		String lastThreeyearDate = DateUtil.previousDate(param,TimeFormaterType.YEAR,AppInfo.PREVIOUSDATETHREE,true);
		System.out.println("三年前"+lastThreeyearDate);
		
		
	}

}
