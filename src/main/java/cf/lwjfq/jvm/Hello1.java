package cf.lwjfq.jvm;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-27 17:41
 * @ Description: JVM 参数调优
 * -Xms1024m -Xmx1024m -XX:+PrintGCDetails
 **/
public class Hello1 {
    public static void main(String[] args) {
        // 获取CPU核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        long maxMemory = Runtime.getRuntime().maxMemory() ;//返回 Java 虚拟机试图使用的最大内存量。
        long totalMemory = Runtime.getRuntime().totalMemory() ;//返回 Java 虚拟机中的内存总量。
        System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");

    }
}
