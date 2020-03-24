package cf.lwjfq.volatiledemo;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-24 10:40
 * @ Description: volatile 关键字
 * 较为轻量级的同步策略
 * 1. 数据在内存中可见性 ：在多个线程操作共享数据时内存中的数据是可见的。
 * 2. 不能保证原子性。
 * 3. 防止指令重排
 **/
public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true){
            if (td.isFlag()) {
                System.out.println("====================");
                break;
            }
        }
    }
}
class ThreadDemo implements Runnable {

    private volatile boolean flag = false;
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag="+flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
