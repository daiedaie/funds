package com.mrbt.mvc.mapper.funds;
import java.util.List;

import com.mrbt.mvc.vo.FundMarketTableVo;


public interface FundMarketTableMapper {
	List<FundMarketTableVo> selectFundMarketTableList();
}
