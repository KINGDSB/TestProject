package com.dsb.test;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {
    
    public static void oom1() {
        List<List<Integer>> cache = new ArrayList<List<Integer>>();

        try {
            while (true) {
                List<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j < 100000; j++) {
                    list.add(j);
                }

                List<Integer> sublist = list.subList(0, 1);
                cache.add(sublist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("cache size = " + cache.size());
        }
    }

	public static void main(String[] args) {
	    oom1();
	}
}
