package com.atguigu.redis.test.atomic;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Setter
@ToString
@AllArgsConstructor
class User {
    private String name;
    private int age;

}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User u1 = new User("张三", 20);
        User u2 = new User("李四", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(u1);

        System.out.println(atomicReference.compareAndSet(u1, u2) + "  " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(u1, u2) + "  " + atomicReference.get().toString());
        System.out.println("33333333333333");
    }
}
