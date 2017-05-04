package com.coding.basic.string;

public class StringUtils {
	/**
	 * 朴素匹配
	 * @param str
	 * @param subString
	 * @param pos
	 * @return
	 */
	public int index(String str, String subString, int pos){
		int i = pos;
		char[] strChars = str.toCharArray();
		char[] subStringChars = subString.toCharArray();
		int j = 0;
		while(i<str.length() && j<subString.length()){
			if(strChars[i] == subStringChars[j]){
				i++;
				j++;
			}else{
				i = i - j + 1;
				j = 0;
			}		
		}
		if(j >= subString.length()){
			return i - j;
		}
		
		return -1;
	}
	
	public static int KMP(String str, String subString, int pos){
		
		return 0;
	}
	
	private static int[] next(char[] strChars){
		int length = strChars.length;
		int[] next = new int[length];
		
		
		return next;
	}
}
