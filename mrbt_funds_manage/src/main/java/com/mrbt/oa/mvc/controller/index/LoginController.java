package com.mrbt.oa.mvc.controller.index;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.funds.FundsPackageRatioController;
import com.mrbt.oa.mvc.service.company.MrbtCompanyMenuRRoleService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyMenuService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyRoleService;
import com.mrbt.oa.mvc.service.company.MrbtCompanyUserService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyUser;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.MD5Utils;
import com.mrbt.utils.MyUtils;
import com.mrbt.utils.ResponseUtils;
import com.mrbt.utils.menu.MenuTree;
import com.mrbt.utils.session.UserSession;

/**
 * 用户登录注销的类
 * 
 * @author airgilbert
 *
 */
@Controller
public class LoginController {
	private Logger log = MyUtils.getLogger(LoginController.class);
	@Autowired
	MrbtCompanyUserService mrbtCompanyUserService;
	@Autowired
	MrbtCompanyMenuService mrbtCompanyMenuService;
	@Autowired
	MrbtCompanyMenuRRoleService mrbtCompanyMenuRRoleService;
	@Autowired
	MrbtCompanyRoleService mrbtCompanyRoleService;

	@RequestMapping("login")
	public Object login(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req
				.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(
				exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		return "login";
	}

	/**
	 * 判断是ajax访问
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", produces = "application/json")
	@ResponseBody
	public Object loginAjax(HttpServletRequest req, Model model) {
		return ResponseUtils.failure(ResponseUtils.ERROR_SESSION_INVALID, "超时");
	}

	@RequestMapping("/")
	public String index(HttpSession session) {
		UserSession sessionUser = (UserSession) session
				.getAttribute(AppCons.SESSION_USER);

		MrbtCompanyUser updateUser = new MrbtCompanyUser();

		// 防止用浏览器后台页面报错问题

		if (sessionUser == null) {
			return "login";
		}

		updateUser.setId(sessionUser.getUser().getId());
		updateUser.setLastDt(new Date());
		mrbtCompanyUserService.updateSelective(updateUser);
		updateUser = null;

		MenuTree tree = null;
		// 判断用户是否为管理员
		if (mrbtCompanyUserService.isAdmin(sessionUser.getUser().getId())) {
			tree = mrbtCompanyMenuService.listAllTree(null);
			sessionUser.setPerms(mrbtCompanyMenuService.listAllPerms());

		} else {
			tree = mrbtCompanyMenuService.listTreeByUid(sessionUser.getUser()
					.getId());
			sessionUser.setPerms(mrbtCompanyMenuRRoleService
					.listPermsByUId(sessionUser.getUser().getId()));
		}
		sessionUser.setRoles(mrbtCompanyRoleService.listRolesByUid(sessionUser
				.getUser().getId()));
		// 添加树状菜单到session中
		if (tree != null) {
			HashMap<String, Object> menuMap = new HashMap<String, Object>();
			menuMap.put("menus", tree.getChildren());
			sessionUser.setMenuMap(menuMap);
		}
		session.setAttribute(AppCons.SESSION_USER, sessionUser);
		return "main";
	}

	/**
	 * 注销用户
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		SecurityUtils.getSubject().logout();
		// session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/editpassword")
	public @ResponseBody Object updatePsw(HttpSession session, String newpass) {
		if (StringUtils.isNotBlank(newpass)) {
			MrbtCompanyUser user = ((UserSession) session
					.getAttribute(AppCons.SESSION_USER)).getUser();
			MrbtCompanyUser updateUser = new MrbtCompanyUser();

			updateUser.setDepId(user.getDepId());
			updateUser.setLoginName(user.getLoginName());
			updateUser.setId(user.getId());
			updateUser.setLoginPass(MD5Utils.MD5(newpass));
			updateUser.setLoginStatus(user.getLoginStatus());
			try {
				mrbtCompanyUserService.update(updateUser);
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"
						+ ResponseUtils.success());
				return ResponseUtils.success();
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						e.getMessage());
			}
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}
	}
}
