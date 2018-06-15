package com.mrbt.mvc.model;

import java.math.BigDecimal;
import java.util.Date;

public class FundsBulletin {
	private BigDecimal id;

    private String title;

    private Date createTime;

    private BigDecimal onLine;

    private Object text;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getOnLine() {
        return onLine;
    }

    public void setOnLine(BigDecimal onLine) {
        this.onLine = onLine;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }
   
}