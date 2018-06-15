package com.mrbt.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class StringCustomerUtils {
	public static boolean isEmpty(String s) {
	    return (s == null) || s.length() == 0 || s.equalsIgnoreCase("null");
	}
	public static boolean isNotEmpty(String s) {
		return (s != null) && s.length() > 0;
	}
	public static boolean isCN(String content){
		if(isNotEmpty(content) && (content.matches("[\\u4E00-\\u9FA5]+"))) {
			return true;
		}
		return false;
	}
	public static boolean isAlpha(String content){
		if(isNotEmpty(content) &&(!isCN(content)) && (StringUtils.isAlpha(content) || StringUtils.isAlphaSpace(content))){
			return true;
		}
		return false;
	}
	
	public static boolean isNumber(String content){
		if(isNotEmpty(content) && (StringUtils.isNumeric(content) || StringUtils.isNumericSpace(content))){
			return true;
		}
		return false;
	}
	public static boolean isNotNumber(String content){
		if(isNotEmpty(content) && (StringUtils.isNumeric(content) || StringUtils.isNumericSpace(content))){
			return false;
		}
		return true;
	}
	//转小写
	public static String lowerCase(String content){
		return StringUtils.lowerCase(content);
	}
	//去除空格
	public static String trim(String content){
		return StringUtils.replaceChars(content," ","");
	}
	public static void main(String[] args) {
		
		String result = trim("12 22");
		System.err.println(result);
	}
	
	public static <K, V> boolean isEmptyMap(Map<K, V> map){
		if(map == null || map.size() == 0){
			return true;
		}
		return false;
	}
	
}
