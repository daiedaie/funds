package com.mrbt.oa.mvc.service.funds;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.funds.FundsMarketLogDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsMarketLog;
import com.mrbt.oa.mvc.vo.funds.FundsMarketLogExample;

@Service
public class FundsMarketLogService extends
		BaseService<FundsMarketLog, FundsMarketLogDao> {

	@Override
	public FundsMarketLogExample getExample(FundsMarketLog vo) {
		FundsMarketLogExample example = new FundsMarketLogExample();
		FundsMarketLogExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getFundsCode())) {
			cri.andFundsCodeLike("%" + vo.getFundsCode() + "%");
		}
		example.setOrderByClause("id");
		return example;
	}

}
