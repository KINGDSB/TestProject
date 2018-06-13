package com.dsb.elasticsearchAndKafka;
//package elasticsearchAndKafka;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.I0Itec.zkclient.ZkClient;
//
//import kafka.consumer.Consumer;
//import kafka.consumer.ConsumerConfig;
//import kafka.consumer.KafkaStream;
//import kafka.javaapi.consumer.ConsumerConnector;
//import kafka.utils.ZkUtils;
//
//public class KafkaWarningAction {
//       private static final String topic = "avro-log-bj-jingnei1-dev-proxy-websense";private static final String group_id = "kafkawarning01";
//      private static final Integer threads = 5;
//      private static ConsumerConnector consumer;
//      private static ExecutorService executor;
//
//      public static void main(String[] args) {//创建消费者消费数据
//          Properties props = new Properties();
//          props.put("zookeeper.connect", "host1:2181,host2:2181,host3:2181");
//          props.put("group.id", group_id);
//          props.put("auto.offset.reset", "smallest");
//          ConsumerConfig config = new ConsumerConfig(props);
//          consumer =Consumer.createJavaConsumerConnector(config);
//          Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//          topicCountMap.put(topic, threads);
//          Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
//          List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
//          executor = Executors.newFixedThreadPool(threads);//创建线程池
//            Runtime.getRuntime().addShutdownHook(new Thread() {
//                @Override
//                public void run() {
//                    shutdown();
//                }
//          });
//          //写入ES
//          
//          EsProducer esProducer = new EsProducer();
//          esProducer.WriteToEs(streams,executor);
//    }public static void shutdown(){
//        if(consumer!=null){
//            consumer.shutdown();
//            consumer = null;
//        }
//         if (executor != null){
//             executor.shutdown();
//             executor = null;
//         }
//               
//    }
//    
//}