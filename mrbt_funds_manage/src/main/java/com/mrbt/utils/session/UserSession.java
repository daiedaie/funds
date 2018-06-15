package com.mrbt.utils.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import com.mrbt.oa.mvc.vo.company.MrbtCompanyUser;

public class UserSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * user信息
	 */
	public MrbtCompanyUser user;
	/**
	 * 导航条的所有信息
	 */
	public HashMap<String, Object> menuMap;
	/**
	 * 权限集合
	 */
	public Set<String> roles;
	/**
	 * 事件集合
	 */
	public Set<String> perms;
	public UserSession() {
		super();
	}

	public MrbtCompanyUser getUser() {
		return user;
	}

	public void setUser(MrbtCompanyUser user) {
		this.user = user;
	}

	public HashMap<String, Object> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(HashMap<String, Object> menuMap) {
		this.menuMap = menuMap;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getPerms() {
		return perms;
	}

	public void setPerms(Set<String> perms) {
		this.perms = perms;
	}

}
