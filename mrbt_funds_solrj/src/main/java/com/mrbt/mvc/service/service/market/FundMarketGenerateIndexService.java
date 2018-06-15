package com.mrbt.mvc.service.service.market;

import java.util.Map;


public interface FundMarketGenerateIndexService {

	boolean generateFundMarketIndex();


	Map<String, Map<String, Object>> calculateGain();

}
