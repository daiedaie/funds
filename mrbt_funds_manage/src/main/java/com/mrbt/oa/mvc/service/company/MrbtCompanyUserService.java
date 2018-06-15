package com.mrbt.oa.mvc.service.company;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.company.MrbtCompanyUserDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUser;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserExample;

@Service
public class MrbtCompanyUserService extends
		BaseService<MrbtCompanyUser, MrbtCompanyUserDao> {
	@Autowired
	public MrbtCompanyUserDao mrbtCompanyUserDao;

	@Override
	public Object getExample(MrbtCompanyUser vo) {
		MrbtCompanyUserExample example = new MrbtCompanyUserExample();
		MrbtCompanyUserExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getLoginName())) {
			cri.andLoginNameEqualTo(vo.getLoginName());
		}
		if (StringUtils.isNotBlank(vo.getLoginPass())) {
			cri.andLoginPassEqualTo(vo.getLoginPass());
		}
		if (vo.getLoginStatus() != null) {
			cri.andLoginStatusEqualTo(vo.getLoginStatus());
		}
		// TODO Auto-generated method stub
		return example;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean isAdmin(int id) {
		return mrbtCompanyUserDao.isAdmin(id);
	}

}
