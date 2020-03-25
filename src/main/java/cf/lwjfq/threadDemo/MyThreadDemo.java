package cf.lwjfq.threadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-25 21:47
 * @ Description: Java 线程池计数
 **/
public class MyThreadDemo {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5); // 线程池
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); //一池子一个工作线程类似银行只有一个窗口
        ExecutorService executorService = Executors.newCachedThreadPool(); //可扩容线程池
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+" 办理业务");
            });
        }
    }
}
