package com.atguigu.redis.test.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CollectionDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();

        }
        System.out.println("22222222222");
    }
}
