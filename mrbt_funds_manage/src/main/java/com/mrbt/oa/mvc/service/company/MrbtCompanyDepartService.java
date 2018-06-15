package com.mrbt.oa.mvc.service.company;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.company.MrbtCompanyDepartDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyDepart;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyDepartExample;

@Service
public class MrbtCompanyDepartService
		extends
			BaseService<MrbtCompanyDepart, MrbtCompanyDepartDao> {

	@Override
	public MrbtCompanyDepartExample getExample(MrbtCompanyDepart vo) {
		MrbtCompanyDepartExample example = new MrbtCompanyDepartExample();
		MrbtCompanyDepartExample.Criteria cri = example.createCriteria();
		if (vo.getDpStatus() != null) {
			cri.andDpStatusEqualTo(vo.getDpStatus());
		}
		if (StringUtils.isNotBlank(vo.getDpName())) {
			cri.andDpNameLike("%" + vo.getDpName() + "%");
		}
		// TODO Auto-generated method stub
		return example;
	}
	/**
	 * 根据用户查找部门
	 * 
	 * @param uId
	 * @return
	 */
	public List<MrbtCompanyDepart> listDepartByUid(Integer uId) {
		return this.getGeneralDao().listDepartByUid(uId);
	}
	/**
	 * 根据用户id，或者部门名称查找用户不在的部门
	 * 
	 * @param uId
	 * @param dpName
	 * @return
	 */
	public List<MrbtCompanyDepart> listByUidNotRelation(Integer uId,
			String dpName) {
		return this.getGeneralDao().listByUidNotRelation(uId, dpName);
	}

}
