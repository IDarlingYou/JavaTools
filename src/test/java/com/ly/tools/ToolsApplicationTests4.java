package com.ly.tools;

import com.ly.tools.utils.ExportPDF;
import com.ly.tools.utils.PDFWordWaterMark;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ToolsApplicationTests4 {

    @Test
    public void testJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("jiafang", "张三");
        map.put("yifang", "李四");
        map.put("year", "2021");
        map.put("month", "12");
        map.put("day", "1");
        String s = ExportPDF.createPdf("D:\\MD\\logs\\cp.pdf", "D:\\MD\\cpp.pdf", map);
        PDFWordWaterMark.waterMark(s,"D:\\MD\\logs\\cpp.pdf","liyao");
    }

}
