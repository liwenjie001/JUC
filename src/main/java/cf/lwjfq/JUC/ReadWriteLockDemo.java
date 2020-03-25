package cf.lwjfq.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-25 18:07
 * @ Description: 读写分离锁
 * 多个线程同时读取一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是如果一个线程想去写共享资源来，就不应该在有其他线程可以对资源进行读或者写
 * 小总结：
 *      读-读能共享
 *      读-写不能共享
 *      写-写不能共享
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        // 5个线程进行写
        for (int i = 0; i < 5; i++) {
            int finalI = i;

            new Thread(()->{
                myCache.put(finalI+"", finalI);
            },String.valueOf(i)).start();
        }
        // 5个线程进行读
        for (int i = 0; i < 5; i++) {
            int finalI = i;

            new Thread(()->{
                myCache.get(finalI+"");
            },String.valueOf(i)).start();
        }
    }
}
class MyCache {
    private volatile Map<String, Object> hashMap = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock() ;

    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+" ===写入数据"+key);
        hashMap.put(key, value);
        System.out.println(Thread.currentThread().getName()+" ===写入完成");
        readWriteLock.writeLock().unlock();
    }
    public void get(String key){
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+" ===读取数据"+key);
        hashMap.get(key);
        System.out.println(Thread.currentThread().getName()+" ===读取完成");
        readWriteLock.readLock().unlock();
    }
}