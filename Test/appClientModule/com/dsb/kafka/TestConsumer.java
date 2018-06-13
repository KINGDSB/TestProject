package com.dsb.kafka;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class TestConsumer extends Thread {
	private final ConsumerConnector consumer;
	private final String topic;

	public static void main(String[] args) {
		// TestConsumer consumerThread = new TestConsumer("mykafka");
//		TestConsumer consumerThread = new TestConsumer("test_kafka_topic");
		TestConsumer consumerThread = new TestConsumer("testTopic");
		consumerThread.start();
	}

	public TestConsumer(String topic) {
		consumer = kafka.consumer.Consumer
				.createJavaConsumerConnector(createConsumerConfig());
		this.topic = topic;
	}

	private static ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();
//		props.put("zookeeper.connect","10.XX.XX.XX:2181,10.XX.XX.XX:2181,10.XX.XX.XX:2181");
		props.put("zookeeper.connect","192.168.56.128:2181");
		props.put("group.id", "1");
		props.put("zookeeper.session.timeout.ms", "10000");
		return new ConsumerConfig(props);
	}

	public void run() {
		Map<String, Integer> topickMap = new HashMap<String, Integer>();
		topickMap.put(topic, 1);
		Map<String, List<KafkaStream<byte[], byte[]>>> streamMap = consumer.createMessageStreams(topickMap);
		KafkaStream<byte[], byte[]> stream = streamMap.get(topic).get(0);
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		System.out.println("*********Results********");
		while (true) {
			if (it.hasNext()) {

				System.err.println("get data:" + new String(it.next().message()));
			}
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}