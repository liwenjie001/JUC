package cf.lwjfq.volatiledemo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-24 13:02
 * @ Description: 写一个ArrayList 线程不安全的例子
 * 1. 故障现象
 *  java.util.ConcurrentModificationException
 * 2. 导致的原因：
 *  多线程并发挣抢同一个资源类并且没有加锁。
 * 3. 解决方法
 *  3.1 new Vector<>()
 *  3.2 Collections.synchronizedList(new ArrayList<>())
 *  3.3 new CopyOnWriteArrayList<>() 写时复制系统
 **/
public class NotSafeDemo {
    public static void main(String[] args) {

        hashMapNotSafe();
    }

    private static void hashMapNotSafe() {
        Map<String, String> hashMap = new HashMap<>();
        //并发版本的hashMap。
//        Map<String, String> hashMap = new ConcurrentHashMap<>() ;
        for (int i = 0; i < 300; i++) {
            new Thread(()-> {
                hashMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(hashMap);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
//        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 3; i++) {
            new Thread(()-> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //List<String> arrayList = new ArrayList();
        //List<String> arrayList = new Vector<>();
        List<String> arrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(()-> {
                arrayList.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(arrayList);
            }, String.valueOf(i)).start();
        }
    }
}
