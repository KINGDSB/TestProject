package com.dsb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsb.util.EhCacheService;

////指定bean注入的配置文件  
//@ContextConfiguration(locations = { "classpath:config/spring-mvc.xml" })
//// 使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
//@RunWith(SpringJUnit4ClassRunner.class)
//public class EhCacheTestServiceTest {
//
//	@Autowired
//	private EhCacheService ehCacheService;
//
//	@Test
//	public void getTimestampTest() throws InterruptedException {
//		System.out.println("第一次调用：" + ehCacheService.getTimestamp("param"));
//		Thread.sleep(2000);
//		System.out.println("2秒之后调用：" + ehCacheService.getTimestamp("param"));
//		Thread.sleep(11000);
//		System.out.println("再过11秒之后调用：" + ehCacheService.getTimestamp("param"));
//	}
//}