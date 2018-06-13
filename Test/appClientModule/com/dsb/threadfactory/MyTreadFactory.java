package com.dsb.threadfactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;

public class MyTreadFactory implements ThreadFactory{

	private int counter;
	private String name;
	private List<String> stats;
	public MyTreadFactory(String name){
		counter = 0;
		this.name = name;
		stats = new ArrayList<String>();
	}
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r,name+"_Thread_"+counter);
		counter++;
		stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(),t.getName(),new Date()));
		return t;
	}

	public String getStatuts(){
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();
		while(it.hasNext()){
			buffer.append(it.next());
			buffer.append("\n");
		}
		return buffer.toString();
	}

}

class Task implements Runnable{  
	  
    @Override  
    public void run() {  
  
        try {
        	// 随机休眠多少秒
        	int i = RandomUtils.nextInt() * 100;
        	System.out.println(Thread.currentThread().getName()+" "+i);
            TimeUnit.SECONDS.sleep(i);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}