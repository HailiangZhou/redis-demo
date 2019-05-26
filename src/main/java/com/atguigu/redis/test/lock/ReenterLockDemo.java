package com.atguigu.redis.test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone {

    public synchronized void send() {
        System.out.println(Thread.currentThread().getName() + " send()....");
        //睡3秒保证期间main线程能调用到sendMessage方法，但是T1已经占用锁，
        // 要等到T1执行完释放了锁，main线程才能执行
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendMessage();
    }

    public synchronized void sendMessage() {
        System.out.println(Thread.currentThread().getName() + " sendMessage()....");
    }
}

class Phone2 {
    Lock lock = new ReentrantLock();

    public void send() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " send()....");

            //睡3秒保证期间main线程能调用到sendMessage方法，但是T1已经占用锁，
            // 要等到T1执行完释放了锁，main线程才能执行
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sendMessage();
        } finally {
            lock.unlock();
        }
    }

    public void sendMessage() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " sendMessage()....");
        } finally {
            lock.unlock();
        }
    }


}

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        new Thread(() -> {
            phone2.send();
        }).start();

        //保证T1先执行
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        phone2.sendMessage();


       /* Phone phone = new Phone();
        new Thread(() -> {
            phone.send();
        }, "T1").start();

        //保证T1先执行
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        phone.sendMessage();*/

    }
}
