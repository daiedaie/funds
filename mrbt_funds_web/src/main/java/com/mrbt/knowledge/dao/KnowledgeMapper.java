package com.mrbt.knowledge.dao;

import java.util.List;

import com.mrbt.knowledge.model.KnowledgeInfo;

public interface KnowledgeMapper {

	public List<KnowledgeInfo> getKnowledgeList(Integer page, Integer rows);

	public Integer getKnowledgeCount();
	
}
