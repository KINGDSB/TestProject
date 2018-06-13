package com.dsb.elasticsearchAndKafka;
//package elasticsearchAndKafka;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//
//import org.apache.avro.Schema;
//import org.apache.avro.generic.GenericDatumReader;
//import org.apache.avro.generic.GenericDatumWriter;
//import org.apache.avro.generic.GenericRecord;
//import org.apache.avro.io.DatumReader;
//import org.apache.avro.io.Decoder;
//import org.apache.avro.io.DecoderFactory;
//import org.apache.avro.io.EncoderFactory;
//import org.apache.avro.io.JsonEncoder;
//import org.elasticsearch.action.bulk.BulkItemResponse;
//import org.elasticsearch.action.bulk.BulkRequestBuilder;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.client.Client;
//
//import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
//
//public class EsProducer {
//    SimpleDateFormat _format = new SimpleDateFormat("yyyyMMdd");
//    long starttime = System.currentTimeMillis();
//
//    public void WriteToEs(List<KafkaStream<byte[], byte[]>> streams,  ExecutorService executor) {
//        
//
//        
//        for (final KafkaStream<byte[], byte[]> kafkaStream : streams) {
//            executor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    Client client = Utils.getClient();
//                    BulkRequestBuilder bulk = client.prepareBulk();
//                    String typeName = "type-warning";
//                    Schema _schema = null;
//                    String schemaName = "websense.avsc";
//                    ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
//                    while(it.hasNext()){
//                        byte[] message = it.next().message();
//                        String indexName = "warning-message-";
//                        try {
//                            String schemaContesnt =Utils.readFileContent(schemaName);
//                            _schema = new Schema.Parser().parse(schemaContesnt);
//                            DatumReader<GenericRecord> reader1 = new GenericDatumReader<GenericRecord>(_schema);  
//                            Decoder decoder1 = DecoderFactory.get().binaryDecoder(message, null);
//                            GenericRecord result = (GenericRecord) reader1.read(null, decoder1);
//                            GenericDatumWriter<Object>  w = new GenericDatumWriter<Object>(_schema);
//                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                            JsonEncoder    jsonEncoder = EncoderFactory.get().jsonEncoder(_schema, outputStream);
//                            w.write(result, jsonEncoder);
//                            jsonEncoder.flush();
//                            
//                            String timestamp = _format.format(new Date(Long.parseLong(
//                                    result.get("time_received").toString())));
//                            indexName = indexName +timestamp;
//                            IndexRequest index = new IndexRequest();
//                            index.index(indexName);
//                            index.type(typeName);
//                            index.source(new String(outputStream.toByteArray()));
//                            bulk.add(index);
//                            if (bulk.numberOfActions() >= 20000) {
//                                BulkResponse response = bulk.execute().actionGet();
//                                long l1 = 0L;
//                                //long currentTime = System.currentTimeMillis();
//                                //if(currentTime>=starttime+120*1000){
//                                //    System.out.println("stop........");　　　　　　定时停止任务代码
//                                //    KafkaWarningAction.shutdown();
//                                //    break;
//                                //}
//                                if (response.hasFailures()) {
//                                    BulkItemResponse[] arrayOfBulkItemResponse1 = response.getItems();
//                                    for (BulkItemResponse localBulkItemResponse : arrayOfBulkItemResponse1) {
//                                        if (localBulkItemResponse.isFailed()) {
//                                            l1 += 1L;
//                                            System.out.println(
//                                                    "bulk failure message " + localBulkItemResponse.getFailureMessage());
//                                        }
//                                    }
//                                }
//                                System.out.println("Inner  Failure  of article number is " + l1);
//                                bulk = client.prepareBulk();
//                            }
//                            
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (bulk.numberOfActions() > 0) {
//                        BulkResponse response = bulk.execute().actionGet();
//                        long l1 = 0L;
//                        if (response.hasFailures()) {
//                            BulkItemResponse[] arrayOfBulkItemResponse1 = response.getItems();
//                            for (BulkItemResponse localBulkItemResponse : arrayOfBulkItemResponse1) {
//                                if (localBulkItemResponse.isFailed()) {
//                                    l1 += 1L;
//                                    System.out.println(
//                                            "bulk failure message " + localBulkItemResponse.getFailureMessage());
//                                }
//                            }
//                        }
//                        System.out.println("Failure of article number is " + l1);
//                    }
//                }
//            
//            });
//        }
//    }
//    
//
//}