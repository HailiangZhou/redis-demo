package com.atguigu.redis.test;

import java.util.concurrent.atomic.AtomicInteger;

public class atomistic {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        int i = atomicInteger.incrementAndGet();
        System.out.println(i);
    }
}
