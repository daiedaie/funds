package com.mrbt.oa.mvc.dao.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.company.MrbtCompanyMenuRRoleMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenuRRole;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRole;
@Repository
public class MrbtCompanyMenuRRoleDao
		extends
			BaseDao<MrbtCompanyMenuRRole, MrbtCompanyMenuRRoleMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.company.MrbtCompanyMenuRRoleMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}

	public void saveRelation(MrbtCompanyRole role, String menuIds) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rId", role.getId());
		map.put("rName", role.getrName());
		List<Integer> list = new ArrayList<Integer>();
		if (StringUtils.isNotBlank(menuIds)) {
			for (String tmp : menuIds.split(",")) {
				list.add(Integer.parseInt(tmp));
			}
		}
		map.put("menu_ids", list);
		this.getSqlSession().insert(this.getMapper_preffix() + "saveRelation",
				map);
	}
	/**
	 * 手动插入根目录
	 * 
	 * @param role
	 */
	public void saveRelationRoot(MrbtCompanyRole role) {
		this.getSqlSession().insert(
				this.getMapper_preffix() + "saveRelationRoot", role);
	}
	/**
	 * 根据用户id查找权限
	 * 
	 * @param u_id
	 * @return
	 */
	public Set<String> listPermsByUId(Integer u_id) {
		List<String> reList = this.getSqlSession().selectList(
				this.getMapper_preffix() + "listPermsByUId", u_id);
		return new HashSet<String>(reList);
	}

	public List<MrbtCompanyMenuRRole> listAdmins() {
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "listAdmins");
	}

	public List<MrbtCompanyMenuRRole> listUrlByRole() {
		return this.getSqlSession().selectList(
				this.getMapper_preffix() + "listUrlByRole");
	}

}
