package com.mrbt.oa.mvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mrbt.oa.mvc.exception.JsonSessionInvalidException;
import com.mrbt.oa.mvc.exception.PageSessionInvalidException;
import com.mrbt.utils.AppCons;

/**
 * 处理session的拦截
 * 
 * @author lzp
 *
 */
public class SecurityInterceptor implements HandlerInterceptor {
	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// excluded URLs:
		// see
		// http://stackoverflow.com/questions/9908124/spring-mvc-3-interceptor-on-all-excluding-some-defined-paths
		String requestUri = request.getRequestURI();

		for (String url : excludedUrls) {
			if (requestUri.indexOf(url) >= 0) {
				return true;
			}
		}
		// intercept
		HttpSession session = request.getSession();
		if (session.getAttribute(AppCons.SESSION_USER) == null) {
			if (requestUri.indexOf(AppCons.PAGE_PREFFIX) >= 0) {
				throw new PageSessionInvalidException("用户超时");
			} else {
				throw new JsonSessionInvalidException("用户超时,请重新登陆");
			}
			// see
			// http://stackoverflow.com/questions/12713873/spring-3-1-how-do-you-send-all-exception-to-one-page
		} else {
			return true;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}
}
