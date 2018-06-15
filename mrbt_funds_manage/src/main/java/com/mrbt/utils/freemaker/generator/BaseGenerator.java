package com.mrbt.utils.freemaker.generator;

import java.io.File;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.mrbt.utils.freemaker.generator.bean.XmlBean;

public abstract class BaseGenerator {
	public XmlBean bean;
	public File ftlFile = null;
	protected ExpressionParser parser = new SpelExpressionParser();

	public BaseGenerator(XmlBean bean) {
		this.bean = bean;
	}

	/**
	 * 获取带/的路径地址
	 * 
	 * @param path
	 * @return
	 */
	public String getPathWithEnd(String path) {
		return path.endsWith("/") ? path : path + "/";
	}

	/**
	 * 获取高度
	 * 
	 * @return
	 */
	public int getHeight(int size) {
		return getHeight(size, 2, 120);
	}

	/**
	 * 获取高度
	 * 
	 * @return
	 */
	public int getHeight(int size, int div, int base) {
		int hight = size / div;
		hight = hight * div < size ? hight + 1 : hight;
		return hight * 30 + base;
	}

	public abstract boolean generatorHtml(String ftlPath);
}
