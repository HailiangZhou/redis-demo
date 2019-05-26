package com.atguigu.redis.test.volatiles;

public class VolatileDemo2 {
    public static void main(String[] args) {

        MyData2 myData2 = new MyData2();
        //开启20个线程各对number进行1000次++操作
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData2.incrNumber();
                }
            }).start();
        }

        //需要等待上面20个线程全部执行完再执行main线程看number最终结果是多少
        //yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；
        //设置>2 是看当前存活的线程是否>2，一个是main线程，一个是后台线程，如果>2，说明上面的20个线程还没有执行完
        while (Thread.activeCount() < 2) {
            Thread.yield();
        }

        System.out.println("finally number value:" + myData2.number);

    }
}

class MyData2 {
    volatile int number = 0;

    public  void incrNumber() {
        number++;
    }
}