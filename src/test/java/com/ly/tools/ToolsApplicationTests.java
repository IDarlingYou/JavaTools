package com.ly.tools;


import com.ly.tools.entity.ExcelData;
import com.ly.tools.utils.ExcelExportUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ToolsApplicationTests {

    @Test
    void contextLoads() {
        // 测试excel导出
        ExcelData excelData = new ExcelData();
        excelData.setName("纸质对账");
        // 设置标题
        List<String> titles1 = new ArrayList<>();
        titles1.add("网银对账");
        excelData.setTitles(titles1);
        // 设置表头字段
        List<String> titles = new ArrayList<>();
        titles.add("姓名");
        titles.add("年龄");
        titles.add("电话");
        titles.add("地址");
        excelData.setErji(titles);
        // 设置内容
        List<List<Object>> content = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("First");
        row.add("张三");
        row.add("18");
        row.add("1666666666");
        row.add("上海白金湾");
        content.add(row);
        excelData.setRows(content);
        // 调用导出
        try {
            ExcelExportUtils.exportExcel(excelData , null,3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
