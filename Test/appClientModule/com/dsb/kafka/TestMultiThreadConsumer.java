package com.dsb.kafka;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * 测试kafka多线程消费者
 * @author admin
 *
 */
public class TestMultiThreadConsumer {

	public static void main(String[] args) throws InterruptedException {

		// 订阅的topicName
		String topic = "kafkatopic6";

		// 线程总数
		int threadNum = 10;
		
		
		KafkaTreadFactory factory = new KafkaTreadFactory("kafka线程工厂");
		CountDownLatch latch = new CountDownLatch(threadNum);
		
		
		Map<String, Integer> topickMap = new HashMap<String, Integer>();
		topickMap.put(topic, threadNum);
		
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(ConsumerConfigSingleton.getConfig());
		
		Map<String, List<KafkaStream<byte[], byte[]>>> streamMap = consumer.createMessageStreams(topickMap);
		List<KafkaStream<byte[], byte[]>> listKafkaStream = streamMap.get(topic);
		
		System.out.println("Starting the Threads ");
		
		Thread thread = null;
		for (KafkaStream<byte[], byte[]> kafkaStream : listKafkaStream) {

			thread = factory.newThread(new TaskConsumer(kafkaStream , latch));
			thread.start();
		}

		System.out.println("当前时间:"+new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
		System.out.println("Factory stats:");
		System.out.printf("%s\n", factory.getStatuts());
		
		// 等待所有线程处理完毕
		latch.await();

		System.out.println("**********************************");
		System.out.println("所有线程处理完毕\n当前时间:"+new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));

	}

}


/**
 * 消费者配置文件的单例
 * @author admin
 *
 */
class ConsumerConfigSingleton {

	private static ConsumerConfig instance;
	
	static{
		Properties props = new Properties();
		props.put("zookeeper.connect","192.168.56.128:2181");
		props.put("group.id", "group1");
		props.put("zookeeper.session.timeout.ms", "400000");
		instance = new ConsumerConfig(props); 
	}
	
	private ConsumerConfigSingleton(){}
	
	/**
	 * 获取配置
	 *  (是否需要synchronized)
	 * @return
	 */
	public static ConsumerConfig getConfig(){ return instance; }
	
}