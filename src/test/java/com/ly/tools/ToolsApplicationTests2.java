package com.ly.tools;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolsApplicationTests2 {

    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        int c = 4;
        int d = a > b ? 0 : c > b ? 1 : 0;
        // 可以用括号分开来看，会比较清楚
        // int d = a > b ? 0 : (c > b ? 1 : 0);
        System.out.println("值1:" + (c > b ? 1 : 0));
        System.out.println("值2:" + d);

        if (a > b) {
            d = 0;
        } else {
            if (c > b) {
                d = 1;
            } else {
                d = 0;
            }
        }
        System.out.println(d);

    }


}
