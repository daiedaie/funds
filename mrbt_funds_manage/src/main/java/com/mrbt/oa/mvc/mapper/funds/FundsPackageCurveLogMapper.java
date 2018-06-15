package com.mrbt.oa.mvc.mapper.funds;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurveLog;
import com.mrbt.oa.mvc.vo.funds.FundsPackageCurveLogExample;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FundsPackageCurveLogMapper extends BaseMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_CURVE_LOG
	 * @mbggenerated  Thu Jul 07 10:13:37 CST 2016
	 */
	int countByExample(FundsPackageCurveLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_CURVE_LOG
	 * @mbggenerated  Thu Jul 07 10:13:37 CST 2016
	 */
	int deleteByExample(FundsPackageCurveLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_CURVE_LOG
	 * @mbggenerated  Thu Jul 07 10:13:37 CST 2016
	 */
	int insert(FundsPackageCurveLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_CURVE_LOG
	 * @mbggenerated  Thu Jul 07 10:13:37 CST 2016
	 */
	int insertSelective(FundsPackageCurveLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_CURVE_LOG
	 * @mbggenerated  Thu Jul 07 10:13:37 CST 2016
	 */
	List<FundsPackageCurveLog> selectByExample(
			FundsPackageCurveLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_CURVE_LOG
	 * @mbggenerated  Thu Jul 07 10:13:37 CST 2016
	 */
	int updateByExampleSelective(@Param("record") FundsPackageCurveLog record,
			@Param("example") FundsPackageCurveLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_CURVE_LOG
	 * @mbggenerated  Thu Jul 07 10:13:37 CST 2016
	 */
	int updateByExample(@Param("record") FundsPackageCurveLog record,
			@Param("example") FundsPackageCurveLogExample example);

	int batchInsertFundsPackageCurveLog(List<FundsPackageCurveLog> datas);
}