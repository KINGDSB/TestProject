package com.dsb.test;

import java.util.HashMap;
import java.util.Map;

import com.dsb.tools.HttpUtil;

public class TestHttpUtil {

	public static void main(String[] args) throws Exception {
		
//		HttpUtil httpUtil = new HttpUtil();
//		Map<String, String> pairs = new HashMap<String, String>();
//		pairs.put("secretKey", "123456");
//		pairs.put("receiveUserIds", "1455,1457");
//		pairs.put("redName", "投资红包31");
//		pairs.put("periodType", "2");
//		pairs.put("periodType_day", "3");
//		pairs.put("redType", "4");
//		pairs.put("redType_2", "4");
//		pairs.put("tenderMoney", "100");
//		pairs.put("tenderMoneyRed", "5");
//		pairs.put("continueDayRed", "100");
//
////		String returnMsg = httpUtil.post("127.0.0.1", 10001, "/east/redenvelopes/sendRedEnv.do", pairs, "utf-8");
//		String returnMsg = httpUtil.post("118.178.89.125", 8081, "/redenvelopes/sendRedEnv.do", pairs, "utf-8");
//		System.out.println(returnMsg);
		
		HttpUtil httpUtil = new HttpUtil();

		Map<String, String> pairs = new HashMap<String, String>();
		pairs.put("category.id", "2");
		pairs.put("category.categoryName", "namesss");
		pairs.put("category.onIconUrl", "http://1.jpg");
		pairs.put("category.offIconUrl", "http://2.jpg");
		String returnMsg = httpUtil.post("127.0.0.1", 8081, "/a/category/saveCategory", pairs, "utf-8");
		System.out.println(returnMsg);
		
	}
	
}
