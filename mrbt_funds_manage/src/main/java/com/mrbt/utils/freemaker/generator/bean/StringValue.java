package com.mrbt.utils.freemaker.generator.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class StringValue {
	@XStreamAsAttribute
	@XStreamAlias("jsp")
	public String jsp;

	@XStreamAsAttribute
	@XStreamAlias("js")
	public String js;

	@XStreamAsAttribute
	@XStreamAlias("vist-url")
	public String vistUrl;
}
