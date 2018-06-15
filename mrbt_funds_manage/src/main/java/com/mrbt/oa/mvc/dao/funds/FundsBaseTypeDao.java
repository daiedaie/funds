package com.mrbt.oa.mvc.dao.funds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsBaseTypeMapper;
import com.mrbt.oa.mvc.vo.funds.FundsBaseType;

@Repository
public class FundsBaseTypeDao extends
		BaseDao<FundsBaseType, FundsBaseTypeMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsBaseTypeMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}
}
