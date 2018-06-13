package com.dsb.test;

import java.util.ArrayList;
import java.util.List;

public class Test05 {
	
//	public static void main(String[] args) {
//		
//		int n = 10;
//		int m = 2;
//		
//		List<Integer> list = new ArrayList<Integer>();
//		for (int i = 1; i <= n; i++) {
//			list.add(i);
//		}
//		
//		while (list.size() > 0) {
//			System.out.println(list.remove(m));;
//		}
//		
//	}

	// 
	public Test05(){
		
	}
	public void Test05(){
		
	}
	
	
	// 静态代码块 → 静态成员 → 实例成员 → 实例代码块 → 构造方法
	public static void main(String[] args) {
		
		System.out.println("hello world");
		
		System.out.println(Tree.staticColor); // 6
		Tree tree = new Tree();
		System.out.println(tree.instanceColor); // 7
	}
	
}

class Tree {
	static {
		System.out.println("静态块。"); // 1
		staticColor = "Red";
	}

	public static String staticColor = getStaticColor();

	public String instanceColor = getInstanceColor();

	{
		System.out.println("实例块。"); // 2
		instanceColor = "Brown";
	}

	public Tree() {
		System.out.println("构造方法"); // 3
	}

	public static String getStaticColor() {
		System.out.println("静态方法。"); // 4
		return "Green";
	}

	public String getInstanceColor() {
		System.out.println("实例方法。"); // 5
		return "Yellow";
	}
	
	
}
