package com.mrbt.oa.mvc.service.funds;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.funds.FundsBaseTypeDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsBaseType;
import com.mrbt.oa.mvc.vo.funds.FundsBaseTypeExample;

@Service
public class FundsBaseTypeService extends
		BaseService<FundsBaseType, FundsBaseTypeDao> {

	@Override
	public FundsBaseTypeExample getExample(FundsBaseType vo) {
		FundsBaseTypeExample example = new FundsBaseTypeExample();
		FundsBaseTypeExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getName())) {
			cri.andNameLike("%" + vo.getName() + "%");
		}
		example.setOrderByClause("id");
		return example;
	}

}
