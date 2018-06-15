package com.mrbt.oa.mvc.service.company;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrbt.oa.mvc.dao.company.MrbtCompanyUserRRoleDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRRole;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRRoleExample;

/**
 * 查看用户权限的service
 * 
 * @author airgilbert
 *
 */
@Service
public class MrbtCompanyUserRRoleService
		extends
			BaseService<MrbtCompanyUserRRole, MrbtCompanyUserRRoleDao> {
	@Autowired
	public MrbtCompanyUserRRoleDao mrbtCompanyUserRRoleDao;

	@Override
	public MrbtCompanyUserRRoleExample getExample(MrbtCompanyUserRRole vo) {
		MrbtCompanyUserRRoleExample example = new MrbtCompanyUserRRoleExample();
		MrbtCompanyUserRRoleExample.Criteria cri = example.createCriteria();
		if (vo.getrId() != null) {
			cri.andRIdEqualTo(vo.getrId());
		}
		if (vo.getuId() != null) {
			cri.andUIdEqualTo(vo.getuId());
		}
		return example;
	}

	public MrbtCompanyUserRRole findByUser(Integer uId) {
		return mrbtCompanyUserRRoleDao.findByUser(uId);
	}

	@Transactional
	public void deleteByUidRid(MrbtCompanyUserRRole vo) {
		mrbtCompanyUserRRoleDao.deleteByExample(getExample(vo));
	}

	@Transactional
	public void updateUserRRole(Integer u_id, String r_ids) {
		if (StringUtils.isNotBlank(r_ids)) {
			mrbtCompanyUserRRoleDao.saveByUidRid(u_id, r_ids);
		}
	}

}
