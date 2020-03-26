package cf.lwjfq.jvm;

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-27 00:06
 * @ Description: 类加载器
 **/
public class Hello {

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader());
        System.out.println();
        System.out.println();
        System.out.println();
        Hello hello = new Hello();
        System.out.println(hello.getClass().getClassLoader());
        System.out.println(hello.getClass().getClassLoader().getParent());
        System.out.println(hello.getClass().getClassLoader().getParent().getParent());
    }
}
