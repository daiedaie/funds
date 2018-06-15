package com.mrbt.oa.mvc.service.company;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.dao.company.MrbtCompanyMenuDao;
import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenu;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenuExample;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.menu.MenuTree;

@Service
public class MrbtCompanyMenuService
		extends
			BaseService<MrbtCompanyMenu, MrbtCompanyMenuDao> {

	@Override
	public Object getExample(MrbtCompanyMenu vo) {
		MrbtCompanyMenuExample example = new MrbtCompanyMenuExample();
		MrbtCompanyMenuExample.Criteria cri = example.createCriteria();
		if (vo.getParentId() != null) {
			cri.andParentIdEqualTo(vo.getParentId());
		}
		example.setOrderByClause("id,seq");
		// TODO Auto-generated method stub
		return example;
	}
	public Set<String> listAllPerms() {
		return this.getGeneralDao().listAllPerms();
	}
	/**
	 * 根据用户id获取菜单
	 * 
	 * @param uid
	 * @return
	 */
	public MenuTree listTreeByUid(Integer uid) {
		List<MrbtCompanyMenu> menuList = this.getGeneralDao()
				.listTreeByUid(uid);
		return createTree(menuList);
	}

	/**
	 * 树形结构生成的json图
	 */
	public MenuTree listAllTree(Boolean widthAction) {
		MrbtCompanyMenuExample example = new MrbtCompanyMenuExample();
		MrbtCompanyMenuExample.Criteria cri = example.createCriteria();
		if (widthAction == null || widthAction == false) {
			cri.andTypeLessThan(AppCons.MENY_TYPE_EVENT);
		}
		example.setOrderByClause("id,seq");
		List<MrbtCompanyMenu> menuList = this.getGeneralDao().list(example,
				null);
		return createTree(menuList);
	}

	/**
	 * 生成树对象
	 * 
	 * @param menuList
	 * @return
	 */
	private MenuTree createTree(List<MrbtCompanyMenu> menuList) {
		MenuTree tmpTree;
		MenuTree root = null;
		HashMap<Integer, MenuTree> tmpMap = new HashMap<Integer, MenuTree>();
		for (MrbtCompanyMenu tmpMenu : menuList) {
			// 创建一个tree节点
			tmpTree = new MenuTree(tmpMenu.getId(), tmpMenu.getText(), null,
					tmpMenu.getIcon(), tmpMenu.getUrl());
			tmpMap.put(tmpTree.getId(), tmpTree);
			// 如果当前menu的pid不等于空，并且在Map中能查到pid对应的节点，则添加到次节点的children中
			if (tmpMenu.getParentId() != null) {
				if (tmpMap.containsKey(tmpMenu.getParentId().intValue())) {
					tmpMap.get(tmpMenu.getParentId().intValue()).getChildren()
							.add(tmpTree);
				}
			} else {
				root = tmpTree;
			}
		}
		tmpMap.clear();
		return root;
	}
}
