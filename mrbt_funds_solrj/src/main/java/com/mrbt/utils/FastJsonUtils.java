package com.mrbt.utils;

import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonUtils {
	/**
	 * Java对象转json字符串
	 * @param t
	 * @return
	 */
	public static <T> String Obj2Json(T t){
		return JSON.toJSONString(t);
	}
	/**
	 * List集合转JSON
	 * @param lists
	 * @return
	 */
	public static <T> String list2Json(Collection<T> lists){
		return JSON.toJSONString(lists,true);
	}
	@SuppressWarnings("unchecked")
	public static  <T> T Json2Map(String text,Class<?> clazz){
		return (T) JSON.parseObject(text, clazz);
	}
	public static List<?> JsonToList(String result, Class<?> clazz) {
		return JSON.parseArray(result, clazz);
	}
	/***
	 * json转Bean
	 * @param <T>
	 * @param jsonObject
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T JsonToBean(String json,Class<?> clazz){
		return (T) JSONObject.parseObject(json, clazz);
	}
}
