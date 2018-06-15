package com.mrbt.oa.mvc.service.funds;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrbt.oa.mvc.dao.funds.FundsRiskDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsRisk;
import com.mrbt.oa.mvc.vo.funds.FundsRiskExample;

@Service
public class FundsRiskService extends BaseService<FundsRisk, FundsRiskDao> {

	@Override
	public FundsRiskExample getExample(FundsRisk vo) {
		FundsRiskExample example = new FundsRiskExample();
		FundsRiskExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getFundsCode())) {
			cri.andFundsCodeLike("%" + vo.getFundsCode() + "%");
		}
		example.setOrderByClause("FUNDS_CODE");
		return example;
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 */
	@Transactional
	public void batchInsertFundsRisk(List<FundsRisk> datas) {
		this.getGeneralDao().batchInsertFundsRisk(datas);
	}
}
