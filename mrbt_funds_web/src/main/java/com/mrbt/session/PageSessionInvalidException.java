package com.mrbt.session;

/**
 * 页面级别的session过期
 * 
 * @author lzp
 *
 */
public class PageSessionInvalidException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String ERR_CODE ="700";

	public PageSessionInvalidException(String message) {
		super(message);
	}

}
