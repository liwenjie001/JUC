package cf.lwjfq.JUC;


import java.util.concurrent.CountDownLatch;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-25 17:29
 * @ Description: 当其他线程运行完了在运行下面的代码
 * 倒计时
 * 做减法
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长关门走人");
    }

    private static void closeDoor() {
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "离开教室");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"班长关门走人");
    }
}
