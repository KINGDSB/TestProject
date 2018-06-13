package com.dsb.elasticsearchAndKafka;
//package elasticsearchAndKafka;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.InetAddress;
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//
//public class Utils {
//    
//        static TransportClient client = null;
//        
//        /*elasticSearch 1.5.0*/
////        static {
////            String clusterName = "SOCBigData";
////            String ip = "11.11.184.180";
////            Settings settings = ImmutableSettings.settingsBuilder().put("client.transport.sniff", true)
////                    .put("client.transport.nodes_sampler_interval", 60)
////                    .put("cluster.name",clusterName).build();
////            client = new TransportClient(settings);
////            client.addTransportAddress(new InetSocketTransportAddress(ip, 9300));
////            client.connectedNodes();
////        }
//        
//        /*elasticSearch 2.0.0*/
//        static {
//            //Config config = Config.getInstance();
////            String clusterName = config.getESClusterName();
////            String ip = config.getESIp();
//            String clusterName = "udb_soc";
//            String ip = "11.11.11.11";
//            Settings settings = Settings.settingsBuilder().put("client.transport.sniff", false)
//                    .put("client.transport.nodes_sampler_interval", "60s")
//                    .put("cluster.name",clusterName).build();
//            client = TransportClient.builder().settings(settings).build();
//            for (String host : ip.split(",")) {
//                try {
//                    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            client.connectedNodes();
//        }
//        
//        public final static Client getClient() {
//            return client;
//        }
//        
//        
//        
//    // 参数string为你的文件名
//        public static String readFileContent(String fileName) throws IOException {
//            String path = "E:\\workspace\\DataImportV2.1\\target";
//            File file = new File(path+"\\"+fileName);
//            BufferedReader bf = new BufferedReader(new FileReader(file));
//            String content = "";
//            StringBuilder sb = new StringBuilder();
//
//            while (content != null) {
//                content = bf.readLine();
//
//                if (content == null) {
//                    break;
//                }
//
//                sb.append(content.trim());
//            }
//            
//            bf.close();
//            return sb.toString();
//        }
//}