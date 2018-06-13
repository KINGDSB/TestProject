package com.dsb.mq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

//import org.apache.log4j.Logger;
//
//import com.ai.appframe2.service.ServiceFactory;
//import com.asiainfo.appframe.ext.flyingserver.org.apache.commons.lang.StringUtils;
//import com.easyfun.easyframe.msg.MsgIterator;
//import com.easyfun.easyframe.msg.MsgUtil;
//import com.szjk.common.inter.common.util.SysUtil;
//import com.szjk.common.kafka.kmessage.service.interfaces.IMessageDispatcherSV;
//import com.szjk.dhome.common.type.KafkaMsgName;
//import com.szjk.dhome.common.util.Base64Util;

/**
 * 消息服务器-- 通用消费者
 * @author DerekLee
 */
public class MessageNormalConsumerMain {
//	private static final Logger log = Logger.getLogger(MessageNormalConsumerMain.class);
//	private final String topic =  KafkaMsgName.MSG_TOPIC.getCode();  //主题
//
//	public void postConsumer(int threadCount) throws Exception{
//		List<PostThread> threadList = new ArrayList<PostThread>();
//		int threadNumber = 0;
//		
//		List<MsgIterator> iters = MsgUtil.consume(topic, threadCount);
//		for (MsgIterator iter : iters) {
//			threadNumber++;
//			threadList.add(new PostThread(iter, threadNumber));
//		}
//		
//		for (int i = 0; i < threadList.size(); i++) {
//			threadList.get(i).start();
//		}
//		for (int i = 0; i < threadList.size(); i++) {
//			threadList.get(i).shutdown();
//		}
//		
//		MsgUtil.shutdownConsummer();
//	}
//	
//	public static class PostThread extends Thread {
//		private int threadNumber;
//		private CountDownLatch shutdownLatch = new CountDownLatch(1);
//		MsgIterator iter;
//
//		public PostThread(MsgIterator iter, int threadNumber) {
//			this.iter = iter;
//			this.threadNumber = threadNumber;
//		}
//		
//		public void shutdown() throws Exception {
//			shutdownLatch.await();
//		}
//
//		public void run() {
//			Thread.currentThread().setName("EF--Msg--" + threadNumber);
//
//			while (iter.hasNext()) {
//				String sMsg = "";
//				try {
////					sPost = new String(iter.next().getBytes(),"UTF-8");
//					sMsg = new String(iter.next().getBytes());
//					sMsg =Base64Util.decode(sMsg);
//					log.info("Message from thread :: [" + threadNumber + "], 消息报文--> " + sMsg);
//			
//					IMessageDispatcherSV iSV = (IMessageDispatcherSV) ServiceFactory.getService(IMessageDispatcherSV.class);
//					iSV.dealNormalMessageByMap(sMsg);
//				} catch (Exception e) {
//					log.error("[kafka]帖子处理消息异常:"+e.getMessage());
//					e.printStackTrace();
//				}
//			}
//
//			shutdownLatch.countDown();
//		}
//	}
//
//	public static void main(String[] args) throws Exception{
//		int threadCount = 4; //默认是4个进程
//		try {
//			String sThreadNum = SysUtil.getParaDetailValueBySymbol("DOCTOR", "KAFKA", "KAFKA_THREAD_NUM", null);
//			if(StringUtils.isNotBlank(sThreadNum)&& StringUtils.isNumeric(sThreadNum)){
//				int iThreadNum = Integer.parseInt(sThreadNum);
//				if(iThreadNum>50){
//					log.error("进程数不能超过50");
//				}else{
//					threadCount = iThreadNum;
//				}
//			}
//		} catch (Exception e) {
//		}
//
//		MessageNormalConsumerMain simpleHLConsumer = new MessageNormalConsumerMain();
//		simpleHLConsumer.postConsumer(threadCount);
//	}

}
