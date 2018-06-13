package com.dsb.test;

import java.util.Timer;
import java.util.TimerTask;

public class TestTask {
	public static void main(String[] args) {
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				// 执行的任务
//				System.out.println("Hello !!!:" + System.currentTimeMillis());
//			}
//		};
//		Timer timer = new Timer();
//		//延迟多少秒后开始执行
//		long delay = 5000;
//		// 间隔的时间(毫秒) (初步判断为 执行的任务结束之后开始算
//		long intevalPeriod = 1 * 1000;
//		System.out.println("Start !!!:" + System.currentTimeMillis());
//		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
		
		new Thread(() -> System.out.println("asas")).start();
		
	}
}
