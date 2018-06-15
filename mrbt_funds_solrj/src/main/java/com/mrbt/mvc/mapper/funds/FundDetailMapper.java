package com.mrbt.mvc.mapper.funds;

import java.util.List;

import com.mrbt.mvc.vo.FundDetailVo;



public interface FundDetailMapper {
	//根据基金代码查询详情的数据
	FundDetailVo getDetailsFromId(String fundcode);
	//生成详情页面的索引值使用
	List<FundDetailVo> getDetailsList();
}
