package com.dsb.util;

import java.util.Arrays;

public class ClassUtils {
	
	// 输出 Class 定义的属性名
	public static void outInfo(Class clazz){
		Arrays.stream(clazz.getDeclaredFields()).forEach(field -> System.out.println(field.getName()));
	}
	
	public static <T> void outInfo(T t){
		
	}

}
