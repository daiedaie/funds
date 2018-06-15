package com.mrbt.oa.mvc.dao.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.company.MrbtCompanyMenuMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenu;

@Repository
public class MrbtCompanyMenuDao
		extends
			BaseDao<MrbtCompanyMenu, MrbtCompanyMenuMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.company.MrbtCompanyMenuMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		super.mapper_preffix = mapper_preffix;
	}

	/**
	 * 根据用户id获取menu
	 * 
	 * @param uid
	 * @return
	 */
	public List<MrbtCompanyMenu> listTreeByUid(Integer uid) {
		return this.getSqlSession().selectList(
				this.mapper_preffix + "listTreeByUid", uid);
	}
	/**
	 * 查找所有权限
	 * 
	 * @return
	 */
	public Set<String> listAllPerms() {
		List<String> reList = this.getSqlSession().selectList(
				this.mapper_preffix + "listAllPerms");
		return new HashSet<String>(reList);
	}
}
