package com.dsb.mq;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class MQInfo {
	
	private MQInfo() {}

	private static volatile Queue<String> queue = new LinkedBlockingDeque<String>();
	
	public static synchronized boolean sendMsg(String msg){
		return queue.offer(msg);
	}
	
	public static synchronized String popMsg(){
		return queue.peek();
	}
	
}
