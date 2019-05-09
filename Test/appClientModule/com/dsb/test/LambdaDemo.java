package com.dsb.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author dsb
 * @ClassName: LambdaDemo 
 * @Description: LambdaDemo 
 * @date 2017-11-01 17:49:51 
 *
 */
public class LambdaDemo {

	public static void main(String[] args) {
		
//		// lambda test
//		List<String> list = Arrays.asList(new String[] { " ", "aaa", "cccd", "bv", "null" });
//		list.forEach(n -> System.out.println(n));
//		list.forEach(n -> {n+= "ccc"; System.out.println(n);});
//		// 按字符串长度升序
//		list.sort((str1, str2) -> str1.length() - str2.length());
//		list.forEach(System.out::println);
//
//		System.out.println("*************");
//
//		// 按字符串长度降序
//		list.sort((str1, str2) -> str2.length() - str1.length());
//		list.forEach(System.out::println);
//
//		System.out.println("*************");
//
//		filter(list, (str) -> str.length() > 2);
//
//		System.out.println("*************");
//
//		list.forEach(System.out::println);
//
//		System.out.println("*************");
//
//		// 使用lambda表达式为每个订单加上12%的税
//		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//		costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).forEach(System.out::println);
//
//		System.out.println("*************");
//
//		// 创建一个字符串列表，每个字符串长度大于2
//		List<String> filtered = list.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
//		System.out.printf("Original List : %s, filtered list : %s %n", list, filtered);
//		System.out.printf("Original List : %s, filtered list : %s ", list, filtered);
//
//		System.out.printf("%n*************%n");
//		
//		// 用所有不同的数字创建一个正方形列表 并去重
//		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
//		List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//		System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
//
//		System.out.printf("%n*************%n");
//		
//		//获取数字的个数、最小值、最大值、总和以及平均值
//		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
//		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
//		System.out.println("Highest prime number in List : " + stats.getMax());
//		System.out.println("Lowest prime number in List : " + stats.getMin());
//		System.out.println("Sum of all prime numbers : " + stats.getSum());
//		System.out.println("Average of all prime numbers : " + stats.getAverage());
		
		

        List<Integer> couponIds = Arrays.asList(new Integer[]{3,4,5});
        List<Integer> old = new ArrayList<>();
        
        List<Info> list = new ArrayList<>();
        Info info1 = new Info();
        info1.setId(1);
        list.add(info1);
        Info info2 = new Info();
        info2.setId(2);
        list.add(info2);
        Info info3 = new Info();
        info3.setId(3);
        list.add(info3);
        
        List<Info> delete = list.stream().filter(tmp -> {
            old.add(tmp.getId());
            return !couponIds.contains(tmp.getId());
        }).collect(Collectors.toList());
        
        System.out.println(old);
        System.out.println(JSONObject.toJSONString(delete));
        System.out.println(couponIds.stream().filter(id -> !old.contains(id)).collect(Collectors.toList()));
	}

	
	/**
	 * 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @date 2017-11-01 17:52:51 
	 * @author dsb
	 * @param names
	 * @param condition
	 */
	public static void filter(List<String> names, Predicate<String> condition) {
		names.stream().filter((name) -> (condition.test(name))).forEach(System.out::println);
	}

}
