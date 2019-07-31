package com.dsb.test;

import java.util.concurrent.atomic.AtomicInteger;

class UnAtomic {
    volatile int flag = 0;
    AtomicInteger aiFlag = new AtomicInteger();

    public void AtomicFlag() {
        aiFlag.getAndIncrement();
    }
}

public class VolatileTest_UnAtomic {
    public static void main(String[] args) {
        UnAtomic ua = new UnAtomic();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    ua.AtomicFlag();
                }
            }, String.valueOf(i)).start();
            while (Thread.activeCount() > 2) {
                Thread.yield();
            }
            System.out.println("UnAtomicFlag sum : " + ua.flag);
            System.out.println("AtomicFlag sum : " + ua.aiFlag);
        }
    }
}