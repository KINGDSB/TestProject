package com.dsb.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestJson {
	public static void main(String args[]) throws Exception {
		
//		String json = "{\"#\":[{\"key\":\"value\"}]}";
//		Map map = JsonUtil.getMapFromJsObject(json);
//		System.out.println(map);
		
		JSONArray jsonArray = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("2", "赵丽萍");
		jsonArray.add(obj1);

		JSONObject obj2 = new JSONObject();
		obj2.put("1", "王平q");
		JSONObject obj22 = new JSONObject();
		obj22.put("asas", "asas");
		obj2.put("obj22", obj22);
		jsonArray.add(obj2);
		
		System.out.println(jsonArray);
		
		
	}
}