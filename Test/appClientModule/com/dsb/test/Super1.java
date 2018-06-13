package com.dsb.test;

public class Super1 {
	int a = 1;
	
	public Super1() {
		add();
	}

	public void add(){
		this.a++;
		System.out.println("Class.Add()\tsuper.a:"+a);
	}

	public static void main(String[] args) {
		Child1 c1 = new Child1();
		System.out.println("main\tc1.a:"+c1.a);
	}

}

class Child1 extends Super1{
	int a = 11;

	public Child1() {}
	
	public void add(){

		System.out.println("Child.Add()\tsuper.a:"+super.a);
		super.a++;
		System.out.println("Child.Add()\tsuper.a:"+super.a);
		
		System.out.println("Child.Add()\tchild.a:"+this.a);
		this.a++;
		System.out.println("Child.Add()\tchild.a:"+this.a);
	}
}

class OtherTest{
	
	public static void add(){
		System.out.println("other");
	}
}