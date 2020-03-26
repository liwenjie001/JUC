package cf.lwjfq.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private Integer id;
    private String userName;
    private int age;
}

/**
 * @ Author: Mr.Li
 * @ Date: 2020-03-26 21:02
 * @ Description: Java 流式计算
 * 题目： 请按照给出数据，找出同时满足以下条件的用户，
 * 偶数ID 且年龄大于24且用户名转为大写且用户名字母到排序，只输出一个用户名。
 * 简单的理解一下 和 scala 没有太大的区别。
 **/
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(15, "e", 26);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream().filter(p -> {
            return p.getId() % 2 == 0;
        }).filter(p -> {
            return p.getAge() > 24;
        }).map(f -> {
            return f.getUserName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);

        // 以下为四大函数式接口
        Function<String, Integer> fun = s -> s.length();
        System.out.println(fun.apply("aaaa"));
        Predicate<String> predicate = s -> s.isEmpty();
        System.out.println(predicate.test("s"));
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("sss");
        Supplier<String> supplier = ()-> {return "Java Stream";};
        System.out.println(supplier.get());
    }
}

