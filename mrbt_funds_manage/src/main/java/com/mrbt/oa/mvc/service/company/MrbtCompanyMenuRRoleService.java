package com.mrbt.oa.mvc.service.company;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrbt.oa.mvc.dao.company.MrbtCompanyMenuRRoleDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.shiro.ShiroFilerChainManager;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenuRRole;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenuRRoleExample;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyRole;
@Service
public class MrbtCompanyMenuRRoleService
		extends
			BaseService<MrbtCompanyMenuRRole, MrbtCompanyMenuRRoleDao> {
	@Autowired
	private ShiroFilerChainManager shiroFilerChainManager;
	@Override
	public MrbtCompanyMenuRRoleExample getExample(MrbtCompanyMenuRRole vo) {
		// TODO Auto-generated method stub
		MrbtCompanyMenuRRoleExample example = new MrbtCompanyMenuRRoleExample();
		MrbtCompanyMenuRRoleExample.Criteria cri = example.createCriteria();
		if (vo.getrId() != null && vo.getrId() > 0) {
			cri.andRIdEqualTo(vo.getrId());
		}
		return example;
	}

	/**
	 * 根据用户名查找权限
	 * 
	 * @param loginName
	 * @return
	 */
	public Set<String> listPermsByUId(Integer u_id) {
		return this.generalDao.listPermsByUId(u_id);
	}

	public List<MrbtCompanyMenuRRole> listAdmins() {
		return this.generalDao.listAdmins();
	}
	/**
	 * 保存关系
	 * 
	 * @param role
	 * @param menuIds
	 */
	@Transactional
	public void saveRelation(MrbtCompanyRole role, String menuIds) {
		this.deleteByRid(role.getId());
		this.getGeneralDao().saveRelation(role, menuIds);
		this.getGeneralDao().saveRelationRoot(role);
		initFilterChain();
	}
	/**
	 * 根据r_id删除
	 * 
	 * @param r_id
	 */
	@Transactional
	public void deleteByRid(Integer r_id) {
		MrbtCompanyMenuRRole vo = new MrbtCompanyMenuRRole();
		vo.setrId(r_id);
		this.getGeneralDao().mapper.deleteByExample(getExample(vo));
		initFilterChain();
	}

	// @PostConstruct
	public void initFilterChain() {
		shiroFilerChainManager.initFilterChains(
				this.generalDao.listUrlByRole(), listAdmins());
	}
}
