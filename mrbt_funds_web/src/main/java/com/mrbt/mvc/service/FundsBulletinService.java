package com.mrbt.mvc.service;

import java.util.List;

import com.mrbt.mvc.model.FundsBulletin;

/**
 *@author yiban sun
 *@date 2016年6月20日 下午5:01:09
 *@version 1.0
 *@description 
 *@since
 **/
public interface FundsBulletinService {
	List<FundsBulletin> queryNewestOne();
}
