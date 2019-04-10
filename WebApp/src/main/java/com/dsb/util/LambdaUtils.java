package com.dsb.util;

import java.util.function.Function;

public class LambdaUtils {

    public static void main(String[] args) throws Exception {
        execute(a -> String.valueOf(a + 1), 10);
    }

    public static void execute(Function<Integer, String> func, int num) {
        System.out.println(func.apply(num));
    }
    

    public static String redisGet(String key, Function<String, String> func) {
        // return RedisUtils.get(key);
        return key;
    }
    
}
