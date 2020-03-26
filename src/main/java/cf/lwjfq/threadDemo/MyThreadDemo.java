package cf.lwjfq.threadDemo;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-25 21:47
 * @ Description: Java 线程池计数
 **/
public class MyThreadDemo {
    // 自定义线程池
    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 9; i++) {
//            Thread.sleep(20);
            threadPoolExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName()+" 办理业务");
            });
        }
        threadPoolExecutor.shutdown();
//        initPool();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static void initPool() {
//        ExecutorService executorService = Executors.newFixedThreadPool(5); // 线程池
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); //一池子一个工作线程类似银行只有一个窗口
        ExecutorService executorService = Executors.newCachedThreadPool(); //可扩容线程池
        for (int i = 0; i < 10; i++) {
//            Thread.sleep(20);
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+" 办理业务");
            });
        }
        executorService.shutdown();
    }
}
