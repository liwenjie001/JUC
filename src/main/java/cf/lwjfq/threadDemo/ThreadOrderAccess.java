package cf.lwjfq.threadDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-24 18:04
 * @ Description:  多个线程按顺序执行
 * 题目： 多线程之间按照顺序执行实现从A->B->C
 * 三个线程启动 要求如下 AA 打印5次 BB打印10次 CC打印15次
 * 接着 AA 打印5次 BB打印10次 CC打印15次
 * 来10伦
 * 思想：
 *  1. 高聚低合前提下，线程操作资源类
 *  2. 判断//干货//通知
 *  3. 多线程交互下，必须防止多线程的虚假唤醒，也即（判断使用while 不能使用if）
 *  4. 标记位
 **/
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResources shareResources = new ShareResources();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResources.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResources.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResources.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
    }
}

class ShareResources{
    private int number = 1; // A:1 B:2 C:3
    private ReentrantLock lock = new ReentrantLock();
    private  Condition condition1 = lock.newCondition();
    private  Condition condition2 = lock.newCondition();
    private  Condition condition3 = lock.newCondition();
    public void print5() throws InterruptedException {
        lock.lock();
        // 判断
        while (number!=1){
            condition1.await();
        }
        // 干货
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);

        }
        // 通知
        number = 2;
        condition2.signal();
        lock.unlock();
    }
    public void print10() throws InterruptedException {
        lock.lock();
        // 判断
        while (number!=2){
            condition2.await();
        }
        // 干货
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);

        }
        // 通知
        number = 3;
        condition3.signal();
        lock.unlock();
    }
    public void print15() throws InterruptedException {
        lock.lock();
        // 判断
        while (number!=3){
            condition3.await();
        }
        // 干货
        for (int i = 0; i < 15; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);

        }
        // 通知
        number = 1;
        condition1.signal();
        lock.unlock();
    }
}