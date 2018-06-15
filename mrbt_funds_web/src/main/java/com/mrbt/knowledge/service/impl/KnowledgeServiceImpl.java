package com.mrbt.knowledge.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrbt.knowledge.dao.KnowledgeMapper;
import com.mrbt.knowledge.model.KnowledgeInfo;
import com.mrbt.knowledge.service.KnowledgeService;

@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService{

	@Resource
	private KnowledgeMapper knowledgeMapper;
	
	@Override
	public List<KnowledgeInfo> getKnoledgeList(Integer page, Integer rows) {
		return knowledgeMapper.getKnowledgeList(page, rows);
	}

	@Override
	public Integer getKnoledgeCount() {
		return knowledgeMapper.getKnowledgeCount();
	}
	
	
}
