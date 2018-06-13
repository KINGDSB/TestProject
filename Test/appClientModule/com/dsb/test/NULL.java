package com.dsb.test;

public class NULL {
	
	public static int I = 1;
	
	public static void hehe(){
		System.out.println("hehehehe");
	}
	
	public static void main(String[] args) {
		((NULL)null).hehe();
		((TestNull)null).haha();
		((OtherTest)null).add();
		System.out.println(((NULL)null).I);
		
		NULL nall = null;
		nall.hehe();
		System.out.println(nall.I);
	}
}

class TestNull{
	
	public static void haha(){
		System.out.println("hahahaha");
	}
}
