package com.mrbt.units;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yiban sun
 * @date 2016年6月13日 下午4:55:04
 * @version 1.0
 * @description properties文件工具類
 * @since
 **/
public class PropertiesUtils {

	private static String prefix = PropertiesUtils.class.getResource("/").getPath();
	private static String suffix = ".properties";

	private static Properties pro;

	/**
	 * @Description 获取属性文件(.properties文件)中的值 ,所有的key值默认转换为小写
	 * @param propertiesName
	 *            配置文件名 name
	 * @param key
	 *            需要获取的key值
	 * @return key对应的值 沒有則返回null
	 */
	public static String getPropertyByKey(String propertiesName, String key) {
		if (null != key && !key.isEmpty()) {
//			key = key.toLowerCase();
			pro = PropertiesUtils.loadProperties(propertiesName);
		} else {
			return null;
		}
		return pro.getProperty(key);
	}

	/**
	 * @Description 添加properties
	 * @param propertiesName
	 *            文件名称
	 * @param key
	 *            添加的key
	 * @param value
	 *            添加的value
	 * @param remark
	 *            添加的备注
	 */
	public static void addProperty(String propertiesName, String key,
			String value, String remark) {
		if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
			pro = PropertiesUtils.loadProperties(propertiesName);
			BufferedWriter bw = null;
			key = key.toLowerCase();
			try {
				String val = pro.getProperty(key);
				if (StringUtils.isNotBlank(val)) {
					System.out.println(key + ":" + val);
					System.err.println("此键已存在，请从新输入。");
				} else {
					pro.clear();// 去除重复追加
					bw = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(prefix + propertiesName
									+ suffix, true), "UTF-8"));
					pro.setProperty(key, value);
					pro.store(bw, new String(remark.getBytes(), "UTF-8"));
					System.out.println("保存成功！");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != bw) {
					try {
						bw.flush();
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	/**
	 * @Description 获取指定properties中所有属性
	 * @param propertiesName
	 *            文件名
	 * @return json类型所有属性
	 */
	public static JSONObject getAllProperties(String propertiesName) {
		if (StringUtils.isNotBlank(propertiesName)) {
			pro = PropertiesUtils.loadProperties(propertiesName);
			Map<String, String> map = new HashMap<String, String>();
			Iterator<String> it = pro.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = pro.getProperty(key);
				map.put(key, value);
			}
			JSONObject jo = (JSONObject) JSONObject.toJSON(map);
			return jo;
		}
		return null;
	}

	/**
	 * @Description 更新指定properties的key的值，注释原有的value
	 * @param propertiesName
	 *            文件名
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param remark
	 *            备注
	 * @return 更新成功返回true 否则false
	 */
	public static boolean updatePropertiesByKey(String propertiesName,
			String key, String value, String remark) {
		if (StringUtils.isNotBlank(propertiesName)
				&& StringUtils.isNotBlank(key)) {
			pro = PropertiesUtils.loadProperties(propertiesName);
			if(pro.containsKey(key)){
				BufferedWriter bw = null;
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(
							new FileInputStream(prefix + propertiesName + suffix),
							"UTF-8"));
					StringBuffer str = new StringBuffer("");
					while (true) {
						String s = br.readLine();
						if (StringUtils.isBlank(s)) {
							break;
						} else if (s.startsWith(key.toLowerCase() + "=")) {
							str.append("#" + s + "\n");
							str.append("#" + remark + "\n");
							s = key + "=" + value;
						}
						str.append(s + "\n");
					}
					bw = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(prefix + propertiesName + suffix),
							"UTF-8"));
					bw.write(str.toString());
					
					System.out.println("Update Complete!");
					return true;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (null != bw) {
						try {
							bw.flush();
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				System.out.println("此鍵不存在");
				return false;
			}
		} 

		return false;
	}

	/**
	 * @Description 加载properties
	 * @param propertiesName
	 *            文件名
	 * @return Properties实例
	 */
	public static Properties loadProperties(String propertiesName) {
		BufferedReader br = null;
		if (null == pro) {
			pro = new Properties();
		}
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					prefix + propertiesName + suffix), "UTF-8"));
			pro.load(br);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pro;
	}

	public static void main(String[] args) throws IOException {
		String url = PropertiesUtils.getPropertyByKey("url", "recommendFunds");
		System.out.println(url);
//		PropertiesUtils
//				.addProperty(
//						"solr-url",
//						"URL16",
//						"http://192.168.1.88:8020/core/select?q=SEQ:基金代理&wt=json&indent=true",
//						"基金测试");
//		PropertiesUtils.updatePropertiesByKey("solr-url", "url17", "new URL",
//				"新的基金查询URL");
	}
}
