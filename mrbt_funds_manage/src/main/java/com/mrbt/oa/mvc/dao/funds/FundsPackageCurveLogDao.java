package com.mrbt.oa.mvc.dao.funds;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsPackageCurveLogMapper;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurveLog;

@Repository
public class FundsPackageCurveLogDao extends
		BaseDao<FundsPackageCurveLog, FundsPackageCurveLogMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsPackageCurveLogMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

	public void batchInsertFundsPackageCurveLog(List<FundsPackageCurveLog> datas) {
		this.getMapper().batchInsertFundsPackageCurveLog(datas);
	}
}
