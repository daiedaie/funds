package com.mrbt.oa.mvc.dao.funds;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsRiskMapper;
import com.mrbt.oa.mvc.vo.funds.FundsRisk;

@Repository
public class FundsRiskDao extends BaseDao<FundsRisk, FundsRiskMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsRiskMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

	public void batchInsertFundsRisk(List<FundsRisk> datas) {
		this.getMapper().batchInsertFundsRisk(datas);
	}
}
