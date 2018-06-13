package com.dsb.mq;

import java.util.concurrent.TimeUnit;

public class MQSender {
	
	public static void main(String[] args) {
		try {
			
			for (int i = 1; i < 1001; i++) {
				TimeUnit.SECONDS.sleep(3);
				
				new Thread(){
					@Override
					public void run() {
						MQInfo.sendMsg("现在时间:"+System.currentTimeMillis()+"线程名称:"+Thread.currentThread().getName());
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
