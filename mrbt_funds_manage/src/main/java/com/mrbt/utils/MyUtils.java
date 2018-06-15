package com.mrbt.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyUtils {
	/**
	 * 统一获取log日志的方法
	 * 
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class clazz) {
		return LogManager.getLogger(clazz);
	}

	public static Logger getsellPayMoneyLogger() {
		return LogManager.getLogger("sellPayMoneyFile");
	}

}
