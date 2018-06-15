package com.mrbt.oa.mvc.realm;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbt.oa.mvc.service.company.MrbtCompanyUserService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUser;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.MD5Utils;
import com.mrbt.utils.session.UserSession;

@Service
public class MrbtOaRealm extends AuthorizingRealm {
	@Autowired
	private MrbtCompanyUserService mrbtCompanyUserService;

	/**
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		// TODO Auto-generated method stub
		Session session = SecurityUtils.getSubject().getSession();
		UserSession sessionUser = (UserSession) session
				.getAttribute(AppCons.SESSION_USER);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(sessionUser.getRoles());
		authorizationInfo.setStringPermissions(sessionUser.getPerms());
		return authorizationInfo;

	}

	/**
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());
		MrbtCompanyUser user = new MrbtCompanyUser();
		user.setLoginName(username);
		user.setLoginPass(MD5Utils.MD5(password));
		user.setLoginStatus(AppCons.STATUS_OK);
		
		List<MrbtCompanyUser> list = mrbtCompanyUserService.list(user, new RowBounds(0, 1));
		if (list != null && list.size() > 0) {

			Subject currentUser = SecurityUtils.getSubject();

			Session session = currentUser.getSession();
			UserSession sessionUser = new UserSession();
			sessionUser.setUser(list.get(0));
			session.setAttribute(AppCons.SESSION_USER, sessionUser);
			return new SimpleAuthenticationInfo(upToken.getUsername(),
					upToken.getPassword(), this.getName());
		} else {
			throw new UnknownAccountException();// 没找到帐号
		}
	}

}
