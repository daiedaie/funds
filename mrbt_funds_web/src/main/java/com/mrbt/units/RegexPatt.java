package com.mrbt.units;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatt {
	String regdex;
	String match;
	public RegexPatt(String regdex, String match){
		this.regdex = regdex;
		this.match = match;
	}
	public static void main(String[] args) {
		int num = new RegexPatt("class=\"Color7\">\\((\\d+)\\)</span>","").getValueNum();
		System.out.println(num);
	}
	
	public String getPattValue(){
		String value = "";
		Matcher m = Pattern.compile(regdex).matcher(match);
		int num = getValueNum();
		if(m.find()){
			for (int i = 1; i <= num; i++) {
				if(i == num){
					value = value + m.group(i);
				}else{
					value = value + m.group(i) + "\",\"";
				}
			}
		}
		return value;
	}
	
	public List<String> getPattList(){
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile(regdex).matcher(match);
		int num = getValueNum();
		while(m.find()){
			String value = "";
			for (int i = 1; i <= num; i++) {
				if(i == num){
					value = value + m.group(i);
				}else{
					value = value + m.group(i) + "\",\"";
				}
			}
			list.add(value);
		}
		return list;
	}
	
	public int getValueNum() {
		String t = regdex.replace("\\)", "brackets");
		int x = 0;
		while(t.indexOf(")") > 0){
			x++;
			t = t.substring(t.indexOf(")") + 1,t.length());
		}
		return x;
	}
}
