package com.mrbt.session;

import javax.servlet.http.HttpSession;

import com.mrbt.user.model.AppCons;
import com.mrbt.user.model.Users;
import com.mrbt.user.model.UsersProperties;
import com.mrbt.user.model.UsresDegree;

public class UserSessionRefreshUtil {
	public static void sessoinRefresh(HttpSession session, Users users, UsersProperties usersProperties,
			UsresDegree usresDegree) {
		UsersSession usession = new UsersSession();
		usession.setUsers(users);
		usession.setUsersProperties(usersProperties);
		usession.setUsresDegree(usresDegree);
		session.removeAttribute(AppCons.SESSION_USER);
		session.setAttribute(AppCons.SESSION_USER, usession);
	}

}
