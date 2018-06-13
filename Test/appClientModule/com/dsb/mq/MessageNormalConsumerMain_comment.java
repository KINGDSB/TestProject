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
public class MessageNormalConsumerMain_comment {
//	private static final Logger log = Logger.getLogger(MessageNormalConsumerMain_comment.class);
//	private final String topic =  KafkaMsgName.MSG_TOPIC.getCode();  //主题
//
//	public void postConsumer(int threadCount) throws Exception{
//		List<PostThread> threadList = new ArrayList<PostThread>();
//		int threadNumber = 0;
//		
//		// 包装了工具类，又没代码、什么注释都没有，叫人怎么用
//		List<MsgIterator> iters = MsgUtil.consume(topic, threadCount);
//		// MsgUtil.consume假如实现改了呢，返回的列表长度是不可控的，我们的线程数也是不可控的吗？
//		for (MsgIterator iter : iters) {
//			threadNumber++;
//			threadList.add(new PostThread(iter, threadNumber));
//		}
//		
//		for (int i = 0; i < threadList.size(); i++) {
//			threadList.get(i).start();
//		}
//		for (int i = 0; i < threadList.size(); i++) {
//			// 方法名写的是shutdown，结果里面是await，误导人家
//			// 等待的是调用此方法当前线程，并不是你新建的线程等待，现在只是把等待条件存放在各个线中，要这么多等待条件干嘛
//			threadList.get(i).shutdown();
//		}
//		
//		// 为什么要关闭，不是一直监听等待kafka消息吗
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
//			// 每次run都要设置线程名吗，不能用线程工厂吗
//			Thread.currentThread().setName("EF--Msg--" + threadNumber);
//
//			while (iter.hasNext()) {
//				String sMsg = "";
//				try {
//					// 转成json，再转base64，为什么要转来转去
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
//			// 这方法永远没执行，因为前面的while是阻塞的
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
//					// 为什么不修正为合理值，iThreadNum=50000，程序挂了没？
//				}else{
//					threadCount = iThreadNum;
//				}
//			}
//		} catch (Exception e) {
//			// 为什么不输出异常，有问题永远找不到
//		}
//
//		MessageNormalConsumerMain_comment simpleHLConsumer = new MessageNormalConsumerMain_comment();
//		simpleHLConsumer.postConsumer(threadCount);
//	}

	

}
