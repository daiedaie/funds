package com.mrbt.utils.freemaker.generator.element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mrbt.utils.freemaker.generator.BaseGenerator;
import com.mrbt.utils.freemaker.generator.GeneratorHtml;
import com.mrbt.utils.freemaker.generator.bean.Column;
import com.mrbt.utils.freemaker.generator.bean.XmlBean;

public class ShowGenerator extends BaseGenerator {

	public ShowGenerator(XmlBean bean) {
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
			contextMap.put("searchList", generatorList());

			contextMap.put("comboMap", bean.comboMaps);
			contextMap.put("height", getHeight(generatorList().size(), 3, 50));
			contextMap.put(
					"resourcePath",
					getPathWithEnd(bean.path.js.substring(bean.path.js
							.indexOf("WebContent/") + "WebContent/".length())));
			contextMap.put("title", bean.title);
			// getPathWithEnd(bean.path.jsp)
			generator.geneHtmlFile("show.ftl", ftlFile.getPath(), contextMap,
					getPathWithEnd(bean.path.jsp), "show.jsp");
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
