package com.mrbt.oa.mvc.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbt.oa.mvc.service.BaseService;
import com.mrbt.oa.mvc.vo.BaseVo;
import com.mrbt.utils.MyUtils;
import com.mrbt.utils.ResponseUtils;
import com.mrbt.utils.RowBoundsUtils;
import com.mrbt.utils.StringFromatUtils;

public class BaseController<T extends BaseVo, D extends BaseService<T, ?>> {
	@Autowired
	public D baseService;
	public Logger log = MyUtils.getLogger(BaseController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = { "show", "/" })
	public String index(HttpServletRequest req) {
		if (StringUtils.isNotBlank(req.getRequestURI())) {
			String path = req.getRequestURI().replace("/rest/", "");
			if (!path.endsWith("show")) {
				path += "show";
			}
			return path;
		}
		return null;
	}

	@RequestMapping("del")
	@ResponseBody
	public Object del(String id) {
		if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id)) {
			try {
				baseService.delete(new BigDecimal(id));
				return ResponseUtils.success();
			} catch (Exception ex) {
				log.error(ex);
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						ex.getMessage());
			}
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}
	}

	@RequestMapping("delChar")
	@ResponseBody
	public Object delChar(String id) {
		if (StringUtils.isNotBlank(id)) {
			String idChar = String.format("%-100s", id);
			try {
				baseService.delete(idChar);
				return ResponseUtils.success();
			} catch (Exception ex) {
				log.error(ex);
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						ex.getMessage());
			}
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}
	}

	@RequestMapping("delString")
	@ResponseBody
	public Object delString(String id) {
		if (StringUtils.isNotBlank(id)) {
			try {
				baseService.delete(id);
				return ResponseUtils.success();
			} catch (Exception ex) {
				log.error(ex);
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						ex.getMessage());
			}
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(String id) {
		if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id)
				&& NumberUtils.toInt(id) > 0) {
			try {
				baseService.delete(NumberUtils.toInt(id));
				return ResponseUtils.success();
			} catch (Exception ex) {
				log.error(ex);
				return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
						ex.getMessage());
			}
		} else {
			return ResponseUtils.failure(ResponseUtils.ERROR_PARAM, "参数错误");
		}
	}

	/**
	 * 查询
	 * 
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object list(T vo, String page, String rows, Integer limit) {
		if (limit != null) {
			return baseService.listGrid(vo,
					RowBoundsUtils.getRowBounds(page, rows, limit));
		} else {
			return baseService.listGrid(vo,
					RowBoundsUtils.getRowBounds(page, rows));
		}
	}

	/**
	 * 保存权限
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save(T vo) {
		try {
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
	@RequestMapping("update")
	@ResponseBody
	public Object update(T vo) {
		try {
			baseService.updateSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	@RequestMapping("updateByExampleSelective")
	@ResponseBody
	public Object updateByExampleSelective(T vo) {
		try {
			baseService.updateByExampleSelective(vo);
			return ResponseUtils.success();
		} catch (Exception ex) {
			log.error(ex);
			return ResponseUtils.failure(ResponseUtils.ERROR_SERVER,
					ex.getMessage());
		}
	}

	public D getBaseService() {
		return baseService;
	}

	public void setBaseService(D baseService) {
		this.baseService = baseService;
	}

}
