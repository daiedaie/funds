package com.mrbt.session;

import com.mrbt.user.model.Users;
import com.mrbt.user.model.UsersProperties;
import com.mrbt.user.model.UsresDegree;

public class UsersSession implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * users信息
	 */
	private Users users;// 当前用户
	private UsersProperties usersProperties;// 当前用户的属性信息
	private UsresDegree usresDegree;// 当前用户的等级

	public UsersSession() {
		super();
	}

	public UsresDegree getUsresDegree() {
		return usresDegree;
	}

	public void setUsresDegree(UsresDegree usresDegree) {
		this.usresDegree = usresDegree;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public UsersProperties getUsersProperties() {
		return usersProperties;
	}

	public void setUsersProperties(UsersProperties usersProperties) {
		this.usersProperties = usersProperties;
	}

}
