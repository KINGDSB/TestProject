package com.dsb.test;

class Base {
	public static String baseStaticField = "父类--静态变量";
	public String baseInstanceField = "父类--实例变量";

	static {
		System.out.println("1: " + baseStaticField);
		System.out.println("2: " + "父类--静态初始化块");
	}

	{
		System.out.println("3: " + baseInstanceField);
		System.out.println("4: " + "父类--实例初始化块");
	}

	public Base() {
		System.out.println("5: " + "父类--构造器");
	}
}

class SubClass extends Base {
	public static String subStaticField = "子类--静态变量";
	public String subInstanceField = "子类--实例变量";

	static {
		System.out.println("6: " + subStaticField);
		System.out.println("7: " + "子类--静态初始化块");
	}

	{
		System.out.println("8: " + subInstanceField);
		System.out.println("9: " + "子类--实例初始化块");
	}

	public SubClass() {
		System.out.println("10: " + "子类--构造器");
	}
}

public class Test02 {
	static {
		System.out.println("11: " + "Java 每日一题");
	}

	public static void main(String[] args) {
		new SubClass();
	}
}
