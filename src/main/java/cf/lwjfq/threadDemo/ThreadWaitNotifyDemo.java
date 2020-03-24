package cf.lwjfq.threadDemo;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-24 15:56
 * @ Description: 生产者 消费者模型
 * 题目：现在有两个线程，可以操作初始值为零的变量，实现一个线程对变量加1，另一个线程对变量减1.
 * 实现交替来10个轮回。变量值为0；
 * 思想：
 *  1. 高聚低合前提下，线程操作资源类
 *  2. 判断//干货//通知
 *  3. 多线程交互下，必须防止多线程的虚假唤醒，也即（判断使用while 不能使用if）
 **/
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}

class AirConditioner{
    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        // 判断
        while (number!=0){
            this.wait();
        }
        // 干货
        number++;
        // 通知
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+":"+number);

    }
    public synchronized void decrement() throws InterruptedException {
        while (number==0){
            this.wait();
        }
        number--;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+":"+number);
    }
}