package com.mrbt.oa.mvc.exception;

/**
 * 页面级别的session过期
 * 
 * @author lzp
 *
 */
public class PageSessionInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PageSessionInvalidException(String message) {
		super(message);
	}

}
