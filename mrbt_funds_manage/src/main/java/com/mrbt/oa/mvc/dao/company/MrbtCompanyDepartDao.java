package com.mrbt.oa.mvc.dao.company;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mrbt.oa.mvc.dao.BaseDao;
import com.mrbt.oa.mvc.mapper.company.MrbtCompanyDepartMapper;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyDepart;

@Repository
public class MrbtCompanyDepartDao
		extends
			BaseDao<MrbtCompanyDepart, MrbtCompanyDepartMapper> {

	@Override
	@Value("com.mrbt.oa.mvc.mapper.company.MrbtCompanyDepartMapper.")
	public void setMapper_preffix(String mapper_preffix) {
		// TODO Auto-generated method stub
		super.mapper_preffix = mapper_preffix;
	}
	/**
	 * 根据用户查找部门,体现在user_properties中的adGrid1的链接
	 * 
	 * @param uId
	 * @return
	 */
	public List<MrbtCompanyDepart> listDepartByUid(Integer uId) {
		return this.getSqlSession().selectList(
				mapper_preffix + "listDepartByUid", uId);
	}
	/**
	 * 根据用户id，或者部门名称查找用户不在的部门，体现在depart_pluging中的查找url
	 * 
	 * @param uId
	 * @param dpName
	 * @return
	 */
	public List<MrbtCompanyDepart> listByUidNotRelation(Integer uId,
			String dpName) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uId", uId);
		paramMap.put("dpName", dpName);
		return this.getSqlSession().selectList(
				mapper_preffix + "listByUidNotRelation", paramMap);
	}

}
