package com.dsb.mq;

import java.util.ArrayList;
import java.util.List;

public class MQConsumerMain {
	
	public static class MQConsumer extends Thread{
		
		private String Msg;

		public void run() {
			System.out.println("线程名:"+Thread.currentThread().getName()+"Msg:"+Msg);
			stop();
		}

		public MQConsumer(String msg) {
			Msg = msg;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		int threadCount = 4; //默认是4个进程
		
		try {
			
			List<MQConsumer> threadList = new ArrayList<MQConsumer>(threadCount);
			
			for (int i = 0; i < threadCount; i++) {
				
				String msg = MQInfo.popMsg();
				if (msg == null || "".equals(msg)) {
					break;
				}
				
				MQConsumer consumer = new MQConsumer(msg);
				threadList.add(consumer);
			}
			
			while(true){
				
				String msg = MQInfo.popMsg();
				if (msg == null || "".equals(msg)) {
					break;
				}
				
				for (MQConsumer mqConsumer : threadList) {
					
					if (!mqConsumer.isAlive()) {
						mqConsumer = new MQConsumer(msg);
						mqConsumer.start();
					}
					
				}
				
			}
			
		} catch (Exception e) {}

		
	}
	
	
}
