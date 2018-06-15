package com.mrbt.oa.mvc.dao.funds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsMarketLogMapper;
import com.mrbt.oa.mvc.vo.funds.FundsMarketLog;

@Repository
public class FundsMarketLogDao extends
		BaseDao<FundsMarketLog, FundsMarketLogMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsMarketLogMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}
}
