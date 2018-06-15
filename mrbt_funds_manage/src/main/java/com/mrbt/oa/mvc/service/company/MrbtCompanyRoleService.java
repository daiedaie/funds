package com.mrbt.oa.mvc.service.company;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.company.MrbtCompanyRoleDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRole;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRoleExample;

@Service
public class MrbtCompanyRoleService
		extends
			BaseService<MrbtCompanyRole, MrbtCompanyRoleDao> {

	@Override
	public Object getExample(MrbtCompanyRole vo) {
		// TODO Auto-generated method stub
		MrbtCompanyRoleExample example = new MrbtCompanyRoleExample();
		MrbtCompanyRoleExample.Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(vo.getrName())) {
			cri.andRNameEqualTo(vo.getrName());
		}
		// TODO Auto-generated method stub
		return example;
	}

	/**
	 * 根据uid查询role权限，如果存在，则checked为true
	 * 
	 * @param uid
	 * @return
	 */
	public List<MrbtCompanyRole> listByCheckUid(Integer uid, String rName) {
		return this.getGeneralDao().listByCheckUid(uid, rName);
	}
	/**
	 * 根据用户id查找权限
	 * 
	 * @param uid
	 * @return
	 */
	public List<MrbtCompanyRole> listByUid(Integer uid) {
		return this.getGeneralDao().listByUid(uid);
	}
	/**
	 * 根据用户查找权限
	 * 
	 * @param u_id
	 * @return
	 */
	public Set<String> listRolesByUid(Integer u_id) {
		return this.getGeneralDao().listRolesByUid(u_id);
	}
}
