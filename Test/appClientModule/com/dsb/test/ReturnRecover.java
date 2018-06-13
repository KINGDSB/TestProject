package com.dsb.test;

//思路：用return的相互覆盖就可以解决这个难题。
public class ReturnRecover {

	public static void main(String[] args) {
		// 调用下面的方法
//		int n = fun(2);
//		System.out.println("打印fun（）函数返回的n值=  " + n);
		
//		System.out.println("打印fun（）函数返回的n值=  " + fun(2));
		
		ObjA a = new ObjA();
		setObjB(a);
		System.out.println(a.toString());
	}

	public static int fun(int i) {
		try {
			// 发现异常
			int m = i / 0;
			// 异常直接被捕获，后面的不会执行。
			return i++;
		} catch (ArithmeticException e) {
			System.out.println("异常信息：" + e);
			System.out.println("catch 中的i = " + i);
			return i + 3; // 返回的是 2+3， 而不是finally中对i的赋值再来加上3，
							// finally中对i的操作，不会影响此时catch中的return i+3
		} finally {
			i++;
			i++;
			System.out.println("finally 执行  " + i);
			// return i+8; //如果这里没注释
			// 这里会返回12，而不会去返回catch中的 return i+3
		}
	}
	
	public static void setObjB(ObjA obja){
		int i = 0;
		try {
			// 发现异常
			int m = 2 / 0;
			return ;
		} catch (ArithmeticException e) {
			System.out.println("异常信息：" + e);
			obja.objb = new ObjB("catch");
			obja.i = i + 1;
			return ; // 返回的是 2+3， 而不是finally中对i的赋值再来加上3，
							// finally中对i的操作，不会影响此时catch中的return i+3
		} finally {
			obja.objb = new ObjB("finally");
			obja.i = i + 2;
		}
	}
}

class ObjA{
	ObjB objb;
	
	int i;

	@Override
	public String toString() {
		
		return objb.name+"\ni:"+i;
	}
}

class ObjB{
	
	String name;

	public ObjB(String name) {
		super();
		this.name = name;
	}
	
}
