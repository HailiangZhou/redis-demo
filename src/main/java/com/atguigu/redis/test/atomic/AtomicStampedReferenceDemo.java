package com.atguigu.redis.test.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    private static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("A", 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第一次版本号：" + stamp);
            //暂停T1一秒钟，保证T2也获得当前版本
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result1 = atomicStampedReference.compareAndSet("A", "B", stamp, ++stamp);
            System.out.println(stamp);
            System.out.println(Thread.currentThread().getName() + "是否操作成功：" + result1 + ",第二次版本号：" + atomicStampedReference.getStamp());
            boolean result2 = atomicStampedReference.compareAndSet("B", "A", stamp, ++stamp);
            System.out.println(Thread.currentThread().getName() + "是否操作成功：" + result2 + ",第三次版本号：" + atomicStampedReference.getStamp());
        }, "T1").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第一次版本号：" + stamp);
            //暂停T2三秒钟，保证T2完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet("A", "X", stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "是否修改成功：" + result + ",当前版本号：" + atomicStampedReference.getStamp());
            System.out.println("当前实际最新值：" + atomicStampedReference.getReference());
        }, "T2").start();
        System.out.println("444444444444");
    }
}
