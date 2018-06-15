package com.mrbt.oa.mvc.service.funds;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrbt.oa.mvc.dao.funds.FundsPackageCurveDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurve;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurveExample;

@Service
public class FundsPackageCurveService extends
		BaseService<FundsPackageCurve, FundsPackageCurveDao> {

	@Override
	public FundsPackageCurveExample getExample(FundsPackageCurve vo) {
		FundsPackageCurveExample example = new FundsPackageCurveExample();
		FundsPackageCurveExample.Criteria cri = example.createCriteria();
		if (vo.getTypeId() != null) {
			cri.andTypeIdEqualTo(vo.getTypeId());
		}
		example.setOrderByClause("TYPE_ID, C_DATE");
		return example;
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 */
	@Transactional
	public void batchInsertFundsPackageCurve(List<FundsPackageCurve> datas) {
		this.getGeneralDao().batchInsertFundsPackageCurve(datas);
	}
}
