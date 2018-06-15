package com.mrbt.utils.freemaker.generator.element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mrbt.utils.freemaker.generator.BaseGenerator;
import com.mrbt.utils.freemaker.generator.GeneratorHtml;
import com.mrbt.utils.freemaker.generator.bean.Column;
import com.mrbt.utils.freemaker.generator.bean.TypeCombo;
import com.mrbt.utils.freemaker.generator.bean.XmlBean;

/**
 * 生成表格的generator
 * 
 * @author airgilbert
 *
 */
public class GridTitleGenerator extends BaseGenerator {
	public GridTitleGenerator(XmlBean bean) {
		super(bean);
		// TODO Auto-generated constructor stub
	}

	public String thStringPattern = "<th data-options=\"field:'%s',width:100 %s\">%s</th>";

	@Override
	public boolean generatorHtml(String ftlPath) {
		try {
			// TODO Auto-generated method stub
			ftlFile = new File(GridTitleGenerator.class.getResource("/")
					.getPath() + ftlPath);
			GeneratorHtml generator = new GeneratorHtml();
			HashMap<String, Object> contextMap = new HashMap<String, Object>();
			contextMap.put("grid", generatorTH());
			contextMap.put("listUrl", generatorListUrl());
			generator.geneHtmlFile("grid.ftl", ftlFile.getPath(), contextMap,
					getPathWithEnd(bean.path.jsp), "grid.jsp");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 生成list的访问地址
	 * 
	 * @return
	 */
	public String generatorListUrl() {
		return getPathWithEnd(bean.path.vistUrl) + "list";
	}

	/**
	 * 生成th标题
	 * 
	 * @return
	 */
	public List<String> generatorTH() {
		List<Column> list = bean.columns;
		List<String> thList = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		for (Column col : list) {
			if (col.getGrid()) {
				map.put("name", col.name);
				map.put("label", col.label);
				map.put("format", "");
				StringBuffer format = new StringBuffer("");
				// 生成formatter
				if (StringUtils.isNotBlank(col.format)) {
					// 如果是combobox类型，并且bean中的comboMaps包含 colboName
					if (col.format.equalsIgnoreCase("combobox")
							&& StringUtils.isNotBlank(col.comboName)
							&& bean.comboMaps != null
							&& bean.comboMaps.containsKey(col.comboName)) {
						format.append(" ,formatter:function(value){");
						List<TypeCombo> formatList = bean.comboMaps
								.get(col.comboName);
						for (int i = 0; i < formatList.size(); i++) {
							if (i == 0) {
								format.append("if(value==");
							} else {
								format.append("else if(value==");
							}
							format.append(formatList.get(i).option)
									.append("){").append("return '")
									.append(formatList.get(i).text)
									.append("'}");
						}
						format.append("else {return '未知'}").append("}");
					} else if (col.format.equalsIgnoreCase("date")) {
						format.append(" ,formatter:formatDate");
					} else if (col.format.equalsIgnoreCase("time")) {
						format.append(" ,formatter:formatTime");
					}
				}
				System.out.println(format.toString());
				map.put("format", format.toString());
				thList.add(String.format(thStringPattern, map.get("name"),
						map.get("format"), map.get("label")));
			}
		}

		return thList;
	}

}
