package com.mrbt.mvc.vo;


import java.io.Serializable;
import java.util.List;

public class SolrResponseVo<T> implements Serializable{
	/***
	 * JSON返回数据结构
	 */
	private static final long serialVersionUID = -7469348750408567984L;
	private int qTime;
	private long numFound;
	private int status;
	private List<T> indexBean;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public SolrResponseVo(){
	}
	public int getqTime() {
		return qTime;
	}
	public void setqTime(int qTime) {
		this.qTime = qTime;
	}
	public long getNumFound() {
		return numFound;
	}
	public void setNumFound(long numFound) {
		this.numFound = numFound;
	}
	public List<T> getIndexBean() {
		return indexBean;
	}
	public void setIndexBean(List<T> resultList) {
		this.indexBean = resultList;
	}
}
