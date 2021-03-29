package com.ly.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
class ToolsApplicationTests1 {

    /**
     * java8新特性
     */
    @Test
    void contextLoads() {
        // 一、lambda表达式(Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。使用Lambda 表达式可以使代码变的更加简洁紧凑。)

        // 二、Stream流过滤
        // 在Java 8中,集合接口有两个方法来生成流：
        // stream() −为集合创建串行流。
        // parallelStream() − 为集合创建并行流。
        List<String> list = Arrays.asList("a", "b", "c", "", "d");
        List<String> collect = list.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        System.out.println(collect);
        // 2、forEach
        //Stream 提供了新的方法 'forEach' 来迭代流中的每个数据
        Random random = new Random();
        random.ints().limit(6).forEach(System.out::println);
        System.out.println("---------------------------------------------");
        // 3、map
        // map 方法用于映射每个元素到对应的结果
        List<Integer> asList = Arrays.asList(1, 2, 3, 3, 4, 6, 6, 8);
        System.out.println(asList.stream().map(s->(s+1)).distinct().collect(Collectors.toList()));
        // 4、sorted
        // sorted 方法用于对流进行排序

        // 5、并行（parallel）程序
        // 多线程下，使用并行流会有线程安全的问题
        // 三、Java 8通过发布新的Date-Time API (JSR 310)来进一步加强对日期与时间的处理。
        LocalDateTime now = LocalDateTime.now();

    }

}
