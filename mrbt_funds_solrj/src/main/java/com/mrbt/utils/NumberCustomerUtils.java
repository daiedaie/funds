package com.mrbt.utils;

import java.util.Random;

import org.apache.solr.common.StringUtils;

public class NumberCustomerUtils {
	private final static String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static void main(String[] args) {
		System.out.println(getRandomString("调查发现在映射出现重复基金测试索引代码", 6));
		System.out.println(getRandomString(null, 6));
		System.out.println(getFour(3.141592653589793238462643383249901429));
		
	}
	/*
	 * 随机6位字符串
	 */
	public static String getRandomString(String content,int length) {
		if(StringUtils.isEmpty(content)){
			content = base;
		}
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(content.length());
			sb.append(content.charAt(number));
		}
		return sb.toString();
	}
	
	/*
	 * 保留4位小数点
	 */
	public static String getFour(double param) {
		return new java.text.DecimalFormat("#.####").format(param);
	}
	
	/**
	 * 随机生成四位小数
	 */
	public static String getRandomFour(){
		return new java.text.DecimalFormat("#.####").format(Math.random()*(new Random().nextDouble()))+"";
	}
}
