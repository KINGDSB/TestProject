package com.dsb.test;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.scheduling.annotation.Scheduled;

public class TaskTest implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(new Date() + "DSB最帅！！！");

	}

	public static void main(String[] args) {
		try {
			JobDetailImpl detail = new JobDetailImpl();
			detail.setName("job1");
			detail.setGroup("group1");
			detail.setJobClass(TaskTest.class);
			CronTrigger cronTrigger = new CronTriggerImpl("job1", "group1", "0/1 * * * * ?");
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler;
			try {
				scheduler = factory.getScheduler();
				try {
					scheduler.scheduleJob(detail, cronTrigger);
					scheduler.start();
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 定时任务-任务自动逾期
	 * 执行规则-每分钟执行一次
	 * 
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void taskOverdue() {

		Date currentDate = new Date();
		System.out.println("任务开始时间：" + currentDate);
		
	}
}
