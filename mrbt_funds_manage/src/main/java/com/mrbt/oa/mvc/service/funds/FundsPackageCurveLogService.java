package com.mrbt.oa.mvc.service.funds;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrbt.oa.mvc.dao.funds.FundsPackageCurveLogDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurveLog;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurveLogExample;
import com.mrbt.utils.DateUtils;

@Service
public class FundsPackageCurveLogService extends
		BaseService<FundsPackageCurveLog, FundsPackageCurveLogDao> {

	@Override
	public FundsPackageCurveLogExample getExample(FundsPackageCurveLog vo) {
		FundsPackageCurveLogExample example = new FundsPackageCurveLogExample();
		FundsPackageCurveLogExample.Criteria cri = example.createCriteria();
		if (vo.getCreateTime() != null) {
			cri.andCreateTimeLike("%"
					+ DateUtils.sdf2.format(vo.getCreateTime()) + "%");
		}
		if (vo.getOperateStyle() != null) {
			if (vo.getOperateStyle() == (short) 1) {// 定时任务
				cri.andOperateStyleEqualTo((short) 0);
			}
			if (vo.getOperateStyle() == (short) 2) {// 手动
				cri.andOperateStyleEqualTo((short) 1);
			}
		}
		example.setOrderByClause("CREATE_TIME");
		return example;
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 */
	@Transactional
	public void batchInsertFundsPackageCurveLog(List<FundsPackageCurveLog> datas) {
		this.getGeneralDao().batchInsertFundsPackageCurveLog(datas);
	}

}
