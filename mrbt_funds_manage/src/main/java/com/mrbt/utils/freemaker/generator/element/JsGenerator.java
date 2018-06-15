package com.mrbt.utils.freemaker.generator.element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mrbt.utils.freemaker.generator.BaseGenerator;
import com.mrbt.utils.freemaker.generator.GeneratorHtml;
import com.mrbt.utils.freemaker.generator.bean.Column;
import com.mrbt.utils.freemaker.generator.bean.XmlBean;

public class JsGenerator extends BaseGenerator {

	public JsGenerator(XmlBean bean) {
		super(bean);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean generatorHtml(String ftlPath) {
		try {
			// TODO Auto-generated method stub
			ftlFile = new File(GridTitleGenerator.class.getResource("/")
					.getPath() + ftlPath);
			GeneratorHtml generator = new GeneratorHtml();
			HashMap<String, Object> contextMap = new HashMap<String, Object>();
			contextMap.put("saveUrl", getPathWithEnd(bean.path.vistUrl)
					+ "save");
			contextMap.put("deleteUrl", getPathWithEnd(bean.path.vistUrl)
					+ "delete");
			contextMap.put("updateUrl", getPathWithEnd(bean.path.vistUrl)
					+ "update");
			contextMap.put("searchList", generatorList());
			generator.geneHtmlFile("js.ftl", ftlFile.getPath(), contextMap,
					getPathWithEnd(bean.path.js), "show.js");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 生成th标题
	 * 
	 * @return
	 */
	public List<Column> generatorList() {
		List<Column> list = bean.columns;
		List<Column> searchList = new ArrayList<Column>();
		for (Column col : list) {
			if (col.getSearch()) {
				searchList.add(col);
			}
		}
		return searchList;
	}
}
