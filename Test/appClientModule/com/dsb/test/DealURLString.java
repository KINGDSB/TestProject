package com.dsb.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;

public class DealURLString {

	public static void main(String[] args) {
//		File file = new File("acp_sdk.properties");
//
//		FileInputStream fileInputStream = null;
//		
//		try {
//			fileInputStream = new FileInputStream(file);
//			
//			byte[] arrByte = new byte[(int) file.length()];
//			fileInputStream.read(arrByte);
//			String content = new String(arrByte);
//			System.out.println(content);
//			
//			String decodeString = URLDecoder.decode(content);
//			
//			System.out.println(decodeString);
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			
//			try {
//				fileInputStream.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}

		
		StringBuilder sb = new StringBuilder("");

		sb.append(URLDecoder.decode("\u914d\u7f6e\u6587\u4ef6   \u914d\u7f6e\u6587\u4ef6\u4e2d\u65e5\u5fd7\u548c\u8bc1\u4e66\u7684\u5b58\u653e\u8def\u5f84\u6839\u636e\u5b9e\u9645\u60c5\u51b5\u914d\u7f6e\uff0c\u4ea4\u6613\u5730\u5740\u548c\u8bc1\u4e66\u6839\u636ePM\u73af\u5883\u3001\u751f\u4ea7\u73af\u5883\u914d\u5957\u914d\u7f6e")+"\n");
		sb.append(URLDecoder.decode("\u4ea4\u6613\u53d1\u9001\u5730\u5740\u914d\u7f6e")+"\n");
		sb.append(URLDecoder.decode("\u4ee5\u4e0b\u914d\u7f6e\u4e3aPM\u73af\u5883\uff1a\u5165\u7f51\u6d4b\u8bd5\u73af\u5883\u7528\uff0c\u751f\u4ea7\u73af\u5883\u914d\u7f6e\u89c1\u6587\u6863\u8bf4\u660e")+"\n\n");
		
		sb.append(URLDecoder.decode("\u524d\u53f0\u4ea4\u6613\u8bf7\u6c42\u5730\u5740")+"\n");
		sb.append(URLDecoder.decode("\u540e\u53f0\u4ea4\u6613\u8bf7\u6c42\u5730\u5740")+"\n");
		sb.append(URLDecoder.decode("\u540e\u53f0\u4ea4\u6613\u8bf7\u6c42\u5730\u5740(\u82e5\u4e3a\u6709\u5361\u4ea4\u6613\u914d\u7f6e\u8be5\u5730\u5740)")+"\n");
		sb.append(URLDecoder.decode("\u5355\u7b14\u67e5\u8be2\u8bf7\u6c42\u5730\u5740")+"\n");
		sb.append(URLDecoder.decode("\u6279\u91cf\u4ea4\u6613\u8bf7\u6c42\u5730\u5740")+"\n");
		sb.append(URLDecoder.decode("\u6587\u4ef6\u4f20\u8f93\u7c7b\u4ea4\u6613\u5730\u5740")+"\n");
		sb.append(URLDecoder.decode("app"+"\u4EA4\u6613\u8BF7\u6C42\u5730\u5740")+"\n\n");
		
		sb.append(URLDecoder.decode("\u7b7e\u540d\u8bc1\u4e66\u914d\u7f6e")+"\n");
		sb.append(URLDecoder.decode("(\u4ee5\u4e0b\u914d\u7f6e\u4e3aPM\u73af\u5883\uff1a\u5165\u7f51\u6d4b\u8bd5\u73af\u5883\u7528\uff0c\u751f\u4ea7\u73af\u5883\u914d\u7f6e\u89c1\u6587\u6863\u8bf4\u660e)")+"\n");
		
		sb.append(URLDecoder.decode("\u7b7e\u540d\u8bc1\u4e66\u8def\u5f84")+"\n");
		sb.append(URLDecoder.decode("\u7b7e\u540d\u8bc1\u4e66\u5bc6\u7801")+"\n");
		sb.append(URLDecoder.decode("\u7b7e\u540d\u8bc1\u4e66\u7c7b\u578b")+"\n\n");


		sb.append(URLDecoder.decode("\u9a8c\u7b7e\u8bc1\u4e66\u914d\u7f6e")+"\n");
		sb.append(URLDecoder.decode("\u9a8c\u8bc1\u7b7e\u540d\u8bc1\u4e66\u76ee\u5f55")+"\n");

		System.out.println(sb.toString());
		
	}
	
}
