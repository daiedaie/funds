package com.mrbt.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLTransCoding  {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String encode = encode("北京");
		System.err.println(encode);
		String decode = decode("%E5%8C%97%E4%BA%AC");
		System.err.println(decode);
		String input = "http://www.altavista.com/cgi-bin/query?pg=q&kl=XX&stype=stext&q=%2B%22Java+I%2FO%22&search.x=38&search.y=3";
		input = URLEncoder.encode(input, "UTF-8");
		System.err.println(input);
		String output = URLDecoder.decode(input, "UTF-8");
		System.out.println(output);
	}
	
	public static String encode(String content) throws UnsupportedEncodingException{
		return URLEncoder.encode(content,"UTF-8");
	}
	public static String decode(String content) throws UnsupportedEncodingException{
		return URLDecoder.decode(content,"UTF-8");
	}
	public static String encode(String content,String env) throws UnsupportedEncodingException{
		return URLEncoder.encode(content,env);
	}
	public static String decode(String content,String env) throws UnsupportedEncodingException{
		return URLDecoder.decode(content,env);
	}
	
}
