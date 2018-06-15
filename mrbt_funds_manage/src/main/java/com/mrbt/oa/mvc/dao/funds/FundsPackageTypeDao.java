package com.mrbt.oa.mvc.dao.funds;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsPackageTypeMapper;
import com.mrbt.oa.mvc.vo.funds.FundsPackageType;

@Repository
public class FundsPackageTypeDao extends
		BaseDao<FundsPackageType, FundsPackageTypeMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsPackageTypeMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}
	
	public List<BigDecimal> selectAllId(){
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "selectAllId");
	}
}
