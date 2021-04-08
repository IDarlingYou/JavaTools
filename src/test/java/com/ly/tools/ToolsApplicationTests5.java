package com.ly.tools;

import com.ly.tools.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolsApplicationTests5 {

    @Autowired
    private RedisUtil redisUtil;


    @Test
    public void testJson() {
        // redisUtil.set("tel","666");
        System.out.println("获取存储键值：" + redisUtil.get("name"));
    }

}
