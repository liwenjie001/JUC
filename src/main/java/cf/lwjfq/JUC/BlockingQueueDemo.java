package cf.lwjfq.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-25 21:02
 * @ Description: 阻塞队列
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        /*arrayBlockingQueue.add("a");
        arrayBlockingQueue.add("b");
        arrayBlockingQueue.add("c");
//        arrayBlockingQueue.add("d");
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
//        System.out.println(arrayBlockingQueue.remove());*/
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d"));
        /*System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());*/
        System.out.println(arrayBlockingQueue.offer("d",300L, TimeUnit.SECONDS));
    }
}
