package com.ly.tools;

import com.ly.tools.entity.po.Son;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolsApplicationTests3 {

    @Test
    public void testJson() {
        Son son = new Son();
        son.setId(1);
        son.setName("张三");
        son.setAddress("日本");
        Son son1 = new Son();
        son1.setId(2);
        son1.setName("老六");
        son1.setAddress("日本");
        System.out.println("比对结果是：" + son.equals(son1));

    }

}
