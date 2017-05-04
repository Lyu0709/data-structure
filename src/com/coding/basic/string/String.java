package com.coding.basic.string;

import java.util.Arrays;

/**
 * 串
 * 
 * @author Administrator
 *
 */
public class String {
	private char[] value;
	private int hash;	//hash值，默认为0
	
	public String(){
		value = new char[0];
	}
	
	public String(String original){
		this.value = original.value;
		this.hash = original.hash;
	}
	
	public String(char[] value){
		this.value = Arrays.copyOf(value, value.length);
	}
	
	
	public int hashCode(){
		int h = this.hash;
		
		if(h==0 && value.length>0){
			char[] val = value;
			for(int i=0; i<val.length; i++){
				h = h * 31 + val[i];
			}
			this.hash = h;
		}		
		
		return h;
	}
	
	public boolean startsWith(String prefix, int toffset){	
		char[] val = value;
		char[] prefVal = prefix.value;
		if(toffset < 0 || toffset > val.length - prefVal.length){
			return false;
		}
		int pos = 0;
		int to = toffset;
		while(pos < prefVal.length){
			if(val[to++]!=prefVal[pos++]){
				return false;
			}
		}	
		
		return true;
	}
	
	public boolean startsWith(String prefix){
		return startsWith(prefix, 0);
	}
	
	public boolean endsWith(String suffix){
		return startsWith(suffix, value.length-suffix.value.length);
	}
	
	public String concat(String str){
		int strlen = str.length();
		if(strlen==0){
			return this;
		}
		int len = value.length;
		char[] buf = Arrays.copyOf(value, strlen+len);
		for(int i=0; i<strlen; i++){
			buf[len+i] = str.value[i];
		}
		return new String(buf);
	}

	public int length() {		
		return value.length;
	}
	
	public String trim(){
		int length = value.length;
		int st = 0;
		char[] val = value;
		while((st < length) && (val[st] <= ' ' ) ){
			st++;
		}
		while((st < length) && (val[length-1] <= ' ' )){
			length--;
		}
		return (st>0 || length<value.length)?substring(st, length):this;
	}

	public String substring(int start, int end) {	//	左闭右开区间
		if(start < 0){
			new IndexOutOfBoundsException();
		}
		if(end > value.length){
			new IndexOutOfBoundsException();
		}
		if(start >= end){
			new IndexOutOfBoundsException();
		}
		char[] original = this.value;
		char[] val = new char[end-start];
		for(int i=start; i<end; i++){
			val[i-start] = original[i];
		}
		return new String(val);
	}
}
