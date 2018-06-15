package com.mrbt.utils.freemaker.generator.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("column")
public class Column {
	/**
	 * 字段显示的宽度
	 */
	@XStreamAsAttribute
	@XStreamAlias("width")
	public String width = "200";
	/**
	 * label显示的宽度
	 */
	@XStreamAsAttribute
	@XStreamAlias("labelWidth")
	public String labelWidth = "100";
	/**
	 * label的名称
	 */
	@XStreamAsAttribute
	@XStreamAlias("label")
	public String label;
	/**
	 * 是否是搜索条件
	 */
	@XStreamAsAttribute
	@XStreamAlias("search")
	public Boolean search = false;
	/**
	 * 在更新里是否显示
	 */
	@XStreamAsAttribute
	@XStreamAlias("update")
	public Boolean update = true;
	/**
	 * 是否为主键
	 */
	@XStreamAsAttribute
	@XStreamAlias("id")
	public Boolean id = false;
	/**
	 * 是否在列表里显示
	 */
	@XStreamAsAttribute
	@XStreamAlias("grid")
	public Boolean grid = true;
	/**
	 * 是否在添加里显示
	 */
	@XStreamAsAttribute
	@XStreamAlias("save")
	public Boolean save = true;
	/**
	 * 是否在详情中显示
	 */
	@XStreamAsAttribute
	@XStreamAlias("view")
	public Boolean view = true;
	/**
	 * 属性名称
	 */
	@XStreamAsAttribute
	@XStreamAlias("name")
	public String name;
	/**
	 * 此字段的类型，目前只支持"hidden,string,int,date,time,combobox"
	 */
	@XStreamAsAttribute
	@XStreamAlias("type")
	public String type = "String";
	/**
	 * 定义format显示，目前只支持"date,time,combobox"
	 */
	@XStreamAsAttribute
	@XStreamAlias("format")
	public String format = null;
	/**
	 * 在显示的时候combo提示下拉框名称，在TypeCombo李查询
	 */
	@XStreamAsAttribute
	@XStreamAlias("comboName")
	public String comboName = null;

	public String getWidth() {
		return width == null ? "200" : width;
	}

	public String getLabelWidth() {
		return labelWidth == null ? "200" : labelWidth;
	}

	public String getLabel() {
		return label;
	}

	public Boolean getSearch() {
		return search == null ? false : search;
	}

	public Boolean getUpdate() {
		return update == null ? true : update;
	}

	public Boolean getId() {
		return id == null ? false : id;
	}

	public Boolean getGrid() {
		return grid == null ? true : grid;
	}

	public Boolean getSave() {
		return save == null ? true : save;
	}

	public Boolean getView() {
		return view == null ? true : view;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type == null ? "String" : type;
	}

	public String getFormat() {
		return format;
	}

	public String getComboName() {
		return comboName;
	}

}
