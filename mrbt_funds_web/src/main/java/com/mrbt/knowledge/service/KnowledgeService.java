package com.mrbt.knowledge.service;

import java.util.List;

import com.mrbt.knowledge.model.KnowledgeInfo;

public interface KnowledgeService {

	public List<KnowledgeInfo> getKnoledgeList(Integer page, Integer rows);

	public Integer getKnoledgeCount();

}
