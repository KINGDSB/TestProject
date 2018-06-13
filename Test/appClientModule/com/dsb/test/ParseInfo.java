package com.dsb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParseInfo {

	public static void main(String[] args) {
		List<Info> list = new ArrayList<Info>();

		Info info1 = new Info(1, "房间1", 0, 1, 0, 0);
		Info info2 = new Info(2, "柜子11", 1, 2, 1, 0);
		Info info3 = new Info(3, "抽屉111", 2, 3, 2, 1);
		Info info4 = new Info(4, "抽屉112", 2, 4, 2, 1);
		Info info5 = new Info(5, "柜子12", 1, 5, 1, 0);
		Info info6 = new Info(6, "抽屉121", 5, 6, 2, 1);
		Info info7 = new Info(7, "房间2", 0, 7, 0, 0);
		Info info8 = new Info(8, "柜子21", 7, 8, 1, 1);
		Info info9 = new Info(9, "柜子22", 7, 9, 1, 1);
		Info info10 = new Info(10, "房间3", 0, 10, 0, 1);

		list.add(info1);
		list.add(info2);
		list.add(info3);
		list.add(info4);
		list.add(info5);
		list.add(info6);
		list.add(info7);
		list.add(info8);
		list.add(info9);
		list.add(info10);
		
		System.out.println("id\tname\tparentId\torderNo\tlevelL\tisLeaf");
		for (Info info : list) {
			System.out.println(info.getId()+"\t"+info.getName()+"\t"+info.getParentId()+"\t\t"+info.getOrderNo()+"\t"+info.getLevelL()+"\t"+info.getIsLeaf());
		}
		
		// 解析
		
		// 房间
		Map<Integer, Map> roomMap = new HashMap<Integer, Map>();
		// 柜子
		Map<Integer, Map> lockerMap = new HashMap<Integer, Map>();
		// 抽屉
		Map<Integer, Map> drawerMap = new HashMap<Integer, Map>();
		
		for (Info info : list) {
			
			Map map = new HashMap();
			map.put("id", info.getId());
			map.put("name", info.getName());
			map.put("parentId", info.getParentId());
			map.put("isLeaf", info.getIsLeaf());
			
			if (info.getLevelL() == 0) {
				
				roomMap.put(info.getId(), map);
				
			}else if (info.getLevelL() == 1) {
				
				lockerMap.put(info.getId(), map);
				
			}else if (info.getLevelL() == 2) {
				
				drawerMap.put(info.getId(), map);
				
			}
		}
		
		
		for (Info info : list) {
			if (info.getLevelL() == 0) {
				
				Map map = new HashMap();
				map.put("id", info.getId());
				map.put("name", info.getName());
				
				roomMap.put(info.getId(), map);
			}
		}

		for (Info info : list) {
			if (info.getLevelL() == 1) {
				
				
			}
		}
		
	}
	
}
