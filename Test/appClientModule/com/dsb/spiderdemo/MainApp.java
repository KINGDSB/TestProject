package com.dsb.spiderdemo;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能概要：主程序
 *
 * @author hwz
 */
public class MainApp {

	private Integer corePoolSize = 10;

	private Integer maxPoolSize = 20;

	private ThreadPoolExecutor executor;

	/** 工作队列 */
	private SpiderQueue workQueue;

	public void start(String url) throws Exception {
		// 初始化线程池
		LinkedBlockingDeque<Runnable> executorQueue = new LinkedBlockingDeque<Runnable>(maxPoolSize);
		executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60L, TimeUnit.SECONDS, executorQueue);

		workQueue = new SpiderQueue(1024);
		SpiderUrl spiderUrl = new SpiderUrl(url, 0);
		try {
			workQueue.add(spiderUrl);
		} catch (Exception e) {
			System.out.println("insert url into workQueue error,url=" + url);
			e.printStackTrace();
		}

		// 提交第一个执行任务
		executor.submit(new SimpleSpider(workQueue, "thread-" + "main"));
		int i = 0;
		int idle = 0;
		while (true) {
			// 判断是否增加更多线程执行任务
			if (workQueue.size() > 20 && executor.getActiveCount() < maxPoolSize) {
				idle = 0;
				System.out.println("submit new thread,workQueue.size=" + workQueue.size()
						+ ",executorQueue.activeCount=" + executor.getActiveCount() + ",i=" + i);
				executor.submit(new SimpleSpider(workQueue, "thread-" + i++));
				Thread.sleep(500);
			} else if (workQueue.size() == 0) {
				idle++;
				System.out.println("main method, idle times=" + idle);

				// 主线程空闲20次，结束运行
				if (idle > 20) {
					System.out.println("main method, idle times=" + idle + ",end!");
					break;
				}
				Thread.sleep(1000);
			} else {
				Thread.sleep(2000);
			}
		}
		System.out.println(
				"End!,workQueue.size=" + workQueue.size() + ",executorQueue.activeCount=" + executor.getActiveCount()
						+ ",executorQueue.CompletedTaskCount" + executor.getCompletedTaskCount() + ",i=" + i);
		workQueue.printAll();
		executor.shutdown();
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {

		MainApp app = new MainApp();
		app.start("http://www.csdn.net/");
	}
}