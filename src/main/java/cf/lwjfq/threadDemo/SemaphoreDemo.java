package cf.lwjfq.threadDemo;

import java.util.concurrent.Semaphore;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-25 17:49
 * @ Description: 争车位
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);// 模拟资源类有三个空车位
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(); // 抢占车位
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"离开了车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(); // 释放车位
                }

            }, String.valueOf(i)).start();
        }
    }
}
