package com.ly.tools.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: LY
 * @Date: 2021/3/9 17:30
 * @Description: excel文件导入
 **/
@Slf4j
public class ExcelImportUtils {


    public static List<?> getBankListByExcel(InputStream in) throws Exception {
        //创建Excel工作薄
        Workbook work = getWorkbook(in);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet;
        Row row;
        Cell cell = null;

        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }

//                    Teacher teacher = new Teacher();
//                    //把每个单元格的值付给对象的对应属性
//                    if (row.getCell(0)!=null){
//                        teacher.setAccount(String.valueOf(getCellValue(row.getCell(0))));
//                    }
//                    //遍历所有的列(把每一行的内容存放到对象中)
//                    list.add(teacher);
            }
        }

        return null;
    }

    /**
     * @param inStr
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr) throws Exception {
        Workbook wb;
        wb = WorkbookFactory.create(inStr);
        return wb;
    }

    /**
     * 描述：excel导入 对表格中数值进行格式化
     *
     * @param cell 单元格
     * @return
     */
    public static Object getCellValue(Cell cell) {
        //格式化number String字符
        DecimalFormat df = new DecimalFormat("0");
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        //格式化数字
        DecimalFormat df2 = new DecimalFormat("0");

        //判断是否为null或空串
        if (cell == null || "".equals(cell.toString().trim())) {
            return "";
        }
        String value = "";

        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                boolean b = cell.getBooleanCellValue();
                value = String.valueOf(b);
                break;
            case BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }


}

