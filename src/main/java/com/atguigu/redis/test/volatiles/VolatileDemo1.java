package com.atguigu.redis.test.volatiles;

import java.util.concurrent.TimeUnit;

public class VolatileDemo1 {
    public static void main(String[] args) {
        //资源类
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.IncrNumber();

            System.out.println(Thread.currentThread().getName() + " update number value:" + myData.number);
        }, "A").start();

        while (myData.number == 0) {
            //main线程一直在这里循环，直到number值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "mission is over,main get number value:" + myData.number);
    }
}

class MyData {
    volatile int number = 0;

    public void IncrNumber() {
        this.number = 10;
    }
}
