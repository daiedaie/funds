package com.mrbt.oa.mvc.service.funds;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.funds.FundsKnowledgeDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsKnowledge;
import com.mrbt.oa.mvc.vo.funds.FundsKnowledgeExample;

@Service
public class FundsKnowledgeService extends
		BaseService<FundsKnowledge, FundsKnowledgeDao> {

	@Override
	public FundsKnowledgeExample getExample(FundsKnowledge vo) {
		FundsKnowledgeExample example = new FundsKnowledgeExample();
		FundsKnowledgeExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getTitle())) {
			cri.andTitleLike("%" + vo.getTitle() + "%");
		}
		example.setOrderByClause("id");
		return example;
	}

}
