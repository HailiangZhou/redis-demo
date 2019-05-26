package com.atguigu.redis.test.jvm;

import java.util.concurrent.TimeUnit;

public class HelloGC {
    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();// 返回java虚拟机的初始内存大小
        long maxMemory = Runtime.getRuntime().maxMemory();// 返回java虚拟机试图使用的最大内存大小
        System.out.println("-Xms=" + totalMemory / 1024 / 1024 + "MB");
        System.out.println("-Xmx=" + maxMemory / 1024 / 1024 + "MB");
        int j = 1;
        for (int i = 1; i < 10; i++) {


            new Thread(() -> {
                int x =1;
                System.out.println(x);

            }).start();
        }

        for (int i = 1; i < 10; i++) {



            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(j);

                }
            }).start();
        }

        System.out.println("main is over");
        System.out.println("===========");
        System.out.println("1111");
    }


    public void test1(){
        int i = 1;
        new Thread(()->{
            System.out.println(i);
        }).start();
    }
}
