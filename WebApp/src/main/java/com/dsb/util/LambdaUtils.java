package com.dsb.util;

import java.util.function.Function;

public class LambdaUtils {

    public static void main(String[] args) throws Exception {
        Integer i = 1;
        execute(a -> {
            System.out.println("execute 1" + a);
            String.valueOf(a + i);
            return String.valueOf(a + i);
        }, 1, 11);
    }

    public static void execute(Function<Integer, String> func, int num, int num2) {
        System.out.println("execute 01" + num);
        if (10 == num) {
            System.out.println(" execute execute");
            System.out.println(func.apply(num2));
        } else {
            System.out.println(" not execute execute");
        }
    }
    

    public static String redisGet(String key, Function<String, String> func) {
        // return RedisUtils.get(key);
        return key;
    }
    
}
