package com.mrbt.utils;

import java.util.Arrays;

public class NumberUtils {
	public static int getMaxNum(int...a){ 
        Arrays.sort(a); 
        int maxNum = a[a.length-1]; 
        return maxNum; 
    }
}
