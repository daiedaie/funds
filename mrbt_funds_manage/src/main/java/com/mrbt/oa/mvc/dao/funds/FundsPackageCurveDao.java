package com.mrbt.oa.mvc.dao.funds;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsPackageCurveMapper;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurve;

@Repository
public class FundsPackageCurveDao extends
		BaseDao<FundsPackageCurve, FundsPackageCurveMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsPackageCurveMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}
	
	public void batchInsertFundsPackageCurve(List<FundsPackageCurve> datas) {
		this.getMapper().batchInsertFundsPackageCurve(datas);
	}
}
