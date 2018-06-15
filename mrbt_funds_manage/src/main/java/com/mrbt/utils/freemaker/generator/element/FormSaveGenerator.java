package com.mrbt.utils.freemaker.generator.element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mrbt.utils.freemaker.generator.BaseGenerator;
import com.mrbt.utils.freemaker.generator.GeneratorHtml;
import com.mrbt.utils.freemaker.generator.bean.Column;
import com.mrbt.utils.freemaker.generator.bean.XmlBean;

public class FormSaveGenerator extends BaseGenerator {

	public FormSaveGenerator(XmlBean bean) {
		super(bean);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 生成list的访问地址
	 * 
	 * @return
	 */
	public String generatorSaveUrl() {
		return getPathWithEnd(bean.path.vistUrl) + "save";
	}

	@Override
	public boolean generatorHtml(String ftlPath) {
		try {
			// TODO Auto-generated method stub
			ftlFile = new File(GridTitleGenerator.class.getResource("/")
					.getPath() + ftlPath);
			GeneratorHtml generator = new GeneratorHtml();
			HashMap<String, Object> contextMap = new HashMap<String, Object>();
			contextMap.put("saveList", generatorList());
			contextMap.put("comboMap", bean.comboMaps);
			contextMap.put("hight", getHeight(generatorList().size()));
			generator.geneHtmlFile("add.ftl", ftlFile.getPath(), contextMap,
					getPathWithEnd(bean.path.jsp), "add.jsp");
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
		List<Column> saveList = new ArrayList<Column>();
		for (Column col : list) {
			if (col.getSave() && !col.getId()) {
				saveList.add(col);
			}
		}
		return saveList;
	}
}
