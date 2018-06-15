package com.mrbt.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.mvc.dao.FundsBulletinMapper;
import com.mrbt.mvc.model.FundsBulletin;
import com.mrbt.mvc.service.FundsBulletinService;

/**
 *@author yiban sun
 *@date 2016年6月20日 下午5:04:10
 *@version 1.0
 *@description 
 *@since
 **/
@Service("fundsBulletinService")
public class FundsBulletinServiceImpl implements FundsBulletinService{

	@Autowired
	private FundsBulletinMapper fundsBulletinMapper;
	
	@Override
	public List<FundsBulletin> queryNewestOne() {
		return fundsBulletinMapper.queryNewestOne();
	}

}
