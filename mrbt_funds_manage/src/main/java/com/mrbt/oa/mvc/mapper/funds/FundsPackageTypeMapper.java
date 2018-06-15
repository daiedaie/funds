package com.mrbt.oa.mvc.mapper.funds;

import com.mrbt.oa.mvc.mapper.BaseMapper;
import com.mrbt.oa.mvc.vo.funds.FundsPackageType;
import com.mrbt.oa.mvc.vo.funds.FundsPackageTypeExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FundsPackageTypeMapper extends BaseMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int countByExample(FundsPackageTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int deleteByExample(FundsPackageTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int deleteByPrimaryKey(BigDecimal id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int insert(FundsPackageType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int insertSelective(FundsPackageType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	List<FundsPackageType> selectByExample(FundsPackageTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	FundsPackageType selectByPrimaryKey(BigDecimal id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int updateByExampleSelective(@Param("record") FundsPackageType record,
			@Param("example") FundsPackageTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int updateByExample(@Param("record") FundsPackageType record,
			@Param("example") FundsPackageTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int updateByPrimaryKeySelective(FundsPackageType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDS_PACKAGE_TYPE
	 * @mbggenerated  Wed Jul 06 15:50:40 CST 2016
	 */
	int updateByPrimaryKey(FundsPackageType record);
}