package com.mrbt.oa.mvc.dao.funds;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsMarketMapper;
import com.mrbt.oa.mvc.vo.funds.FundsMarket;

@Repository
public class FundsMarketDao extends BaseDao<FundsMarket, FundsMarketMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsMarketMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

	/**
	 * 根据主键查找
	 * 
	 * @param fundsCode
	 * @return
	 */
	public FundsMarket findByPk(String fundsCode) {
		return this.getMapper().selectByPrimaryKey(fundsCode);
	}

	/**
	 * 根据推荐排序查询
	 * 
	 * @param recommOrder
	 * @return
	 */
	public List<FundsMarket> selectByRecommOrder(BigDecimal recommOrder) {
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "selectByRecommOrder", recommOrder);
	}

	/**
	 * 将上一个排序规则修改为默认值999
	 * 
	 * @param recommOrder
	 */
	public void updateByRecommOrder(short recommOrderNew, short recommOrderOld) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recommOrderNew", recommOrderNew);
		map.put("recommOrderOld", recommOrderOld);
		this.getMapper().updateByRecommOrder(map);
	}

	/**
	 * 根据主键删除
	 * 
	 * @param fundsCode
	 * @return
	 */
	public void deleteByPk(String fundsCode) {
		this.getMapper().deleteByPrimaryKey(fundsCode);
	}

}
