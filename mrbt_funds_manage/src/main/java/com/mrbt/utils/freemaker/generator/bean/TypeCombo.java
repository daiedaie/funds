package com.mrbt.utils.freemaker.generator.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("TypeCombo")
public class TypeCombo {
	@XStreamAsAttribute
	@XStreamAlias("option")
	public Integer option;
	@XStreamAsAttribute
	@XStreamAlias("text")
	public String text;

	public Integer getOption() {
		return option;
	}

	public String getText() {
		return text;
	}

}
