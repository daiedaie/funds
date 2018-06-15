package com.mrbt.oa.mvc.dao.company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.company.MrbtCompanyUserMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUser;

@Repository
public class MrbtCompanyUserDao extends
		BaseDao<MrbtCompanyUser, MrbtCompanyUserMapper> {
	@Override
	@Value("com.mrbt.oa.mvc.mapper.company.MrbtCompanyUserMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		super.mapper_preffix = mapper_preffix;
	}

	/**
	 * 判断用户是否为admin超级用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean isAdmin(int id) {
		Integer result = this.getSqlSession().selectOne(
				mapper_preffix + "isAdmin", id);
		return result > 0 ? true : false;
	}

	/**
	 * 查找用户所有的权限、菜单、action
	 * 
	 * @param id
	 * @return
	 */
	public MrbtCompanyUser findMenuTree(int id) {
		return this.getSqlSession().selectOne(mapper_preffix + "findMenuTree",
				id);
	}
}
