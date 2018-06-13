package com.dsb.test;

public class TestStaticMethod {
	public static void methodA(){
		System.out.println("methodA");
	}
	
	public void methodB(){
		System.out.println("methodB");
	}
	
	public static void main(String[] args) {
		((TestStaticMethod)null).methodA();
		((TestStaticMethod)null).methodB();
	}
}
