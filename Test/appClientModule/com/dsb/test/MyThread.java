package com.dsb.test;

import java.util.concurrent.TimeUnit;

public class MyThread implements Runnable {

	@Override
	public void run() {
		
		synchronized(this){
			System.out.println(Thread.currentThread().getName());
		}
		
		System.out.println("开始休眠");
		try {
			Thread.sleep(10000);
			//TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"睡10s");

	}

	public static void main(String[] args) {
		MyThread mt=new MyThread();
//		Thread t1=new Thread(mt,"t1");
//		t1.start();
		Thread t1=new Thread(mt,"t1");
		Thread t2=new Thread(mt,"t2");
		Thread t3=new Thread(mt,"t3");
		Thread t4=new Thread(mt,"t4");
		Thread t5=new Thread(mt,"t5");
		Thread t6=new Thread(mt,"t6");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}

}
