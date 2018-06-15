package com.mrbt.oa.mvc.dao.funds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.funds.FundsThemeMapper;
import com.mrbt.oa.mvc.vo.funds.FundsTheme;

@Repository
public class FundsThemeDao extends
		BaseDao<FundsTheme, FundsThemeMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.funds.FundsThemeMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}
}
