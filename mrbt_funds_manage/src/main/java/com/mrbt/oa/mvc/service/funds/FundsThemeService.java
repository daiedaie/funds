package com.mrbt.oa.mvc.service.funds;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.funds.FundsThemeDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.funds.FundsTheme;
import com.mrbt.oa.mvc.vo.funds.FundsThemeExample;

@Service
public class FundsThemeService extends BaseService<FundsTheme, FundsThemeDao> {

	@Override
	public FundsThemeExample getExample(FundsTheme vo) {
		FundsThemeExample example = new FundsThemeExample();
		FundsThemeExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getName())) {
			cri.andNameLike("%" + vo.getName() + "%");
		}
		example.setOrderByClause("id");
		return example;
	}

}
