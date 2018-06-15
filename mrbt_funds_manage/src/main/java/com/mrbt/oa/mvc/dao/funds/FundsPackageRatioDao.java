package com.mrbt.oa.mvc.dao.funds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsPackageRatioMapper;
import com.mrbt.oa.mvc.vo.funds.FundsPackageRatio;

@Repository
public class FundsPackageRatioDao extends
		BaseDao<FundsPackageRatio, FundsPackageRatioMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsPackageRatioMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

	public List<FundsPackageRatio> findByFundsCodeAndTypeId(Map map) {
		return this.getMapper().findByFundsCodeAndTypeId(map);
	}

	public int findByFundsCodeNoId(Map map) {
		return this.getMapper().findByFundsCodeNoId(map);
	}

	public List<String> findByDistinctFundsTypeId(BigDecimal typeId) {
		return this.getMapper().findByDistinctFundsTypeId(typeId);
	}
}
