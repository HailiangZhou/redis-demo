package com.atguigu.redis.test.volatiles;

class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "我是构造方法SingletonDemo()");
    }

    //DCL(double check lock 双端检锁机制)
    public static SingletonDemo getInstance() {

        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }

        return instance;
    }
}

public class SingletonByVolatile {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }).start();
        }
    }
}
