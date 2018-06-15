package com.mrbt.utils.freemaker.generator;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.mrbt.utils.freemaker.generator.bean.StringValue;
import com.mrbt.utils.freemaker.generator.bean.XmlBean;
import com.mrbt.utils.freemaker.generator.element.FormSaveGenerator;
import com.mrbt.utils.freemaker.generator.element.FormUpdateGenerator;
import com.mrbt.utils.freemaker.generator.element.FormViewGenerator;
import com.mrbt.utils.freemaker.generator.element.GridTitleGenerator;
import com.mrbt.utils.freemaker.generator.element.JsGenerator;
import com.mrbt.utils.freemaker.generator.element.ShowGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 自动生成的工厂类
 * 
 * @author airgilbert
 *
 */
public class GeneratorFactory {
	public XmlBean bean = null;
	/**
	 * xml的类路径
	 */
	public String xmlPath = null;
	public XStream xstream = null;
	{
		xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
		xstream.alias("bean", XmlBean.class);
		xstream.alias("path", StringValue.class);
		xstream.alias("comboMaps", Map.class);
		xstream.alias("string", String.class);
		xstream.alias("columns", List.class);
	}

	/**
	 * 
	 * @param xmlPath
	 *            提供的地址为类路径，例如：com/mrbt/utils/freemaker/ftl/user.xml
	 */
	public GeneratorFactory(String xmlPath) {
		bean = (XmlBean) xstream.fromXML(new File(XmlBean.class
				.getResource("/").getPath() + xmlPath));
	}

	/**
	 * 生成列表
	 * 
	 * @return
	 */
	public GridTitleGenerator createGrid() {
		return new GridTitleGenerator(bean);
	}

	/**
	 * 生成添加
	 * 
	 * @return
	 */
	public FormSaveGenerator createFormSave() {
		return new FormSaveGenerator(bean);
	}

	/**
	 * 生成更新
	 * 
	 * @return
	 */
	public FormUpdateGenerator createFormUpdaqte() {
		return new FormUpdateGenerator(bean);
	}

	/**
	 * 生成查看
	 * 
	 * @return
	 */
	public FormViewGenerator createFormView() {
		return new FormViewGenerator(bean);
	}

	/**
	 * 生成js
	 * 
	 * @return
	 */
	public JsGenerator createJs() {
		return new JsGenerator(bean);
	}

	/**
	 * 生成show
	 * 
	 * @return
	 */
	public ShowGenerator createShow() {
		return new ShowGenerator(bean);
	}

	public static void main(String[] args) {
		GeneratorFactory gf = new GeneratorFactory(
				"com/mrbt/utils/freemaker/xml/company/depart.xml");
		gf.createGrid().generatorHtml("com/mrbt/utils/freemaker/ftl");
		gf.createFormSave().generatorHtml("com/mrbt/utils/freemaker/ftl");
		gf.createFormUpdaqte().generatorHtml("com/mrbt/utils/freemaker/ftl");
		gf.createFormView().generatorHtml("com/mrbt/utils/freemaker/ftl");
		gf.createJs().generatorHtml("com/mrbt/utils/freemaker/ftl");
		gf.createShow().generatorHtml("com/mrbt/utils/freemaker/ftl");
	}
}
