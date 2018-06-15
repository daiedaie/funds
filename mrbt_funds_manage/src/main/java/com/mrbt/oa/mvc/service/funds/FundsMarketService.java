package com.mrbt.oa.mvc.service.funds;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.funds.FundsMarketDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsMarket;
import com.mrbt.oa.mvc.vo.funds.FundsMarketExample;

@Service
public class FundsMarketService extends
		BaseService<FundsMarket, FundsMarketDao> {

	@Override
	public FundsMarketExample getExample(FundsMarket vo) {
		FundsMarketExample example = new FundsMarketExample();
		FundsMarketExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getFundsCode())) {
			cri.andFundsCodeLike("%" + vo.getFundsCode() + "%");
		}
		example.setOrderByClause("FUNDS_CODE");
		return example;
	}

	/**
	 * 根据主键查找
	 * 
	 * @param fundsCode
	 * @return
	 */
	public FundsMarket findByPk(String fundsCode) {
		return this.getGeneralDao().findByPk(fundsCode);
	}

	/**
	 * 将上一个排序规则修改为默认值999
	 * 
	 * @param recommOrder
	 */
	public void updateByRecommOrder(short recommOrderNew, short recommOrderOld) {
		this.getGeneralDao()
				.updateByRecommOrder(recommOrderNew, recommOrderOld);
	}

	/**
	 * 根据主键删除
	 * 
	 * @param fundsCode
	 * @return
	 */
	public void deleteByPk(String fundsCode) {
		this.getGeneralDao().deleteByPk(fundsCode);
	}

	/**
	 * 根据推荐排序查询
	 * 
	 * @param recommOrder
	 * @return
	 */
	public List<FundsMarket> selectByRecommOrder(BigDecimal recommOrder) {
		return this.getGeneralDao().selectByRecommOrder(recommOrder);
	}
}
