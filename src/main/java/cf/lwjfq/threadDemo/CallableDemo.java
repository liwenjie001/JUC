package cf.lwjfq.threadDemo;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-24 19:42
 * @ Description:
 * java的第三种多态的方式
 *
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        futureTask.run();
        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<Integer>{
   @Override
    public Integer call() throws Exception {
        System.out.println("*******come in ******");
        return 1024;
    }
}