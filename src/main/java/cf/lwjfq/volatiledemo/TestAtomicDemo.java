package cf.lwjfq.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-24 11:07
 * @ Description: 原子性问题
 * 1. i++ 的原子性问题
 * 2. 使用原子变量解决原子性问题
 * 3. 在jdk1.5以后使用java.util.concurrent.atomic包下面的变量
 *     1. 使用了volatile 保证了可见性
 *     2. 使用CAS(Compare-And-Swap)算法保证原子性
 *          CAS 是我们硬件对并发操作共享数据的支持
 *          内存值 V
 *          预估值 A
 *          更新值 B
 *          当且仅当 V==A时，V=B,否则不做任何操作
 **/
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(atomicDemo).start();
        }
    }
}
class AtomicDemo implements Runnable{

        private int number = 0;
//    private AtomicInteger number = new AtomicInteger();
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(add());
    }
    public int add(){
        return number ++;
    }
}