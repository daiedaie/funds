package com.mrbt.oa.mvc.dao.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.company.MrbtCompanyUserRDepartMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRDepart;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRDepartExample;

@Repository
public class MrbtCompanyUserRDepartDao
		extends
			BaseDao<MrbtCompanyUserRDepart, MrbtCompanyUserRDepartMapper> {
	@Autowired
	public MrbtCompanyUserRDepartMapper mrbtCompanyUserRDepartMapper;

	@Override
	@Value("com.mrbt.oa.mvc.mapper.company.MrbtCompanyUserRDepartMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

	public void deleteByExample(MrbtCompanyUserRDepartExample example) {
		mapper.deleteByExample(example);
	}

	/**
	 * 根据部门查找员工
	 * 
	 * @param did
	 * @return
	 */
	public List<MrbtCompanyUserRDepart> findUserByDepart(Integer did) {
		if (did == null || did <= 0) {
			return new ArrayList<MrbtCompanyUserRDepart>(0);
		}
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "findUserByDepart", did);
	}

	/**
	 * 根据用户查找部门
	 * 
	 * @param uid
	 * @return
	 */
	public List<MrbtCompanyUserRDepart> findDepartByUser(Integer uid) {
		if (uid == null || uid <= 0) {
			return new ArrayList<MrbtCompanyUserRDepart>(0);
		}
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "findDepartByUser", uid);
	}
	/**
	 * 保存用户部门关系
	 * 
	 * @param u_id
	 * @param dp_ids
	 */
	public void saveByUidDpid(Integer u_id, String dp_ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("u_id", u_id);
		List<Integer> list = new ArrayList<Integer>();
		if (StringUtils.isNotBlank(dp_ids)) {
			for (String tmp : dp_ids.split(",")) {
				list.add(Integer.parseInt(tmp));
			}
		}

		map.put("dp_ids", list);
		this.getSqlSession().insert(this.getMapper_preffix() + "saveByUidDpid",
				map);
	}

	public void updateUserRDepart(MrbtCompanyUserRDepart record,
			MrbtCompanyUserRDepartExample example) {
		this.getMapper().updateByExample(record, example);
	}
}
