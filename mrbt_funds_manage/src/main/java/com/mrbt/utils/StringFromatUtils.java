package com.mrbt.utils;

public class StringFromatUtils {
	public static String leftFill(int length, int code) {
		return String.format("%0" + length + "d", code);
	}

}
