package com.dsb.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
	
	static Map<String, String> map = new HashMap<String, String>();
	
	public static void main(String[] args) {
		/*if (map.containsKey(1000+"")) {
			System.out.println("hahaha");
			System.out.println(map.get(1000+""));
		}*/
//		Map<Long, String> m1 = new HashMap<Long, String>();
//		m1.put(Long.valueOf(-1), "asas");
//		System.out.println(m1.get(Long.valueOf(-1)));
		
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(map.containsKey("store_order"));
	}
	
	static{
		map.put(1000+"", "hehehehe");
	}
}
