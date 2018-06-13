package com.dsb.tools;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public static String encode(String str) throws  Exception{
		String sOut = "";
		try {
	    	Base64 bs = new Base64();
	    	sOut = new String(bs.encode(str.getBytes()));
		} catch (Exception e) {
			throw e;
		}
		return sOut;
	}
	
	/**
	 * 解密
	 * @param str
	 * @return
	 */
	public static String decode(String str) throws  Exception{
		String sOut = "";
		try {
	    	Base64 bs = new Base64();
	    	sOut = new String(bs.decode(str));
		} catch (Exception e) {
			throw e;
		}
		return sOut;
	}
	
	/*
	 * 测试程序
	 */
    public static void main(String[] args) throws Exception {
    	String sMsg = "今天吃的阿斯顿节哈斯看见的哈萨克";
    	String msg1 = encode(sMsg);
    	String msg2 = decode(msg1);
		System.out.println(msg1);
		System.out.println(msg2);
    }
}
