package com.dsb.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一群人围成一圈从123报数，如果报到3就退出该圈中，直到最后一个人留下来！ 约瑟夫的游戏
 */
public class GetSort {

	public static void main(String[] args) {
		
		method2();
		
	}
	
	
	public static void method1(){
		// 声明值一个扫描器的变量
		Scanner input = new Scanner(System.in);
		System.out.println("请输入参数游戏的人数：");
		String str = input.next();
		// 判断是不是数字
		String regx = "\\d+";
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(str);
		int num = 0;
		if (m.matches()) {
			// 如果是数字就转换为数字
			num = Integer.parseInt(str);
		} else {
			// 如果不是数字终止
			System.out.println("输入的不是数字：结果为0");
			return;
		}
		// 为每个人编号
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			// 编号从一开始
			list.add(i + 1);
		}
		
		// 定义游戏要退出的变量
		int cursor = 0;
		// 开始游戏只到最后一人是结束
		while (list.size() > 1) {
			for (int i = 0; i < list.size(); i++) {
				// 游戏开始游标+1
				cursor++;
				if (cursor % 3 == 0) {
					list.remove(i);
					// 游标重新开始并归零
					cursor = 0;
					// 人数-1
					i--;
				}

			}
		}
		System.out.println("集合的大小：" + list.size());
		System.out.println("最后的人编号是:" + list.get(0));
	}
	
	public static void method2(){
		// 声明值一个扫描器的变量
		Scanner input = new Scanner(System.in);
		System.out.println("请输入参数游戏的人数：");
		String str = input.next();
		// 判断是不是数字
		String regx = "\\d+";
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(str);
		int num = 0;
		if (m.matches()) {
			// 如果是数字就转换为数字
			num = Integer.parseInt(str);
		} else {
			// 如果不是数字终止
			System.out.println("输入的不是数字：结果为0");
			return;
		}
		// 为每个人编号
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			// 编号从一开始
			list.add(i + 1);
		}
		
		// 定义游戏要退出的变量
		int cursor = 0;
		
		while (list.size() > 0) {
			Iterator<Integer> iterator = list.iterator();
			for (; iterator.hasNext();) {
				Integer tmp = (Integer) iterator.next();
				if (++cursor == 3) {
					System.out.println(tmp+"退出");
					iterator.remove();
					
					cursor=0;
				}
			}
		}
		
	}
	
}
