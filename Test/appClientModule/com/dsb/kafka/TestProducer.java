package com.dsb.kafka;

import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class TestProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
//		props.setProperty("metadata.broker.list", "10.XX.XX.XX:9092");
		props.setProperty("metadata.broker.list", "192.168.56.128:9092");
		props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");
		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config);
//		KeyedMessage<String, String> data = new KeyedMessage<String, String>("mykafka", "test-kafka");
//		KeyedMessage<String, String> data = new KeyedMessage<String, String>("test", "test-kafka");
		try {
			int i = 1;
			while (i < 100) {
				System.out.println("发送"+i);
//				producer.send(new KeyedMessage<String, String>("test", "test-kafka-data time:"+new Date()));
				producer.send(new KeyedMessage<String, String>("testTopic1", "测试kafka数据进程NNNN time:"+new Date()));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		producer.close();
	}
}