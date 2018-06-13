package com.dsb.threadfactory;

public class Test {

	public static void main(String[] args) {

		MyTreadFactory factory = new MyTreadFactory("MyTreadFactory");
		Task task = new Task();
		Thread thread;
		System.out.println("Starting the Threads ");
		for (int i = 0; i < 10; i++) {
			thread = factory.newThread(task);
			thread.start();
		}
		System.out.println("Factory stats:");
		System.out.printf("%s\n", factory.getStatuts());
	}
}
