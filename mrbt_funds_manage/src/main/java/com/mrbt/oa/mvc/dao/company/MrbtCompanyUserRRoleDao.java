package com.mrbt.oa.mvc.dao.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.company.MrbtCompanyUserRRoleMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRRole;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUserRRoleExample;

@Repository
public class MrbtCompanyUserRRoleDao extends
		BaseDao<MrbtCompanyUserRRole, MrbtCompanyUserRRoleMapper> {

	@Autowired
	public MrbtCompanyUserRRoleMapper mapper;

	@Override
	@Value("com.mrbt.oa.mvc.mapper.company.MrbtCompanyUserRRoleMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

	/**
	 * 根据用户id查找权限
	 * 
	 * @param uId
	 * @return
	 */
	public MrbtCompanyUserRRole findByUser(Integer uId) {
		List<MrbtCompanyUserRRole> reList = this.getSqlSession().selectList(
				this.getMapper_preffix() + "findByUser", uId);
		if (reList.size() > 0) {
			return reList.get(0);
		}
		return null;
	}

	public void deleteByExample(MrbtCompanyUserRRoleExample example) {
		mapper.deleteByExample(example);
	}

	public void deleteByUidRid(Integer u_id) {
		this.getSqlSession().delete(
				this.getMapper_preffix() + "deleteByUidRid", u_id);
	}

	public void saveByUidRid(Integer u_id, String r_ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("u_id", u_id);
		List<Integer> list = new ArrayList<Integer>();
		if (StringUtils.isNotBlank(r_ids)) {
			for (String tmp : r_ids.split(",")) {
				list.add(Integer.parseInt(tmp));
			}
		}

		map.put("r_ids", list);
		this.getSqlSession().insert(this.getMapper_preffix() + "saveByUidRid",
				map);
	}

}
