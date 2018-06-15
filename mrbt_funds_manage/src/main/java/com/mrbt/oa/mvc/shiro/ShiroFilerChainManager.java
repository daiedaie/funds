package com.mrbt.oa.mvc.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenuRRole;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-25
 * <p>
 * Version: 1.0
 */
@Service
public class ShiroFilerChainManager {

	@Autowired
	private DefaultFilterChainManager filterChainManager;

	private Map<String, NamedFilterList> defaultFilterChains;

	@PostConstruct
	public void init() {
		defaultFilterChains = new HashMap<String, NamedFilterList>(
				filterChainManager.getFilterChains());
	}

	public void initFilterChains(List<MrbtCompanyMenuRRole> urlFilters,
			List<MrbtCompanyMenuRRole> adminFilters) {
		// 1、首先删除以前老的filter chain并注册默认的
		filterChainManager.getFilterChains().clear();
		if (defaultFilterChains != null) {
			filterChainManager.getFilterChains().putAll(defaultFilterChains);
		}
		HashMap<String, Set<String>> rolsMap = new HashMap<String, Set<String>>();
		HashMap<String, Set<String>> permsMap = new HashMap<String, Set<String>>();
		// 所有的url加admin权限
		for (MrbtCompanyMenuRRole admin : adminFilters) {
			if (StringUtils.isNotBlank(admin.getActionEvent())) {
				Set<String> permsSet;
				if ((permsSet = permsMap.get(admin.getMenuUrl())) != null) {
					permsSet.add(admin.getActionEvent());
				} else {
					permsSet = new HashSet<String>();
					permsSet.add(admin.getActionEvent());
					permsMap.put(admin.getMenuUrl(), permsSet);
				}
			} else {
				HashSet<String> roleList = new HashSet<String>();
				roleList.add(admin.getrName());
				rolsMap.put(admin.getMenuUrl(), roleList);
			}
		}
		// 添加其他权限
		for (MrbtCompanyMenuRRole urlFilter : urlFilters) {
			if (StringUtils.isNotBlank(urlFilter.getMenuUrl())) {
				rolsMap.get(urlFilter.getMenuUrl()).add(urlFilter.getrName());
			}
		}
		// 添加roles
		for (Entry<String, Set<String>> entry : rolsMap.entrySet()) {
			filterChainManager.addToChain(
					entry.getKey(),
					"roles",
					entry.getValue().toString().replaceAll("\\s*", "")
							.replace("[", "").replace("]", ""));
		}
		// 添加perm
		for (Entry<String, Set<String>> entry : permsMap.entrySet()) {
			filterChainManager.addToChain(
					entry.getKey(),
					"perms",
					entry.getValue().toString().replaceAll("\\s*", "")
							.replace("[", "").replace("]", ""));
		}
		rolsMap.clear();
		permsMap.clear();
	}
}
