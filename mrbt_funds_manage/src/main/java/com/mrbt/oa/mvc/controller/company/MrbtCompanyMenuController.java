package com.mrbt.oa.mvc.controller.company;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.controller.BaseController;
import com.mrbt.oa.mvc.service.company.MrbtCompanyMenuService;
import com.mrbt.oa.mvc.vo.company.MrbtCompanyMenu;
import com.mrbt.utils.AppCons;
import com.mrbt.utils.GridPage;
import com.mrbt.utils.ResponseUtils;
import com.mrbt.utils.RowBoundsUtils;
import com.mrbt.utils.menu.MenuTree;
import com.mrbt.utils.session.UserSession;

@Controller
@RequestMapping("company/menu")
public class MrbtCompanyMenuController
		extends
			BaseController<MrbtCompanyMenu, MrbtCompanyMenuService> {

	/**
	 * 查找树节点
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("listMenuBySession")
	@ResponseBody
	public Object listMenuBySession(HttpSession session) {
		UserSession currentUser = (UserSession) SecurityUtils.getSubject()
				.getSession().getAttribute(AppCons.SESSION_USER);
		return currentUser.getMenuMap();
	}

	/**
	 * 查找树节点
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("listAllTree")
	@ResponseBody
	public Object listAllTree() {
		MenuTree tree = this.getBaseService().listAllTree(null);
		return tree.getChildren();
	}

	/**
	 * 查找树节点
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("listAllTreeByRoot")
	@ResponseBody
	public Object listAllTreeByRoot(Boolean widthAction) {
		List<MenuTree> reList = new ArrayList<MenuTree>();
		reList.add(this.getBaseService().listAllTree(widthAction));
		return reList;

	}

	/**
	 * 查找树节点
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("listByPid")
	@ResponseBody
	public Object listByPid(String pid, String page, String rows) {
		MrbtCompanyMenu mrbtCompanyMenu = new MrbtCompanyMenu();
		if (StringUtils.isNotBlank(pid) && NumberUtils.isNumber(pid)) {
			mrbtCompanyMenu.setParentId(NumberUtils.toInt(pid));
			return this.getBaseService().list(mrbtCompanyMenu,
					RowBoundsUtils.getRowBounds(page, rows));
		}
		return new GridPage<MrbtCompanyMenu>();
	}

	/**
	 * 保存权限
	 * 
	 * @param vo
	 * @return
	 */
	@Override
	@RequestMapping("save")
	@ResponseBody
	public Object save(MrbtCompanyMenu vo) {
		try {
			if (StringUtils.isNotBlank(vo.getUrl())) {
				vo.setUrl(initUrl(vo.getUrl(), vo.getParentId(), vo.getType()));
			}
			baseService.saveSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	/**
	 * 更新权限
	 * 
	 * @param vo
	 * @return
	 */
	@Override
	@RequestMapping("update")
	@ResponseBody
	public Object update(MrbtCompanyMenu vo) {
		try {
			if (StringUtils.isNotBlank(vo.getUrl())) {
				vo.setUrl(initUrl(vo.getUrl(), vo.getParentId(), vo.getType()));
			}
			baseService.updateSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}
	/**
	 * 初始化url链接
	 * 
	 * @param url
	 * @param pid
	 * @return
	 */
	private String initUrl(String url, Integer pid, Integer type) {
		// 链接
		if (type != null && type == 1) {
			if (!url.endsWith("/")) {
				url = url + "/";
			}
			if (!url.startsWith("/")) {
				url = "/" + url;
			}
			if (!url.startsWith("/rest")) {
				url = "/rest/" + (url.startsWith("/") ? url.substring(1) : url);
			}
			return url;
		}
		if (type != null && type == 2) {
			if (url.startsWith("/p") || url.startsWith("p/")) {
				MrbtCompanyMenu vo = this.getBaseService().listById(pid);
				return vo.getUrl() + url.replace("/p/", "");
			}
		}
		return url;
	}

}
