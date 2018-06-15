package com.mrbt.mvc.vo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import com.mrbt.utils.FastJsonUtils;
/**
 * solr搜索建议
 * 针对基金代码、基金名称、基金拼音
 *
 */
public class FundSuggestion implements Serializable{
	private static final long serialVersionUID = 1L;
	@Field(value="suggest_name")
	private String suggest_name;
	@Field(value="suggest_fullname")
	private String suggest_fullname;
	@Field(value="suggest_code")
	private String suggest_code;
	@Field(value="suggest_pinyin")
	private String suggest_pinyin;
	public String getSuggest_name() {
		return suggest_name;
	}
	public void setSuggest_name(String suggest_name) {
		this.suggest_name = suggest_name;
	}
	public String getSuggest_fullname() {
		return suggest_fullname;
	}
	public void setSuggest_fullname(String suggest_fullname) {
		this.suggest_fullname = suggest_fullname;
	}
	public String getSuggest_code() {
		return suggest_code;
	}
	public void setSuggest_code(String suggest_code) {
		this.suggest_code = suggest_code;
	}
	public String getSuggest_pinyin() {
		return suggest_pinyin;
	}
	public void setSuggest_pinyin(String suggest_pinyin) {
		this.suggest_pinyin = suggest_pinyin;
	}	
	public String toString(){
		return FastJsonUtils.Obj2Json(this);
	}
}
