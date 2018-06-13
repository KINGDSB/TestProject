package com.dsb.spiderdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 功能概要：自定义爬虫工作同步队列，使用ArrayList实现
 *
 * @author hwz
 */
public class SpiderQueue {

	/** 存储器 */
	private List<SpiderUrl> queue;

	public SpiderQueue(int size) {
		queue = new ArrayList<SpiderUrl>(size);
	}

	public synchronized void add(SpiderUrl spiderUrl) {
		queue.add(spiderUrl);
	}

	public synchronized SpiderUrl poll() {
		if (queue.isEmpty()) {
			return null;
		}
		// 控制台打印结果，方便查看
		SpiderUrl spiderUrl = queue.remove(0);
		System.out.println("SpiderQueue,poll,SpiderUrl=" + spiderUrl.toString() + ",remain size=" + queue.size());
		return spiderUrl;
	}

	public synchronized SpiderUrl peek() {
		if (queue.isEmpty()) {
			return null;
		}
		return queue.get(0);
	}

	public synchronized boolean isExsit(SpiderUrl spiderUrl) {
		return queue.contains(spiderUrl);
	}

	public synchronized int size() {
		return queue.size();
	}

	public void printAll() {
		System.out.println("Enter printAll.");
		for (SpiderUrl spiderUrl : queue) {
			System.out.println(spiderUrl);
		}
	}
}