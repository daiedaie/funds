package com.mrbt.oa.mvc.service.company;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrbt.oa.mvc.dao.company.MrbtCompanyUserRDepartDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRDepart;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRDepartExample;

@Service
public class MrbtCompanyUserRDepartService
		extends
			BaseService<MrbtCompanyUserRDepart, MrbtCompanyUserRDepartDao> {

	@Override
	public MrbtCompanyUserRDepartExample getExample(MrbtCompanyUserRDepart vo) {
		// TODO Auto-generated method stub
		MrbtCompanyUserRDepartExample example = new MrbtCompanyUserRDepartExample();
		MrbtCompanyUserRDepartExample.Criteria cri = example.createCriteria();
		if (vo.getDpId() != null && vo.getDpId() > 0) {
			cri.andDpIdEqualTo(vo.getDpId());
		}
		if (vo.getuId() != null && vo.getuId() > 0) {
			cri.andUIdEqualTo(vo.getuId());
		}
		return example;
	}
	/**
	 * 根据部门查找用户
	 * 
	 * @param did
	 * @return
	 */
	public List<MrbtCompanyUserRDepart> findUserByDepart(Integer did) {
		return this.generalDao.findUserByDepart(did);
	}
	/**
	 * 根据用户id查找部门
	 * 
	 * @param uid
	 * @return
	 */
	public List<MrbtCompanyUserRDepart> findDepartByUser(Integer uid) {
		return this.generalDao.findDepartByUser(uid);
	}
	@Transactional
	public void delete(Integer uid, Integer did) {
		MrbtCompanyUserRDepart vo = new MrbtCompanyUserRDepart();
		vo.setuId(uid);
		vo.setDpId(did);
		this.getGeneralDao().deleteByExample(getExample(vo));
	}
	/**
	 * 根据uid，dpid删除关系
	 * 
	 * @param vo
	 */
	@Transactional
	public void deleteByUidDpid(MrbtCompanyUserRDepart vo) {
		this.getGeneralDao().deleteByExample(getExample(vo));
	}
	/**
	 * 保存用户和部门的关系
	 * 
	 * @param u_id
	 * @param dp_ids
	 */
	@Transactional
	public void updateUserRDepart(Integer u_id, String dp_ids) {
		if (StringUtils.isNotBlank(dp_ids)) {
			this.getGeneralDao().saveByUidDpid(u_id, dp_ids);
		}
	}
	/**
	 * 根据uid，dpid更新关联表
	 * 
	 * @param vo
	 */
	@Transactional
	public void updateUserRDepart(MrbtCompanyUserRDepart vo) {
		this.getGeneralDao().updateUserRDepart(vo, getExample(vo));
	}

}
