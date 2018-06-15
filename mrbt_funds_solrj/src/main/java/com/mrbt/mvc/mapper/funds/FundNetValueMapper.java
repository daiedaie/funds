package com.mrbt.mvc.mapper.funds;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mrbt.mvc.model.funds.AllFundLatestNetValueVo;
import com.mrbt.mvc.model.funds.Ths001;
import com.mrbt.mvc.model.funds.Ths043;

public interface FundNetValueMapper {

	// 根据基金代码查询它的全部净值(针对单条基金)
	List<Ths043> selectAllNetValueByFundCode(@Param("fundcode") String fundcode);

	// 查询数据库基金的总数量,返回基金代码到List集合
	List<Ths001> selectAllFundCodeCount();

	// 根据的最新净值(时间&净值Value,针对单条基金)
	String selectLatestNetValueByFundCode(@Param("fundcode") String fundcode);

	// 查询数据库中全部基金的最新净值,返回基金代码:时间:最新净值
	List<AllFundLatestNetValueVo> selectAllFundLatestNetValue();

	// 查询一个月的净值
	List<AllFundLatestNetValueVo> selectAllFund30NetValue();
	
	//查询单条基金的一个月净值通过基金代码查询
	List<AllFundLatestNetValueVo> selectSingleFund30NetValueByCode(@Param("fundcode") String fundcode);

	// 查询月净值,传入时间范围参数
	List<AllFundLatestNetValueVo> selectAllFund30NetValueByDate(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate);

}