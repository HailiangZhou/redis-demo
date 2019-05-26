package com.atguigu.redis.test.lock;

import com.atguigu.redis.test.enumerate.CountryEnum;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        Integer integer = new Integer(1);
        for (int i = 1; i <= 6; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国，被灭");
                countDownLatch.countDown();
            }, CountryEnum.foreachCountryEnum(i).getRetMessage()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "秦国，一统天下");
    }
}
