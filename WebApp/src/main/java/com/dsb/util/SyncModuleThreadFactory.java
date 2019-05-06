package com.dsb.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncModuleThreadFactory implements ThreadFactory {

	private final ThreadGroup group;
	private final ThreadGroup parentGroup;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	public SyncModuleThreadFactory(String poolName) {
		SecurityManager s = System.getSecurityManager();
		parentGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		group = new ThreadGroup(parentGroup, "SyncModule");

		StringBuilder sb = new StringBuilder(poolName);
		sb.append("Pool-thread-");
		namePrefix = sb.toString();
	}

	public Thread newThread(Runnable r) {
		StringBuilder threadName = new StringBuilder(namePrefix);
		threadName.append(threadNumber.getAndIncrement());

		Thread t = new Thread(group, r, threadName.toString(), 0);
		if (t.isDaemon())
			t.setDaemon(false);
		if (t.getPriority() != Thread.NORM_PRIORITY)
			t.setPriority(Thread.NORM_PRIORITY);
		return t;
	}
}

