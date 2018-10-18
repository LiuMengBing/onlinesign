/**
 * Copyright 2015-2016 momodr.com & Co., Ltd.
 */
package com.example.msgnotice;

public class StringUtils {
	
	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String conversion(String[] array){
		return conversion(array, ",");
	}
	
	public static String conversion(String[] array, String symbol){
		String result = "";
		for (int i = 0; i < array.length; i++) {
			if(i == array.length - 1){
				result = result + array[i];
			}else{
				result = result + array[i] + symbol;
			}
		}
		return result;
	}
	
}
