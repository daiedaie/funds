package com.mrbt.mvc.service.database;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.mapper.funds.FundNetValueMapper;
import com.mrbt.mvc.model.funds.AllFundLatestNetValueVo;
import com.mrbt.utils.StringCustomerUtils;

@Service
public class FundDBHandlerServiceImpl implements FundDBHandlerService {
	@Autowired
	private FundNetValueMapper fundNetValueMapper;
	/*** 
	 * 基金超市列表基金的一个月净值
	 */
	@Override
	public Map<String, List<String>> selectAllMonthNetValueAll() {
		List<AllFundLatestNetValueVo> fundcodes = fundNetValueMapper.selectAllFund30NetValue();
		return combineMap(fundcodes);
	}
	
	
	/***
	 * 单条基金的一个月净值
	 */
	@Override
	public Map<String, List<String>> selectSingleFund30NetValueByCode(String fundCode) {
		List<AllFundLatestNetValueVo> fundcodes = fundNetValueMapper.selectSingleFund30NetValueByCode(fundCode);
		return combineMap(fundcodes);
	}
	
	private Map<String, List<String>> combineMap(List<AllFundLatestNetValueVo> fundcodes){
		Map<String,List<String>> netValues = new LinkedHashMap<String,List<String>>();
		if(CollectionUtils.isNotEmpty(fundcodes)){
			List<String> list = null;
			for(AllFundLatestNetValueVo netValueVo : fundcodes){
				String fundcode = netValueVo.getF002Ths001();
				String netvalue = netValueVo.getF003Ths043()+"";
				String createtime = netValueVo.getCtimeThs043();
				if(StringCustomerUtils.isEmpty(fundcode) || StringCustomerUtils.isEmpty(netvalue) || StringCustomerUtils.isEmpty(createtime)){
					continue;
				}
				String value = createtime+"&"+netvalue;
				if(netValues.containsKey(fundcode)){
					list = netValues.get(fundcode);
					list.add(value);
					//lists.remove(fundcode);
					netValues.put(fundcode,list);
				}
				else{
					list = new ArrayList<String>(0);
					list.add(value);
					netValues.put(fundcode,list);
				}
			}
		}
		return netValues;
	}
}
