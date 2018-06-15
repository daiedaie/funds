package com.mrbt.oa.mvc.mapper.funds;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.funds.FundsMarket;
import com.mrbt.oa.mvc.vo.funds.FundsMarketExample;

public interface FundsMarketMapper extends BaseMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int countByExample(FundsMarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int deleteByExample(FundsMarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int deleteByPrimaryKey(String fundsCode);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int insert(FundsMarket record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int insertSelective(FundsMarket record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	List<FundsMarket> selectByExample(FundsMarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	FundsMarket selectByPrimaryKey(String fundsCode);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int updateByExampleSelective(@Param("record") FundsMarket record,
			@Param("example") FundsMarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int updateByExample(@Param("record") FundsMarket record,
			@Param("example") FundsMarketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int updateByPrimaryKeySelective(FundsMarket record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_MARKET
	 * @mbggenerated  Thu Jul 07 11:44:33 CST 2016
	 */
	int updateByPrimaryKey(FundsMarket record);

	int updateByRecommOrder(Map<String, Object> map);
}