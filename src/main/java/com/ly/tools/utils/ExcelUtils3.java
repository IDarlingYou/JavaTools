package com.ly.tools.utils;

import com.ly.tools.entity.ExcelData;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
public class ExcelUtils3 {

    public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data, Integer i) throws Exception {

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");

        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));

        //把内容写入Excel表
        exportExcel(data, response.getOutputStream(), i);
    }


    /**
     * 把内容写入Excel表
     *
     * @param data 传入要写的内容，此处以一个ExcelData分装类内容为例
     * @param out  把输出流怼到要写入的Excel上，然后往里面写数据
     */
    public static void exportExcel(ExcelData data, OutputStream out, Integer i) throws Exception {
        /**
         　　　　使用的是JAVA POI实现的导出Excel表；
         　　　　POI 提供了对2003版本的Excel的支持 ---- HSSFWorkbook
         　　　　POI 提供了对2007版本以及更高版本的支持 ---- XSSFWorkbook
         */
        //创建Excel工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            //设置工作表名称 为空默名认为Sheet1
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            //创建Excel工作表
            HSSFSheet sheet = wb.createSheet(sheetName);
            //参数：  工作簿、工作表、数据
            writeExcel(wb, sheet, data, i);
            wb.write(out);
        } catch (Exception e) {
            log.error("错误信息",e);
        } finally {
            //此处需要关闭 wb 变量
            out.close();
        }
    }

    private static void writeExcel(HSSFWorkbook wb, Sheet sheet, ExcelData data, Integer i) {
        int rowIndex;
        //写标题内容
        rowIndex = writeTitlesToExcel(wb, sheet, data.getPaymentDays(), data.getTitles(), data.getErji(), i);
        if (data.getRows() != null && data.getRows().size() > 0) {
            //填充和颜色设置
            writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
        }
        //自动根据长度调整单元格长度
        autoSizeColumns(sheet, data.getTitles().size() + 1);

    }

    private static HSSFCellStyle titleStyle(HSSFWorkbook wb, Short i, String typeFace ) {
        //字体类
       // Font titleFont = wb.createFont();
        HSSFFont titleFont = wb.createFont();
        //设置字体类型
        // titleFont.setFontName("simsun");
        titleFont.setFontName(typeFace);
        //设置字体大小
        //titleFont.setFontHeightInPoints((short) 14);
        titleFont.setColor(IndexedColors.BLACK.index);
        //设置是否加粗
        //titleFont.setBold(true);
        //创建单元格样式
        HSSFCellStyle titleStyle = wb.createCellStyle();
        //titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        //自定义颜色
       // titleStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        // 设置背景颜色
        titleStyle.setFillForegroundColor(i);
        //设置填充方案
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //设置字体样式
        titleStyle.setFont(titleFont);
        // 边框颜色
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(169,169,169)));
        return titleStyle;
    }

    //写标题内容
    private static int writeTitlesToExcel(HSSFWorkbook wb, Sheet sheet,String paymentDays, List<String> yiji, List<String> erji, Integer i) {
        //行数
        int rowIndex = 0;
        //列数
        int colIndex = 0;
        //创建Excel工作表的行
        Row titleRow = sheet.createRow(rowIndex);
        // titleRow.setHeightInPoints(25);
        rowIndex = getRowIndex(wb , sheet,paymentDays ,  yiji, erji, rowIndex, colIndex, titleRow, i);
        return rowIndex;
    }

    private static int getRowIndex(HSSFWorkbook wb,Sheet sheet, String paymentDays , List<String> yiji, List<String> erji, int rowIndex, int colIndex,  Row titleRow, Integer i) {
        HSSFCellStyle titleStyle = titleStyle(wb, IndexedColors.YELLOW.getIndex(), "simsun");
        HSSFCellStyle titleStyle1 = titleStyle(wb, IndexedColors.WHITE.getIndex(), "simsun");

        if (i == 0) {
            titleRow = sheet.createRow(rowIndex);
            for (String field : yiji) {
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow.createCell(colIndex);
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
                cell.setCellStyle(titleStyle);
                colIndex++;
            }
        } else if (i == 1) {
            colIndex = 0;
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
            if (yiji.size()==3){
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
            }else {
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, erji.size()));
            }
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 8));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 12));

            int preColNum1 = 5;
            setEmptyCellStyle(titleRow, 0, preColNum1, titleStyle);

            // 设置标题
            Cell cell1 = titleRow.createCell(colIndex );
            //单元格内容
            cell1.setCellValue(paymentDays);
            //自定义颜色
            cell1.setCellStyle(titleStyle);

            // 添加斜杠
            Row titleRow1 = sheet.createRow(rowIndex+1);
            setEmptyCellStyle(titleRow1, 0, preColNum1, titleStyle1);
            setDiagonal(sheet,  titleStyle1 , titleRow1);

            //设置边框
            for (String field : yiji) {
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow1.createCell(colIndex + 1);
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
               cell.setCellStyle(titleStyle1);
                colIndex += 4;
            }
            rowIndex = getRowIndex(sheet, erji, rowIndex+1, titleStyle1);
        } else if (i == 2) {
            colIndex = 0;
            // 合并第一列的前两行
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
            // 合并第一行的第二列到第四列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));
            // 合并第一行的第四列到第10列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 9));
            //设置边框
            for (int j = 0; j < yiji.size(); j++) {
                String field = yiji.get(j);
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow.createCell(colIndex + 1);
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
                cell.setCellStyle(titleStyle);
                if (j == yiji.size() - 1) {
                    colIndex += 4;
                } else {
                    colIndex += 5;
                }
            }
            rowIndex = getRowIndex(sheet, erji, rowIndex, titleStyle);
        } else if (i == 3) {
            colIndex = 0;

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));

            int preColNum1 = 4;
            setEmptyCellStyle(titleRow, 0, preColNum1, titleStyle);

            // 设置标题
            Cell cell1 = titleRow.createCell(colIndex );
            //单元格内容
            cell1.setCellValue(paymentDays);
            //自定义颜色
            cell1.setCellStyle(titleStyle);

            // 添加斜杠
            Row titleRow1 = sheet.createRow(rowIndex+1);
            setEmptyCellStyle(titleRow1, 0, preColNum1, titleStyle1);
            setDiagonal(sheet,  titleStyle1 , titleRow1);

            //设置边框
            for (String field : yiji) {
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow1.createCell(colIndex + 1);
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
                cell.setCellStyle(titleStyle1);
                colIndex += 3;
            }
            rowIndex = getRowIndex(sheet, erji, rowIndex+1, titleStyle1);
        } else if (i == 4) {
            colIndex = 0;
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 6));

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
            sheet.addMergedRegion(new CellRangeAddress(0, 2, 2, 4));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));


            //设置边框
            for (String field : yiji) {
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow.createCell(colIndex + 1);
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
                cell.setCellStyle(titleStyle);
                colIndex += 4;
            }
            rowIndex = getRowIndex(sheet, erji, rowIndex, titleStyle);
        } else if (i == 5) {
            colIndex = 0;
           // sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 6));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 9));
          //  sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 12));

            int preColNum1 = 10;
            setEmptyCellStyle(titleRow, 0, preColNum1, titleStyle);

            // 设置标题
//            Cell cell1 = titleRow.createCell(colIndex );
//            //单元格内容
//            cell1.setCellValue(paymentDays);
//            //自定义颜色
//            cell1.setCellStyle(titleStyle);

            // 添加斜杠
            Row titleRow1 = sheet.createRow(rowIndex);
            setEmptyCellStyle(titleRow1, 0, preColNum1, titleStyle1);
            setDiagonal1(sheet,  titleStyle1 , titleRow1);

            //设置边框
            for (String field : yiji) {
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow1.createCell(colIndex+1 );
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
                cell.setCellStyle(titleStyle1);
                colIndex += 3;
            }
            rowIndex = getRowIndex(sheet, erji, rowIndex, titleStyle1);
        } else if (i == 6) {
            colIndex = 0;
        //    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
            if (yiji.size()==3){
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 4));
            }else {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, erji.size()));
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 9, 12));

            int preColNum1 = 4;
            setEmptyCellStyle(titleRow, 0, preColNum1, titleStyle);

//            // 设置标题
//            Cell cell1 = titleRow.createCell(colIndex );
//            //单元格内容
//            cell1.setCellValue(paymentDays);
//            //自定义颜色
//            cell1.setCellStyle(titleStyle);

            // 添加斜杠
            Row titleRow1 = sheet.createRow(rowIndex);
            setEmptyCellStyle(titleRow1, 0, preColNum1, titleStyle1);
            setDiagonal1(sheet,  titleStyle1 , titleRow1);

            //设置边框
            for (String field : yiji) {
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow1.createCell(colIndex + 1);
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
                cell.setCellStyle(titleStyle1);
                colIndex += 4;
            }
            rowIndex = getRowIndex(sheet, erji, rowIndex, titleStyle1);
        }  else if (i == 7) {

            colIndex = 0;

            CellRangeAddress cellRa = new CellRangeAddress(0, 0, 0, 5);
            sheet.addMergedRegion(cellRa);

            CellRangeAddress cellRange = new CellRangeAddress(1, 2, 0, 0);
            sheet.addMergedRegion(cellRange);

            CellRangeAddress rangeAddress1 = new CellRangeAddress(1, 1, 1, 5);
            sheet.addMergedRegion(rangeAddress1);

            int preColNum1 = 6;
            setEmptyCellStyle(titleRow, 0, preColNum1, titleStyle);

            // 设置标题
            Cell cell1 = titleRow.createCell(colIndex );
            //单元格内容
            cell1.setCellValue(paymentDays);
            //自定义颜色
            cell1.setCellStyle(titleStyle);

            // 添加斜杠
            Row titleRow1 = sheet.createRow(rowIndex+1);
            setEmptyCellStyle(titleRow1, 0, preColNum1, titleStyle1);
            setDiagonal(sheet,  titleStyle1 , titleRow1);

            //设置边框
            for (String field : yiji) {
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow1.createCell(colIndex + 1);
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
                cell.setCellStyle(titleStyle1);
                // 列
                colIndex += 5;
            }
            rowIndex = getRowIndex(sheet, erji, rowIndex+1, titleStyle1);

        }else if (i == 8) {

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 7));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 11));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 12, 15));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 19));

            int preColNum1 = 20;
            setEmptyCellStyle(titleRow, 0, preColNum1, titleStyle);

            // 设置标题
            Cell cell1 = titleRow.createCell(colIndex );
            //单元格内容
            cell1.setCellValue(paymentDays);
            //自定义颜色
            cell1.setCellStyle(titleStyle);

            // 添加斜杠
            Row titleRow1 = sheet.createRow(rowIndex+1);
            setEmptyCellStyle(titleRow1, 0, 6, titleStyle1);
            setDiagonal(sheet,  titleStyle1 , titleRow1);

            int rowIndex2 = getRowIndex2(sheet, yiji, rowIndex, titleStyle1);

            rowIndex = getRowIndex3(sheet, erji, rowIndex2, titleStyle1);

        }else if (i == 9) {
            colIndex = 0;
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));

            int preColNum1 = 4;
            setEmptyCellStyle(titleRow, 0, preColNum1, titleStyle);
            // 设置标题
            Cell cell1 = titleRow.createCell(colIndex );
            //单元格内容
            cell1.setCellValue(paymentDays);
            //自定义颜色
            cell1.setCellStyle(titleStyle);
            // 添加斜杠
            Row titleRow1 = sheet.createRow(rowIndex+1);
            setEmptyCellStyle(titleRow1, 0, preColNum1, titleStyle1);
            setDiagonal(sheet,  titleStyle1 , titleRow1);
            //设置边框
            for (String field : yiji) {
                //创建Excel工作表指定行的单元格
                Cell cell = titleRow1.createCell(colIndex + 1);
                //单元格内容
                cell.setCellValue(field);
                //自定义颜色
                cell.setCellStyle(titleStyle1);
                colIndex += 4;
            }
            rowIndex = getRowIndex(sheet, erji, rowIndex+1, titleStyle1);
        }
        rowIndex++;
        return rowIndex;
    }

    private static int getRowIndex(Sheet sheet, List<String> erji, int rowIndex, HSSFCellStyle titleStyle) {
        int colIndex;
        colIndex = 0;
        rowIndex++;
        Row titleRow2 = sheet.createRow(rowIndex);
        int preColNum1;
        if(erji.size() < 4){
            preColNum1 = 4;
        }else if(erji.size() == 4){
            preColNum1 = 5;
        }else if(erji.size() == 5){
            preColNum1 = 6;
        }else {
            preColNum1 = 5;
        }
        setEmptyCellStyle(titleRow2, 0, preColNum1, titleStyle);
        for (String field : erji) {
            //创建Excel工作表指定行的单元格
            Cell cell = titleRow2.createCell(colIndex + 1);
            //单元格内容
            cell.setCellValue(field);
            //自定义颜色
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        return rowIndex;
    }


    //填充和颜色设置
    private static int writeRowsToExcel(HSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex;
        HSSFFont dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        // dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);
        HSSFCellStyle dataStyle = wb.createCellStyle();
//        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
      //  setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            // dataRow.setHeightInPoints(25);
            colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }

               cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    //自动根据长度调整单元格长度
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            //自动根据长度调整单元格长度
            sheet.autoSizeColumn(i, true);
            int newWidth = sheet.getColumnWidth(i) + 1500;
            sheet.setColumnWidth(i, Math.max(newWidth, orgWidth));

        }
    }

    //设置边框
    private static void setBorder(HSSFCellStyle style, BorderStyle border, XSSFColor color) {
        //上边框
        style.setBorderTop(border);
        //左边框
        style.setBorderLeft(border);
        //右边框
        style.setBorderRight(border);
        //下边框
        style.setBorderBottom(border);
//        //边框样式
//        style.setBorderColor(BorderSide.TOP, color);
//        style.setBorderColor(BorderSide.LEFT, color);
//        style.setBorderColor(BorderSide.RIGHT, color);
//        style.setBorderColor(BorderSide.BOTTOM, color);
    }
    /**
     * 导入
     */

    //导入
    private static int getRowIndex2(Sheet sheet, List<String> yiji, int rowIndex, HSSFCellStyle titleStyle) {
        //列数
        int colIndex;
        colIndex = 1;
        rowIndex++;
        Row titleRow2 = sheet.createRow(rowIndex);
        int preColNum1 = 20;
        setEmptyCellStyle(titleRow2, 0, preColNum1, titleStyle);
        // 添加斜杠
        setDiagonal(  sheet,  titleStyle , titleRow2);
        for (int m = 0; m < yiji.size()  ; m++) {
            String s = yiji.get(m);
            //创建Excel工作表指定行的单元格
            Cell cell = titleRow2.createCell(colIndex );
            //单元格内容
            cell.setCellValue(s);
            //自定义颜色
            cell.setCellStyle(titleStyle);
            if (m == 0) {
                colIndex += 4;
            } else if(m == 1) {
                colIndex += 3;
            }else {
                colIndex += 4;
            }
        }
        return rowIndex;
    }

    private static int getRowIndex3(Sheet sheet, List<String> erji, int rowIndex, HSSFCellStyle titleStyle) {
        //列数
        int colIndex;
        colIndex = 1;
        rowIndex++;
        Row titleRow2 = sheet.createRow(rowIndex);
        int preColNum1 = 19;
        setEmptyCellStyle(titleRow2, 0, preColNum1, titleStyle);
        for (int m = 0; m < erji.size()  ; m++) {
            String s = erji.get(m);
            //创建Excel工作表指定行的单元格
            Cell cell = titleRow2.createCell(colIndex );
            //单元格内容
            cell.setCellValue(s);
            //自定义颜色
            cell.setCellStyle(titleStyle);
            colIndex += 1;
        }
        return rowIndex;
    }

    private static Sheet createWorkBook(String fileName, MultipartFile filePath) {
        boolean isExcel2003;
        XSSFWorkbook wb;
        HSSFWorkbook wh;
        Sheet sheet = null;
        try {
            InputStream is = filePath.getInputStream();
            isExcel2003 = !fileName.matches("^.+\\.(?i)(xlsx)$");
            if (isExcel2003) {
                wh = new HSSFWorkbook(is);
                sheet = wh.getSheetAt(0);
            } else {
                wb = new XSSFWorkbook(is);
                sheet = wb.getSheetAt(0);
            }
        } catch (Exception e) {
            log.error("错误信息",e);
        }
        return sheet;
    }

    /**
     * 添加斜杠
     */
    private static void setDiagonal(Sheet sheet, HSSFCellStyle titleStyle , Row titleRow) {
        // 添加斜杠
        Cell cell0 = titleRow.createCell(0 );
        cell0.setCellValue("机构    类型");
        cell0.setCellStyle(titleStyle);
        //画线(由左上到右下的斜线)  在A1的第一个cell（单位  分类）加入一条对角线
        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
        HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255, (short) 0, 1, (short) 0, 2);
        // 创建形状（直线）
        HSSFSimpleShape shape1 = patriarch.createSimpleShape(a);
        shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
        shape1.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
    }

    /**
     * 添加斜杠
     */
    private static void setDiagonal1(Sheet sheet, HSSFCellStyle titleStyle , Row titleRow) {
        // 添加斜杠
        Cell cell0 = titleRow.createCell(0 );
        cell0.setCellValue("机构    类型");
        cell0.setCellStyle(titleStyle);
        //画线(由左上到右下的斜线)  在A1的第一个cell（单位  分类）加入一条对角线
        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
        HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255, (short) 0, 0, (short) 0, 1);
        // 创建形状（直线）
        HSSFSimpleShape shape1 = patriarch.createSimpleShape(a);
        shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
        shape1.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
    }

    /**
     * 设置合并表格，空缺单元格样式
     * @param row
     * @param startNum
     * @param endNum
     * @param style
     */
    private static void setEmptyCellStyle(Row row, int startNum, int endNum, HSSFCellStyle style) {
        for (int j = startNum; j < endNum; j++) {
            Cell cell = row.createCell(j);
            cell.setCellStyle(style);
        }
    }

}
