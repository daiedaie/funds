package com.mrbt.oa.mvc.dao.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.company.MrbtCompanyRoleMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRole;

@Repository
public class MrbtCompanyRoleDao
		extends
			BaseDao<MrbtCompanyRole, MrbtCompanyRoleMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.company.MrbtCompanyRoleMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		super.mapper_preffix = mapper_preffix;
	}

	/**
	 * 根据uid查找权限，并且设置checked
	 * 
	 * @param uid
	 * @return
	 */
	public List<MrbtCompanyRole> listByCheckUid(Integer uid, String rName) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uId", uid);
		paramMap.put("rName", rName);
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "listByCheckUid", paramMap);
	}
	/**
	 * 根据用户id查找权限
	 * 
	 * @param uid
	 * @return
	 */
	public List<MrbtCompanyRole> listByUid(Integer uid) {
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "listByUid", uid);
	}
	/**
	 * 查找权限字符串
	 * 
	 * @param u_id
	 * @return
	 */
	public Set<String> listRolesByUid(Integer u_id) {
		List<String> roles = this.getSqlSession().selectList(
				this.getMapper_preffix() + "listRolesByUid", u_id);
		return new HashSet<String>(roles);
	}
}
