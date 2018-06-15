package com.mrbt.oa.mvc.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrbt.utils.MyUtils;
import com.mrbt.utils.ResponseUtils;

/**
 * json级别的session过期
 * 
 * @author lzp
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	Logger log = MyUtils.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception exception) {
		// TODO Auto-generated method stub
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			if (exception instanceof PageSessionInvalidException) {
				writer.write("<script>window.top.location.href='/index.html'</script>");
			} else if (exception instanceof JsonSessionInvalidException) {
				ObjectMapper mapper = new ObjectMapper();
				writer.write(mapper.writeValueAsString(ResponseUtils.failure(
						ResponseUtils.ERROR_SESSION_INVALID, "用户超时，请重新登陆")));
			} else {
				ObjectMapper mapper = new ObjectMapper();
				writer.write(mapper.writeValueAsString(ResponseUtils.failure(
						ResponseUtils.ERROR_SERVER, exception.getMessage())));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return null;
	}
}
