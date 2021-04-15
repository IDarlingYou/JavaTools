package com.ly.tools;

import com.ly.tools.entity.po.ExcelData;
import com.ly.tools.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolsApplicationTests2 {

    @Test
    public void testJson() {
        ExcelData e = new ExcelData();
        e.setName("王霸天");
        System.out.println("-------------------");
        String s = null;
        try {
            s = JsonUtils.objectToJson(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("测试对象转json:" + s);

        System.out.println("-------------------");

        Object a = "{\"paymentDays\":null,\"titles\":null,\"erji\":null,\"sanji\":null,\"rows\":null,\"name\":\"王霸天\"}";

        ExcelData pojo = JsonUtils.jsonToPojo(String.valueOf(a), ExcelData.class);
        System.out.println("测试json转pojo:" + pojo);

    }

}
