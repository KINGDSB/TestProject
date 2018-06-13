package com.dsb.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestList {

	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		list.add("哈哈哈");
//		list.add("呵呵呵");
//		list.add("呼呼呼");
//		list.remove(1);
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		System.out.println("================");
//		System.out.println(list.toString());
//		System.out.println("================");
//		System.out.println(list);
//		System.out.println("================");
//		System.out.println(list.toString().substring(1, list.toString().length()-1));
//		
//		
//		List<Long> listLong = new ArrayList<Long>();
//		listLong.add(10001l);
//		listLong.add(10002l);
//		listLong.add(10003l);
//
//		System.out.println("================");
//		System.out.println(listLong.toString().substring(1, listLong.toString().length()-1));
		
		
//		Set<Long> set = new HashSet<Long>();
//		set.add(110l);
//		set.add(120l);
//		set.add(130l);
//		set.add(120l);
//		set.add(140l);
//		
//		List<Long> consultIdList = new ArrayList<Long>(set);
//		
//		for (Long se : set) {
//			System.out.println(se);
//		}
//		System.out.println("***************");
//		
//		for (Long consultId : consultIdList) {
//			System.out.println(consultId);
//		}
		
		String[] arr = new String[5];
		arr[1] = "1";
		arr[0] = "0";
		
//		List<String> list1 = Arrays.asList(arr);
//		list1.remove(null); // 报错 unsupport
		
//		List<String> list2 = new ArrayList<String>(Arrays.asList(arr));
//		list2.remove(null);	// 只清空第一个null
//		list2.removeAll(Collections.singletonList(null)); // 可行 效率未测
//		
//		String[] arr2 = new String[2];
//		System.arraycopy(arr, 0, arr2, 0, 2);
//		List<String> list3 = Arrays.asList(arr2); // 如果可知数组实际长度 并且下标是有序无间断的 目测效率高于list2
		
//		List<String> list = Arrays.asList(new String[]{" ","aaa","cccd","bv"});
//		String listStr = list.toString();
//		System.out.println(list);
//		String[] arr2x = listStr.split(",");
//		System.out.println(arr2x);
	}
	
	public void getNum(Long num){
		num+=1;
		System.out.println(num);
	}
	
}
