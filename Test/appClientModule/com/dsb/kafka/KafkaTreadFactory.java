package com.dsb.kafka;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * kafka线程工厂
 * 
 * @author admin
 *
 */
public class KafkaTreadFactory implements ThreadFactory {

	private int counter;
	private String name;
	private List<String> stats;

	public KafkaTreadFactory(String name) {
		counter = 0;
		this.name = name;
		stats = new ArrayList<String>();
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, name + "_Thread_" + counter);
		counter++;
		stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
		return t;
	}

	public String getStatuts() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();
		while (it.hasNext()) {
			buffer.append(it.next());
			buffer.append("\n");
		}
		return buffer.toString();
	}

}

/**
 * 测试线程工厂消费kafka数据
 * 
 * @author admin
 *
 */
class TaskConsumer implements Runnable {

	/**
	 * 数据
	 */
	private KafkaStream<byte[], byte[]> kafkaStreamMap;
	
	/**
	 * 锁
	 */
	private CountDownLatch latch;
	
	
	private boolean isComplete = false;

	public TaskConsumer(KafkaStream<byte[], byte[]> kafkaStreamMap, CountDownLatch latch) {
		this.kafkaStreamMap = kafkaStreamMap;
		this.latch = latch;
	}

	@Override
	public void run() {

		System.out.println("*****************************\n"+Thread.currentThread().getName()+" is starting\n*****************************");

		// 处理分配给当前线程的信息
		System.out.println("[threadName]:" + "分配到的信息条数为:"+kafkaStreamMap.size());
		ConsumerIterator<byte[], byte[]> it = kafkaStreamMap.iterator();
		while (it.hasNext()) {
			// do something
			System.err.println("[threadName]:" + Thread.currentThread().getName());
			System.err.println("get data:" + new String(it.next().message()));
		}

		System.err.println("[threadName]:信息处理完毕!");
		isComplete = true;
		latch.countDown();
	}

	public KafkaStream<byte[], byte[]> getKafkaStreamMap() {
		return kafkaStreamMap;
	}

	public void setKafkaStreamMap(KafkaStream<byte[], byte[]> kafkaStreamMap) {
		this.kafkaStreamMap = kafkaStreamMap;
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
