package com.mrbt.utils.freemaker.generator.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlBean {

	public StringValue path;

	public String title;

	public Map<String, List<TypeCombo>> comboMaps = new HashMap<String, List<TypeCombo>>();

	public List<Column> columns = new ArrayList<Column>();

}
